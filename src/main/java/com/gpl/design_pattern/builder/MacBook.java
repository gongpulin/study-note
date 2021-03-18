package com.gpl.design_pattern.builder;

/**
 * @author gongpulin
 * date 2021-02-03
 */
public class MacBook extends Computer {
    protected MacBook() {}

    @Override
    public void setOs() {
        mOs = "Mac OS X 12";
    }
}
