package com.funi.platform.lshc.entity.census;

import com.funi.platform.lshc.entity.SuperEntity;
import java.io.Serializable;

/**
 * 人口信息
 * @author sam
 * @date 2019-06-14 23:46:48
 */
public class EntInfo extends SuperEntity implements Serializable {
    /** 普查编号 */
    private String hcId;

    /** 姓名 */
    private String entName;

    /** 性别 */
    private String sex;

    /** 人员类别 */
    private String entType;

    /** 民族 */
    private String entNation;

    /** 籍贯 */
    private String entNative;

    /** 联系电话 */
    private String tel;

    /** 婚姻状态 */
    private String marriageStatus;

    /** 证件类型 */
    private String idType;

    /** 证件号码 */
    private String idNo;

    /** 职业 */
    private String career;

    private static final long serialVersionUID = 1L;

    public String getHcId() {
        return hcId;
    }

    public void setHcId(String hcId) {
        this.hcId = hcId;
    }

    public String getEntName() {
        return entName;
    }

    public void setEntName(String entName) {
        this.entName = entName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEntType() {
        return entType;
    }

    public void setEntType(String entType) {
        this.entType = entType;
    }

    public String getEntNation() {
        return entNation;
    }

    public void setEntNation(String entNation) {
        this.entNation = entNation;
    }

    public String getEntNative() {
        return entNative;
    }

    public void setEntNative(String entNative) {
        this.entNative = entNative;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }
}