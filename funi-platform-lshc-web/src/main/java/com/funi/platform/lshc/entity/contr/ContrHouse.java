package com.funi.platform.lshc.entity.contr;

import com.funi.platform.lshc.entity.BaseEntity;


/**
 * 合同房屋关联实体
 * @author 3
 */
public class ContrHouse extends BaseEntity {

    /**
     * 合同ID
     */
    private String contrId;

    /**
     * 房屋ID
     */
    private String houseId;

    public String getContrId() {
        return contrId;
    }

    public void setContrId(String contrId) {
        this.contrId = contrId;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }
}