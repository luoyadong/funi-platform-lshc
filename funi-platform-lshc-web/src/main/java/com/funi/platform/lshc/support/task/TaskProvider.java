package com.funi.platform.lshc.support.task;

import com.funi.framework.app.invocation.AppInvocationProvider;
import com.funi.framework.app.invocation.anotation.Invocation;
import com.funi.platform.lshc.service.ContractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;


/**
 * 调度任务
 *
 * @author 3
 */
@Invocation("task")
public class TaskProvider implements AppInvocationProvider {

    private static Logger logger = LoggerFactory.getLogger(TaskProvider.class);
    @Resource
    private ContractService contractService;




//    *
//     * 合同自动过期
//
   @Invocation("overdueContract")
    public String autoOverdueContract() {
        contractService.overdue();
        return "调度成功";
    }


}
