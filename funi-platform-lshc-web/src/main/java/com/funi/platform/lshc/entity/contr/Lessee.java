package com.funi.platform.lshc.entity.contr;

import com.funi.platform.lshc.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * 租户
 * @author 3
 */
public class Lessee extends BaseEntity {

    /**
     * 租户名称
     */
    private String lesseeName;

    /**
     * 身份证号码
     */
    private String uniqueId;

    /**
     * 单位代码
     */
    private String organizationId;

    /**
     * 机构名称(单位)
     */
    private String organizationName;

    /**
     * 联系人(单位)
     */
    private String organizationContract;

    /**
     * 联系电话(单位)
     */
    private String organizationTel;

    /**
     * 联系人身份证号(单位)
     */
    private String organizationIdNo;

    /**
     * 联系地址(单位)
     */
    private String organizationAddress;

    /**
     * 性别(个人)
     */
    private String personalSex;

    /**
     * 联系电话(个人)
     */
    private String personalTel;

    /**
     * 职业(个人)
     */
    private String personalCareer;

    /**
     * 工作单位(个人)
     */
    private String personalWorkUnit;

    /**
     * 个人年收入(个人)
     */
    private BigDecimal personalAnnualIncome;

    /**
     * 低保标识(个人)
     */
    private String subsistenceAllowancesFlg;

    /**
     * 户籍地址(个人)
     */
    private String personalPermanentAddress;

    /**
     * 承租人(单位)
     */
    private String organizationLesseeName;

    /**
     * 承租人身份证号码(单位)
     */
    private String organizationLesseeIdNo;

    /**
     * 承租人电话号码(单位)
     */
    private String organizationLesseeTel;
    //承租人性质，1表示个人 2表示集体
    private String rentorType;

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName;
    }

    public String getOrganizationAddress() {
        return organizationAddress;
    }

    public void setOrganizationAddress(String organizationAddress) {
        this.organizationAddress = organizationAddress;
    }

    public String getOrganizationContract() {
        return organizationContract;
    }

    public void setOrganizationContract(String organizationContract) {
        this.organizationContract = organizationContract;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationIdNo() {
        return organizationIdNo;
    }

    public void setOrganizationIdNo(String organizationIdNo) {
        this.organizationIdNo = organizationIdNo;
    }

    public String getOrganizationLesseeIdNo() {
        return organizationLesseeIdNo;
    }

    public void setOrganizationLesseeIdNo(String organizationLesseeIdNo) {
        this.organizationLesseeIdNo = organizationLesseeIdNo;
    }

    public String getOrganizationLesseeName() {
        return organizationLesseeName;
    }

    public void setOrganizationLesseeName(String organizationLesseeName) {
        this.organizationLesseeName = organizationLesseeName;
    }

    public String getOrganizationLesseeTel() {
        return organizationLesseeTel;
    }

    public void setOrganizationLesseeTel(String organizationLesseeTel) {
        this.organizationLesseeTel = organizationLesseeTel;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationTel() {
        return organizationTel;
    }

    public void setOrganizationTel(String organizationTel) {
        this.organizationTel = organizationTel;
    }

    public BigDecimal getPersonalAnnualIncome() {
        return personalAnnualIncome;
    }

    public void setPersonalAnnualIncome(BigDecimal personalAnnualIncome) {
        this.personalAnnualIncome = personalAnnualIncome;
    }

    public String getPersonalCareer() {
        return personalCareer;
    }

    public void setPersonalCareer(String personalCareer) {
        this.personalCareer = personalCareer;
    }

    public String getPersonalPermanentAddress() {
        return personalPermanentAddress;
    }

    public void setPersonalPermanentAddress(String personalPermanentAddress) {
        this.personalPermanentAddress = personalPermanentAddress;
    }

    public String getPersonalSex() {
        return personalSex;
    }

    public void setPersonalSex(String personalSex) {
        this.personalSex = personalSex;
    }

    public String getPersonalTel() {
        return personalTel;
    }

    public void setPersonalTel(String personalTel) {
        this.personalTel = personalTel;
    }

    public String getPersonalWorkUnit() {
        return personalWorkUnit;
    }

    public void setPersonalWorkUnit(String personalWorkUnit) {
        this.personalWorkUnit = personalWorkUnit;
    }

    public String getSubsistenceAllowancesFlg() {
        return subsistenceAllowancesFlg;
    }

    public void setSubsistenceAllowancesFlg(String subsistenceAllowancesFlg) {
        this.subsistenceAllowancesFlg = subsistenceAllowancesFlg;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getRentorType() {
        return rentorType;
    }

    public void setRentorType(String rentorType) {
        this.rentorType = rentorType;
    }
}