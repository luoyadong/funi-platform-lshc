package com.funi.platform.lshc.vo.census;

/**
 * Created by sam on 2019/6/21.4:58 PM
 */
public class RegiInfoCheckVo {
    /** 该条普查信息所在Excel中的行号，只有批量导入的时候才有效*/
    private int rowNo;
    /** 数据库已存在的数据条数*/
    private int repeatCount;
    /** 普查信息摘要*/
    private String RegiInfoDesc;

    public RegiInfoCheckVo() {
    }

    public RegiInfoCheckVo(int rowNo, int repeatCount, String regiInfoDesc) {
        this.rowNo = rowNo;
        this.repeatCount = repeatCount;
        RegiInfoDesc = regiInfoDesc;
    }

    public RegiInfoCheckVo(int repeatCount, String regiInfoDesc) {
        this.repeatCount = repeatCount;
        RegiInfoDesc = regiInfoDesc;
    }

    public int getRowNo() {
        return rowNo;
    }

    public void setRowNo(int rowNo) {
        this.rowNo = rowNo;
    }

    public int getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(int repeatCount) {
        this.repeatCount = repeatCount;
    }

    public String getRegiInfoDesc() {
        return RegiInfoDesc;
    }

    public void setRegiInfoDesc(String regiInfoDesc) {
        RegiInfoDesc = regiInfoDesc;
    }
}
