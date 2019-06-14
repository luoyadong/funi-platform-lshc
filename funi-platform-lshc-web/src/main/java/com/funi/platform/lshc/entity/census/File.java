package com.funi.platform.lshc.entity.census;

import com.funi.platform.lshc.entity.SuperEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 附件信息
 * @author sam
 * @date 2019-06-14 23:45:55
 */
public class File extends SuperEntity implements Serializable {
    /** 普查编号 */
    private String hcId;

    /** 产权证编号 */
    private String rightNo;

    /** 附件名称 */
    private String fileName;

    /** 附件大小 */
    private BigDecimal fileSize;

    /** 附件类型 */
    private String fileType;

    /** url */
    private String url;

    /** 上传时间 */
    private Date submitDate;

    /** 上传者账号 */
    private String userName;

    /** 单位名称 */
    private String unitName;

    private static final long serialVersionUID = 1L;

    public String getHcId() {
        return hcId;
    }

    public void setHcId(String hcId) {
        this.hcId = hcId;
    }

    public String getRightNo() {
        return rightNo;
    }

    public void setRightNo(String rightNo) {
        this.rightNo = rightNo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BigDecimal getFileSize() {
        return fileSize;
    }

    public void setFileSize(BigDecimal fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }
}