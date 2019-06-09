package com.funi.platform.lshc.enumatation;

/**
 * @author 3
 */
public enum InvalidReason {

    CANCEL("注销"),
    CHANGE("变更"),
    RENEW("续签");

    private String message;

    InvalidReason(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
