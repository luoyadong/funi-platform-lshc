package com.funi.platform.lshc.query;

import com.funi.framework.utils.StringUtils;

/**
 * @author 3
 */
public class CheckQuery extends GhouseBaseQuery {

    private String archiveRoomNo;
    //合同存放点_档案柜
    private String archiveCabinetNo;
    //合同存放点_档案号
    private String archiveFileNo;
    //管理机构id
    private String manageOrganizationId;
    //关键字
    private String keyword;
    //合同编号
    private String contractNo;
    //住户姓名
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.hasText(name)?name:null;;
    }

    public String getArchiveCabinetNo() {
        return archiveCabinetNo;
    }

    public void setArchiveCabinetNo(String archiveCabinetNo) {
        this.archiveCabinetNo = StringUtils.hasText(archiveCabinetNo)?archiveCabinetNo:null;
    }

    public String getArchiveFileNo() {
        return archiveFileNo;
    }

    public void setArchiveFileNo(String archiveFileNo) {
        this.archiveFileNo = StringUtils.hasText(archiveFileNo)?archiveFileNo:null;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = StringUtils.hasText(contractNo)?contractNo:null;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = StringUtils.hasText(keyword)?keyword:null;
    }

    public String getManageOrganizationId() {
        return manageOrganizationId;
    }

    public void setManageOrganizationId(String manageOrganizationId) {
        this.manageOrganizationId = StringUtils.hasText(manageOrganizationId)?manageOrganizationId:null;
    }

    public String getArchiveRoomNo() {
        return archiveRoomNo;
    }

    public void setArchiveRoomNo(String archiveRoomNo) {
        this.archiveRoomNo = StringUtils.hasText(archiveRoomNo)?archiveRoomNo:null;
    }
}
