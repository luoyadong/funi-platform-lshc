package com.funi.platform.lshc.query;

import com.funi.framework.biz.eic.bo.Dict;
import com.funi.framework.utils.StringUtils;

import java.util.List;

/**
 * @author 3
 */
public class ContractQuery extends GhouseBaseQuery {

    //关键字
    private String keyword;
    //档案室
    private String archiveRoomNo;
    //档案柜
    private String archiveCabinetNo;
    //合同状态
    private String contractStatus;
    //管理机构id
    private String manageOrganizationId;
    //
    private List<Dict> owner;
    //
    private String ownerType="R";
    private String serviceNum;

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = StringUtils.hasText(serviceNum)?serviceNum:null;
    }

    public String getManageOrganizationId() {
        return manageOrganizationId;
    }

    public void setManageOrganizationId(String manageOrganizationId) {
        this.manageOrganizationId = StringUtils.hasText(manageOrganizationId)?manageOrganizationId:null;
    }

    public String getArchiveCabinetNo() {
        return archiveCabinetNo;
    }

    public void setArchiveCabinetNo(String archiveCabinetNo) {
        this.archiveCabinetNo = StringUtils.hasText(archiveCabinetNo)?archiveCabinetNo:null;
    }

    public String getArchiveRoomNo() {
        return archiveRoomNo;
    }

    public void setArchiveRoomNo(String archiveRoomNo) {
        this.archiveRoomNo = StringUtils.hasText(archiveRoomNo)?archiveRoomNo:null;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = StringUtils.hasText(contractStatus)?contractStatus:null;
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public void setKeyword(String keyword) {
        this.keyword = StringUtils.hasText(keyword)?keyword:null;
    }

    public List<Dict> getOwner() {
        return owner;
    }

    public void setOwner(List<Dict> owner) {
        this.owner = owner;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }
}
