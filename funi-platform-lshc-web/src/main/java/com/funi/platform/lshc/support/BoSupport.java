package com.funi.platform.lshc.support;

import com.funi.framework.biz.eic.bo.Dict;
import com.funi.framework.biz.support.CurrentUser;
import com.funi.framework.biz.support.CurrentUserAccessor;
import com.funi.platform.lshc.entity.BaseEntity;
import com.funi.platform.lshc.entity.SuperEntity;
import com.funi.platform.lshc.query.GhouseBaseQuery;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 3
 */
@Aspect
@Order(4)
@Component
public class BoSupport {

    //默认版本
    private static final Integer VERSION = 1;
    /**
     * 注入当前用户访问组件
     */
    @Autowired(required = false)
    private CurrentUserAccessor currentUserAccessor;
    @Resource
    private UserManager userManager;

    /**
     * 创建实体
     *
     * @param superEntity
     */
    @Before("execution(public * com.funi.platform.lshc..service.*Service.create*(..)) && args(superEntity,..)")
    public void createBo(SuperEntity superEntity) {
        buildCreateBo(superEntity);
    }

    /**
     * 批量创建创建实体
     *
     * @param superEntityList
     */
    @Before("execution(public * com.funi.platform.lshc..service.*Service.create*(..)) && args(superEntityList,..)")
    public void batchCreateBo(List<SuperEntity> superEntityList) {
        for(SuperEntity superEntity : superEntityList) {
            buildCreateBo(superEntity);
        }
    }

    /**
     * 为新创建的对象构造审计字段相关属性
     * @param superEntity
     */
    private void buildCreateBo(SuperEntity superEntity) {
        // 设置ID
        superEntity.setId(UUID.randomUUID().toString());
        if(superEntity.getVersion() == null) {
            // 新创建的数据版本号为0
            superEntity.setVersion(CensusConstants.DATA_DEFAULT_VERSION);
        }
        if (superEntity.getCreateTime() == null) {
            superEntity.setCreateTime(new Date());
        }
        if (superEntity.getDeleted() == null) {
            // 默认数据未逻辑删除
            superEntity.setDeleted(CensusConstants.DATA_VALIDE_NOT_DELETE);
        }
        CurrentUser currentUser = getCurrentUser();
        if (currentUser != null && superEntity.getCreateId() == null && isAuthentication()) {
            superEntity.setCreateId(currentUser.getUserId());
        }
        if(StringUtils.isBlank(superEntity.getIsvalide())) {
            // 默认数据有效
            superEntity.setIsvalide(CensusConstants.DATA_VALIDE_VALID);
        }
    }

    private CurrentUser getCurrentUser() {
        try {
            return currentUserAccessor.getCurrentUser();
        } catch (Exception e) {
            //可以忽略该错误，套接字通信的时候才会没有
            return null;
        }
    }

    /**
     * 逻辑删除，公共字段自动填充方法
     * @param baseEntity
     */
    @Before("execution(public * com.funi.platform.lshc..service.*Service.remove*(..)) && args(baseEntity,..)")
    public void removeBo(BaseEntity baseEntity) {
        if (baseEntity.getSysDeleteTime() == null) {
            baseEntity.setSysDeleteTime(new Date());
        }
        if (null != baseEntity.getVersion()) {
            baseEntity.setVersion(baseEntity.getVersion() + 1);
        } else {
            baseEntity.setVersion(VERSION);
        }
        if (baseEntity.getSysDeleteId() == null && isAuthentication()) {
            CurrentUser currentUser = getCurrentUser();
            if (currentUser != null) {
                baseEntity.setSysDeleteId(currentUser.getUserId());
            }
        }
        baseEntity.setIsDeleted(1);
    }

    /**
     * 修改实体
     *
     * @param baseEntity
     */
    @Before("execution(public * com.funi.platform.lshc..service.*Service.update*(..)) && args(baseEntity,..)")
    public void updateBo(BaseEntity baseEntity) {
        if (baseEntity.getSysUpdateTime() == null) {
            baseEntity.setSysUpdateTime(new Date());
        }
        if (null != baseEntity.getVersion()) {
            baseEntity.setVersion(baseEntity.getVersion() + 1);
        } else {
            baseEntity.setVersion(VERSION);
        }
        if (baseEntity.getSysUpdateId() == null && isAuthentication()) {
            CurrentUser currentUser = getCurrentUser();
            if (currentUser != null) {
                baseEntity.setSysUpdateId(currentUser.getUserId());
            }
        }
    }

    private Boolean isAuthentication() {
        return null != SecurityContextHolder.getContext().getAuthentication() && !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }



    @Before("execution(public * com.funi.platform.lshc..service.*Service.find*(..)) && args(ghouseBaseQuery,..)")
    public void findByQuery(GhouseBaseQuery ghouseBaseQuery) {
        com.funi.framework.biz.eic.bo.CurrentUser user = userManager.findUser();
        String currentUserOrgCode = user.getOrganization().getDm();
        // 如果当前登录用户所属机构是房管局，不做权限控制，允许查询全部数据
        if(currentUserOrgCode.indexOf("ghouse_zf")==0){
            return;
        } else if(currentUserOrgCode.indexOf("ghouse_dg")==0){
            // 如果当前登录用户所属机构属于代管公司管理员，只返回所属机构
            if(isDgAdmin(user)) {
                List<Dict> authorityList = new ArrayList<>();
                authorityList.add(user.getOrganization());
                ghouseBaseQuery.setAuthorityList(authorityList);
            } else {
                // 代管公司普通用户只能查看自己创建的业务件
                ghouseBaseQuery.setUserId(userManager.findUser().getUserId());
            }
        } else {
            throw new RuntimeException("非房管局和代管公司用户的无效用户");
        }
    }

    /**
     * 判断当前登录用户是否是代管公司管理员
     * @param user
     * @return
     */
    private boolean isDgAdmin(com.funi.framework.biz.eic.bo.CurrentUser user) {
        for(Dict role : user.getRoles()){
            if(role.getDm().indexOf("ROLE_GHOUSE_DG_ADMIN")!=-1){
                return true;
            }
        }
        return false;
    }
}
