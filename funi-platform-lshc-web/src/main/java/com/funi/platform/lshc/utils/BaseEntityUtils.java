package com.funi.platform.lshc.utils;

import com.funi.framework.biz.support.CurrentUser;
import com.funi.platform.lshc.entity.BaseEntity;

import java.util.Date;

/**
 * Created by sam on 2018/11/9.10:01 PM
 */
public class BaseEntityUtils<T extends BaseEntity> {

    /**
     * 填充创建相关的公共字段
     * @param entity
     * @param currentUser
     */
    public void buildCreateEntity(T entity, CurrentUser currentUser){
        entity.setSysCreateId(currentUser.getUserId());
        entity.setSysCreateTime(new Date());
    }

    /**
     * 填充修改相关的公共字段
     * @param entity
     * @param currentUser
     */
    public void buildModifyEntity(T entity, CurrentUser currentUser) {
        entity.setSysUpdateId(currentUser.getUserId());
        entity.setSysUpdateTime(new Date());
        buildEntityVersion(entity);
    }

    /**
     * 填充逻辑删除相关的公共字段
     * @param entity
     * @param currentUser
     */
    public void buildRemoveEntity(T entity, CurrentUser currentUser) {
        entity.setIsDeleted(1);
        entity.setSysDeleteId(currentUser.getUserId());
        entity.setSysDeleteTime(new Date());
        buildEntityVersion(entity);
    }

    /**
     * 填充版本号
     * @param entity
     */
    private void buildEntityVersion(T entity) {
        Integer version = entity.getVersion();
        if (version != null) {
            entity.setVersion(++ version);
        } else {
            entity.setVersion(1);
        }
    }
}
