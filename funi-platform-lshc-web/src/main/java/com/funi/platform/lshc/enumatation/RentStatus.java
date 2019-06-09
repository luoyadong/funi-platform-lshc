package com.funi.platform.lshc.enumatation;

/**
 * @author 3
 */
public enum RentStatus {

    N0RMAL(0),//正常
    RENTING(1);//签约

    private int status;

    RentStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
