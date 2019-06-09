package com.funi.platform.lshc.query;

import com.funi.framework.utils.StringUtils;

/**
 * @author 3
 */
public class ResidentQuery extends GhouseBaseQuery {

    //小区id
    private String villageId;
    //关键字
    private String keyword;
    //
    private String name;
    private String contractNo;

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = StringUtils.hasText(contractNo)?contractNo:null;;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.hasText(name)?name:null;;
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public void setKeyword(String keyword) {
        this.keyword = StringUtils.hasText(keyword)?keyword:null;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = StringUtils.hasText(villageId)?villageId:null;
    }
}
