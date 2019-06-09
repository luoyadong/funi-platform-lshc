package com.funi.platform.lshc.entity.house;

import com.funi.platform.lshc.entity.BaseEntity;
import com.funi.platform.lshc.enumatation.IsChange;
import com.funi.platform.lshc.vo.AddressEditVo;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 地址信息表
 * @author sam
 * @date 2018-11-07 22:57:10
 */
public class AddrInfo extends BaseEntity implements Serializable {
    /** 序号 */
    private Integer serialNo;

    /** 小区名称 */
    private String livingAreaName;

    /** 区域ID */
    private String zoneId;

    /** 地址 */
    private String addressDetail;

    /** 街道（路/巷） */
    private String streetName;

    /** 号 */
    private String streetNo;

    /** 坐标X，经度 */
    private String positionX;

    /** 坐标Y，纬度 */
    private String positionY;

    /** 备注 */
    private String addrRemark;

    /** 是否变更，默认未变更 */
    private Integer isChange = IsChange.NOT_CHANGED.getChangeStatus();
    /** 创建者所属机构编码*/
    private String bizCreateOrgCode;

    public AddrInfo() {
    }

    public AddrInfo(AddressEditVo addressEditVo) {
        super.setId(addressEditVo.getId());
        this.zoneId = addressEditVo.getZoneId();
        this.streetName = addressEditVo.getStreetName();
        this.streetNo = addressEditVo.getStreetNo();
        this.positionX = addressEditVo.getPositionX();
        this.positionY = addressEditVo.getPositionY();
        this.addrRemark = addressEditVo.getAddrRemark();
    }

    /**
     * 将街道和门牌号拼接成完整地址
     * @param addrInfo
     * @return
     */
    public String getCompleteAddress(AddrInfo addrInfo) {
        StringBuilder addressBuilder = new StringBuilder();
        addressBuilder.append(getStreetName());
        addressBuilder.append(getStreetNo());
        return addressBuilder.toString();
    }

    public String getBizCreateOrgCode() {
        return bizCreateOrgCode;
    }

    public void setBizCreateOrgCode(String bizCreateOrgCode) {
        this.bizCreateOrgCode = bizCreateOrgCode;
    }

    private static final long serialVersionUID = 1L;

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }

    public String getLivingAreaName() {
        return livingAreaName;
    }

    public void setLivingAreaName(String livingAreaName) {
        this.livingAreaName = livingAreaName;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getAddressDetail() {
        StringBuilder addressDetailBuilder = new StringBuilder();
        String streetName = getStreetName();
        if(StringUtils.isNotBlank(streetName)) {
            addressDetailBuilder.append(streetName);
        }
        String streetNo = getStreetNo();
        if(StringUtils.isNotBlank(streetNo)) {
            addressDetailBuilder.append(streetNo);
        }
        return addressDetailBuilder.toString();
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
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

    public String getAddrRemark() {
        return addrRemark;
    }

    public void setAddrRemark(String addrRemark) {
        this.addrRemark = addrRemark;
    }

    public Integer getIsChange() {
        return isChange;
    }

    public void setIsChange(Integer isChange) {
        this.isChange = isChange;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AddrInfo other = (AddrInfo) that;
        return (this.getZoneId() == null ? other.getZoneId() == null : this.getZoneId().equals(other.getZoneId()))
            && (this.getStreetName() == null ? other.getStreetName() == null : this.getStreetName().equals(other.getStreetName()))
            && (this.getStreetNo() == null ? other.getStreetNo() == null : this.getStreetNo().equals(other.getStreetNo()))
            && (this.getPositionX() == null ? other.getPositionX() == null : this.getPositionX().equals(other.getPositionX()))
            && (this.getPositionY() == null ? other.getPositionY() == null : this.getPositionY().equals(other.getPositionY()))
            && (this.getAddrRemark() == null ? other.getAddrRemark() == null : this.getAddrRemark().equals(other.getAddrRemark()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", serialNo=").append(serialNo);
        sb.append(", livingAreaName=").append(livingAreaName);
        sb.append(", zoneId=").append(zoneId);
        sb.append(", addressDetail=").append(addressDetail);
        sb.append(", streetName=").append(streetName);
        sb.append(", streetNo=").append(streetNo);
        sb.append(", positionX=").append(positionX);
        sb.append(", positionY=").append(positionY);
        sb.append(", addrRemark=").append(addrRemark);
        sb.append(", isChange=").append(isChange);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}