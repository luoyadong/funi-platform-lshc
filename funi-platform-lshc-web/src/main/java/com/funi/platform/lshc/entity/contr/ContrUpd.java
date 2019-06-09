package com.funi.platform.lshc.entity.contr;


import com.funi.platform.lshc.entity.BaseEntity;

/**
 * 合同更新信息
 * @author 3
 */
public class ContrUpd extends BaseEntity{

    /**
     * 更新前合同编号
     */
    private String contractNoBef;

    /**
     * 更新后合同编号
     */
    private String contractNoAft;

    /**
     * 更新原因
     */
    private String updateReason;

    /**
     * 更新批准日期
     */
    private String updateReviewDate;


    public String getContractNoAft() {
        return contractNoAft;
    }

    public void setContractNoAft(String contractNoAft) {
        this.contractNoAft = contractNoAft;
    }

    public String getContractNoBef() {
        return contractNoBef;
    }

    public void setContractNoBef(String contractNoBef) {
        this.contractNoBef = contractNoBef;
    }

    public String getUpdateReason() {
        return updateReason;
    }

    public void setUpdateReason(String updateReason) {
        this.updateReason = updateReason;
    }

    public String getUpdateReviewDate() {
        return updateReviewDate;
    }

    public void setUpdateReviewDate(String updateReviewDate) {
        this.updateReviewDate = updateReviewDate;
    }
}