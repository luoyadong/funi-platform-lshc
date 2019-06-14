package com.funi.platform.lshc.utils;

import com.funi.framework.biz.eic.bo.CurrentUser;
import com.funi.platform.lshc.entity.SuperEntity;
import com.funi.platform.lshc.support.CensusConstants;

import java.util.Date;
import java.util.UUID;

/**
 * Created by sam on 2019/6/14.9:28 PM
 */
public class SuperEntityUtils <T extends SuperEntity> {
    public void buildCreateEntity(T superEntity, CurrentUser currentUser){
        // 设置ID
        superEntity.setId(UUID.randomUUID().toString());
        superEntity.setCreateId(currentUser.getUserId());
        superEntity.setCreateTime(new Date());
        // 新创建的数据版本号为0
        superEntity.setVersion(CensusConstants.DATA_DEFAULT_VERSION);
        // 默认数据未逻辑删除
        superEntity.setIsvalide(CensusConstants.DATA_VALIDE_VALID);
        // 默认数据有效
        superEntity.setDeleted(CensusConstants.DATA_VALIDE_NOT_DELETE);
    }
}
