package com.funi.platform.lshc.vo.census;

/**
 * Created by sam on 2019/6/15.9:40 AM
 */
public class ListRegiInfoVo {
    /**数据库主键ID*/
    private String id;
    /** 房屋编号 */
    private String houseId;
    /** 单元 */
    private String unitNo;
    /** 楼层 */
    private String layer;
    /** 房屋号 */
    private String roomNo;
    /** 图片数量*/
    private int fileCount;

    //受理id
    private String jobAcceptId;
    //业务类型名称
    private String businessTypeName;
    //节点名称
    private String nodeName;
    //普查状态
    private String auditStatus;

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public String getLayer() {
        return layer;
    }

    public void setLayer(String layer) {
        this.layer = layer;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getJobAcceptId() {
        return jobAcceptId;
    }

    public void setJobAcceptId(String jobAcceptId) {
        this.jobAcceptId = jobAcceptId;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public void setBusinessTypeName(String businessTypeName) {
        this.businessTypeName = businessTypeName;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }
}
