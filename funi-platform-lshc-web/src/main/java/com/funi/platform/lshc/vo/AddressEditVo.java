package com.funi.platform.lshc.vo;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 小区地址编辑
 * Created by sam on 2018/11/7.9:08 PM
 */
public class AddressEditVo {
    /** 地址主键ID*/
    private String id;
    /** 区域ID */
    private String zoneId;
    /** 街道（路/巷） */
    private String streetName;
    /** 号 */
    private String streetNo;
    /** 备注 */
    private String addrRemark;
    /** 坐标X */
    private String positionX;
    /** 坐标Y */
    private String positionY;
    /** 图片路径集合*/
    private String fileUrlStr;
    /** 要件集合*/
    private List<LinkFileVo> linkFileVoList;

    public String getFileUrlStr() {
        return fileUrlStr;
    }

    public void setFileUrlStr(String fileUrlStr) {
        this.fileUrlStr = fileUrlStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNo() {
        return streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getAddrRemark() {
        return addrRemark;
    }

    public void setAddrRemark(String addrRemark) {
        this.addrRemark = addrRemark;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    public List<LinkFileVo> getLinkFileVoList() {
        String fileUrlStr = getFileUrlStr();
        if(StringUtils.isNotBlank(fileUrlStr)) {
            return JSONArray.parseArray(fileUrlStr,LinkFileVo.class);
        }
        return linkFileVoList;
    }

    public void setLinkFileVoList(List<LinkFileVo> linkFileVoList) {
        this.linkFileVoList = linkFileVoList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
