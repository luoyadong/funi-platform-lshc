package com.funi.platform.lshc.entity.house;

import com.funi.platform.lshc.entity.BaseEntity;

import java.io.Serializable;

/**
 * 房屋产权土地信息表
 * @author sam
 * @date 2018-11-22 14:37:35
 */
public class RightEnt extends BaseEntity implements Serializable {
    /** 房屋ID */
    private String houseId;

    /** 产权权字号 */
    private String propertyNo;

    /** 产权证书编号 */
    private String propertyCertificateNo;

    /** 产权人姓名 */
    private String propertyUserName;

    /** 产权人身份证号 */
    private String propertyUserIdNumber;

    /** 产权人联系电话 */
    private String propertyUserTel;

    private static final long serialVersionUID = 1L;

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getPropertyNo() {
        return propertyNo;
    }

    public void setPropertyNo(String propertyNo) {
        this.propertyNo = propertyNo;
    }

    public String getPropertyCertificateNo() {
        return propertyCertificateNo;
    }

    public void setPropertyCertificateNo(String propertyCertificateNo) {
        this.propertyCertificateNo = propertyCertificateNo;
    }

    public String getPropertyUserName() {
        return propertyUserName;
    }

    public void setPropertyUserName(String propertyUserName) {
        this.propertyUserName = propertyUserName;
    }

    public String getPropertyUserIdNumber() {
        return propertyUserIdNumber;
    }

    public void setPropertyUserIdNumber(String propertyUserIdNumber) {
        this.propertyUserIdNumber = propertyUserIdNumber;
    }

    public String getPropertyUserTel() {
        return propertyUserTel;
    }

    public void setPropertyUserTel(String propertyUserTel) {
        this.propertyUserTel = propertyUserTel;
    }
}