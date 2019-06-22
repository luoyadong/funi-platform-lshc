package com.funi.platform.lshc.vo.census;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 普查信息校验结果对象
 * Created by sam on 2019/6/21.4:52 PM
 */
public class RegiInfoCheckResultVo {
    /** 是否通过校验，数据库中是否存在重复的普查信息*/
    private boolean pass;
    /** 未通过校验的条数，只有pass为false的时候该字段有效*/
    private int failedCount;
    /** 校验不通过（存在重复数据）的普查信息校验结果集合*/
    private List<RegiInfoCheckVo> failedCheckVoList;

    public RegiInfoCheckResultVo() {
    }

    public RegiInfoCheckResultVo(boolean pass) {
        this.pass = pass;
    }

    public RegiInfoCheckResultVo(RegiInfoCheckVo regiInfoCheckVo) {
        this.failedCount = 1;
        List<RegiInfoCheckVo> regiInfoCheckVoList = new ArrayList<>();
        regiInfoCheckVoList.add(regiInfoCheckVo);
        this.failedCheckVoList = regiInfoCheckVoList;
    }

    public RegiInfoCheckResultVo(List<RegiInfoCheckVo> failedCheckVoList) {
        if(CollectionUtils.isEmpty(failedCheckVoList)) {
            this.pass = true;
        } else {
            failedCount = failedCheckVoList.size();
            this.failedCheckVoList = failedCheckVoList;
        }
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public List<RegiInfoCheckVo> getFailedCheckVoList() {
        return failedCheckVoList;
    }

    public void setFailedCheckVoList(List<RegiInfoCheckVo> failedCheckVoList) {
        this.failedCheckVoList = failedCheckVoList;
    }
}
