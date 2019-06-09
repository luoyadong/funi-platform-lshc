package com.funi.platform.lshc.entity.house;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * GHOUSE_HOUSE_ARCHIVES
 * @author 
 */
public class HouseArchives implements Serializable {
    private String id;

    private String houseId;

    private String houseArchivesNo;

    private String fileQuantity;

    private String saveArchivesId;

    private String saveFileId;

    private String saveNo;

    private String saveDate;

    private String archivesRemark;

    private BigDecimal isDeleted;

    private BigDecimal version;

    private Date sysCreateTime;

    private Date sysUpdateTime;

    private Date sysDeleteTime;

    private String sysCreateId;

    private String sysUpdateId;

    private String sysDeleteId;

    private static final long serialVersionUID = 1L;

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

    public String getHouseArchivesNo() {
        return houseArchivesNo;
    }

    public void setHouseArchivesNo(String houseArchivesNo) {
        this.houseArchivesNo = houseArchivesNo;
    }

    public String getFileQuantity() {
        return fileQuantity;
    }

    public void setFileQuantity(String fileQuantity) {
        this.fileQuantity = fileQuantity;
    }

    public String getSaveArchivesId() {
        return saveArchivesId;
    }

    public void setSaveArchivesId(String saveArchivesId) {
        this.saveArchivesId = saveArchivesId;
    }

    public String getSaveFileId() {
        return saveFileId;
    }

    public void setSaveFileId(String saveFileId) {
        this.saveFileId = saveFileId;
    }

    public String getSaveNo() {
        return saveNo;
    }

    public void setSaveNo(String saveNo) {
        this.saveNo = saveNo;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }

    public String getArchivesRemark() {
        return archivesRemark;
    }

    public void setArchivesRemark(String archivesRemark) {
        this.archivesRemark = archivesRemark;
    }

    public BigDecimal getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(BigDecimal isDeleted) {
        this.isDeleted = isDeleted;
    }

    public BigDecimal getVersion() {
        return version;
    }

    public void setVersion(BigDecimal version) {
        this.version = version;
    }

    public Date getSysCreateTime() {
        return sysCreateTime;
    }

    public void setSysCreateTime(Date sysCreateTime) {
        this.sysCreateTime = sysCreateTime;
    }

    public Date getSysUpdateTime() {
        return sysUpdateTime;
    }

    public void setSysUpdateTime(Date sysUpdateTime) {
        this.sysUpdateTime = sysUpdateTime;
    }

    public Date getSysDeleteTime() {
        return sysDeleteTime;
    }

    public void setSysDeleteTime(Date sysDeleteTime) {
        this.sysDeleteTime = sysDeleteTime;
    }

    public String getSysCreateId() {
        return sysCreateId;
    }

    public void setSysCreateId(String sysCreateId) {
        this.sysCreateId = sysCreateId;
    }

    public String getSysUpdateId() {
        return sysUpdateId;
    }

    public void setSysUpdateId(String sysUpdateId) {
        this.sysUpdateId = sysUpdateId;
    }

    public String getSysDeleteId() {
        return sysDeleteId;
    }

    public void setSysDeleteId(String sysDeleteId) {
        this.sysDeleteId = sysDeleteId;
    }
}