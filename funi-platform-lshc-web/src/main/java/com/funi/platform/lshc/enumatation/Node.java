package com.funi.platform.lshc.enumatation;

/**
 * @author 3
 */
public enum Node {

    N001("初审"),
    N002("复审"),
    N003("审核"),
    N004("结束");

    private String name;

    Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
