package com.funi.platform.lshc.support.flow;

import com.funi.framework.biz.BizException;
import com.funi.platform.lshc.dto.JobDto;
import com.funi.platform.lshc.enumatation.BusinessType;
import com.funi.platform.lshc.enumatation.Conclusion;
import com.funi.platform.lshc.enumatation.Node;
import com.funi.platform.lshc.service.JobAcceptService;
import com.funi.platform.lshc.support.flow.handler.JobHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 工作完成
 *
 * @author 3
 */
@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class JobCompleted implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(JobCompleted.class);
    Collection<JobHandler> collection;
    @Resource
    private JobAcceptService jobAcceptService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        collection = applicationContext.getBeansOfType(JobHandler.class).values();
    }

    @Around(value = "execution(public * com.funi.framework.workflow.eic.service.WorkFlowService.complete(..))")
    public Object done(ProceedingJoinPoint proceedingJoinPoint) {
        Object[] args = proceedingJoinPoint.getArgs();
        String serviceNum = (String) args[0];//业务件号
        Map<String,Object> param = (Map<String,Object>)args[5] ;
        JobDto jobDto =jobAcceptService.findByServiceNum(serviceNum);
        BusinessType businessType = BusinessType.findByWorkName(jobDto.getBusinessName());
        try {
            List<String> nodeNames = (List<String>) proceedingJoinPoint.proceed();
            if (nodeNames.contains(Node.N002.getName())) { //工作流的下一个节点是结束
                for(JobHandler jobHandler:collection){
                    if(jobHandler.support(businessType)){
                        jobHandler.handle(serviceNum,
                                Enum.valueOf(Conclusion.class,param.get("flag").toString())
                                );
                    }
                }
            }
            return nodeNames;
        } catch (BizException e) {
            logger.error("调用工作流complete方法失败：" + args.toString(), e);
            throw e;
        } catch (Throwable e) {
            logger.error("调用工作流complete方法失败：" + args.toString(), e);
            throw new BizException(e);
        }
    }
}
