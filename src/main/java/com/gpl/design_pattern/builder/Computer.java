package com.gpl.design_pattern.builder;

/**
 * @author gongpulin
 * date 2021-02-03
 */
public abstract class Computer {
    protected String mBoard;
    protected String mDisplay;
    protected String mOs;

    protected Computer() {}

    public void setBoard(String board) {
        this.mBoard = board;
    }
    public void setDisplay(String display) {
        this.mDisplay = display;
    }
    public abstract void setOs();

    @Override
    public String toString() {
        return "Computer{" +
                "mBoard='" + mBoard + '\'' +
                ",mDisplay='" + mDisplay + '\'' +
                ",mOs='" + mOs + '\'' +
                '}';
    }
}
