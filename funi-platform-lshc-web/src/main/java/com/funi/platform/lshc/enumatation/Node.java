package com.funi.platform.lshc.enumatation;

/**
 * @author 3
 */
public enum Node {

    N001("审批"),
    N002("结束");

    private String name;

    Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
