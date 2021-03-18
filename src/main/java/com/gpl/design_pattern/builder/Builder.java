package com.gpl.design_pattern.builder;

/**
 * @author gongpulin
 * date 2021-02-03
 */
public abstract class Builder {
    abstract void buildBoard(String board);
    abstract void buildDisplay(String display);
    abstract void buildOs();
    abstract Computer build();
}
