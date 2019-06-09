package com.funi.platform.lshc.entity.charge;

import com.funi.platform.lshc.entity.BaseEntity;
import com.funi.platform.lshc.vo.ChargeAddressVo;
import com.funi.platform.lshc.vo.ChargeDetailVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 收费记录管理表
 * @author sam
 * @date 2018-11-29 22:38:10
 */
public class ArrearageHis extends BaseEntity implements Serializable {
    /** 合同ID */
    private String contrId;

    /** 承租方 */
    private String lesseeName;

    /** 房屋地址集合 */
    private String houseAddress;

    /** 承租房屋套数 */
    private Integer houseCount;

    /** 租金交至日期，租金到期日期 */
    private String rentDueDate;

    /** 滞纳金终止年月日 */
    private String rentStartDate;

    /** 滞纳金金额 */
    private String rentEndDate;

    /** 应收金额 */
    private BigDecimal rentAmount;

    public ArrearageHis(ChargeDetailVo chargeDetailVo) {
        this.contrId = chargeDetailVo.getContractId();
        this.lesseeName = chargeDetailVo.getLesseeName();
        List<ChargeAddressVo> chargeAddressVoList = chargeDetailVo.getChargeAddressVoList();
        if(CollectionUtils.isNotEmpty(chargeAddressVoList)) {
            // 计算合同房屋套数
            this.houseCount = chargeAddressVoList.size();
            List<String> addressDetailList = new ArrayList<>();
            for(ChargeAddressVo chargeAddressVo : chargeAddressVoList) {
                addressDetailList.add(chargeAddressVo.getAddressDetail());
            }
            // 拼接小区地址
            this.houseAddress = StringUtils.join(addressDetailList, " ， ");
        }
        this.rentDueDate = chargeDetailVo.getRentalPaytoDate();
        this.rentStartDate = chargeDetailVo.getThisRentDate();
        this.rentEndDate = chargeDetailVo.getNextRentDate();
        this.rentAmount = chargeDetailVo.getDefaultRentAmount();
    }

    /**
     * 根据收费记录对象，给指定的欠费记录赋值
     * @param chargeDetailVo
     * @param arrearageHis
     */
    public void build(ChargeDetailVo chargeDetailVo, ArrearageHis arrearageHis) {
        arrearageHis.setLesseeName(chargeDetailVo.getLesseeName());
        List<ChargeAddressVo> chargeAddressVoList = chargeDetailVo.getChargeAddressVoList();
        if(CollectionUtils.isNotEmpty(chargeAddressVoList)) {
            arrearageHis.setHouseCount(chargeAddressVoList.size());
            List<String> addressDetailList = new ArrayList<>();
            for(ChargeAddressVo chargeAddressVo : chargeAddressVoList) {
                addressDetailList.add(chargeAddressVo.getAddressDetail());
            }
            arrearageHis.setHouseAddress(StringUtils.join(addressDetailList, " ， "));
        }
        arrearageHis.setRentDueDate(chargeDetailVo.getRentalPaytoDate());
        arrearageHis.setRentStartDate(chargeDetailVo.getThisRentDate());
        arrearageHis.setRentEndDate(chargeDetailVo.getNextRentDate());
        arrearageHis.setRentAmount(chargeDetailVo.getDefaultRentAmount());
        arrearageHis.setSysUpdateTime(new Date());
    }

    public ArrearageHis() {
    }

    private static final long serialVersionUID = 1L;

    public String getContrId() {
        return contrId;
    }

    public void setContrId(String contrId) {
        this.contrId = contrId;
    }

    public String getLesseeName() {
        return lesseeName;
    }

    public void setLesseeName(String lesseeName) {
        this.lesseeName = lesseeName;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public Integer getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(Integer houseCount) {
        this.houseCount = houseCount;
    }

    public String getRentDueDate() {
        return rentDueDate;
    }

    public void setRentDueDate(String rentDueDate) {
        this.rentDueDate = rentDueDate;
    }

    public String getRentStartDate() {
        return rentStartDate;
    }

    public void setRentStartDate(String rentStartDate) {
        this.rentStartDate = rentStartDate;
    }

    public String getRentEndDate() {
        return rentEndDate;
    }

    public void setRentEndDate(String rentEndDate) {
        this.rentEndDate = rentEndDate;
    }

    public BigDecimal getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(BigDecimal rentAmount) {
        this.rentAmount = rentAmount;
    }
}