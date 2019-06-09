package com.funi.platform.lshc.vo;

/**
 * 用于生成票据后，给打印服务传递参数
 * Created by sam on 2018/12/1.4:25 PM
 */
public class BillVo {
    /** 收费记录ID*/
    private String chargeHistoryId;
    /** 模板名称，用于调用打印服务*/
    private String billTempletName;

    public BillVo(String chargeHistoryId, String billTempletName) {
        this.chargeHistoryId = chargeHistoryId;
        this.billTempletName = billTempletName;
    }

    public BillVo() {
    }

    public String getChargeHistoryId() {
        return chargeHistoryId;
    }

    public void setChargeHistoryId(String chargeHistoryId) {
        this.chargeHistoryId = chargeHistoryId;
    }

    public String getBillTempletName() {
        return billTempletName;
    }

    public void setBillTempletName(String billTempletName) {
        this.billTempletName = billTempletName;
    }
}
