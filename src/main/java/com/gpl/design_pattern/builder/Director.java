package com.gpl.design_pattern.builder;

/**
 * @author gongpulin
 * date 2021-02-03
 */
public class Director {
    Builder mBuilder = null;
    public Director(Builder builder) {
        this.mBuilder = builder;
    }

    public void construct(String board, String display) {
        mBuilder.buildBoard(board);
        mBuilder.buildDisplay(display);
        mBuilder.buildOs();
    }
}
