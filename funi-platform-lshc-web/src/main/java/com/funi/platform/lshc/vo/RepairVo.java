package com.funi.platform.lshc.vo;

import com.funi.platform.lshc.entity.repair.Repair;

public class RepairVo extends Repair {
    private String houseNoSys;
    private String houseAddress;
    private String renter;

    public String getRenter() {
        return renter;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }

    public String getHouseNoSys() {
        return houseNoSys;
    }

    public void setHouseNoSys(String houseNoSys) {
        this.houseNoSys = houseNoSys;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }
}
