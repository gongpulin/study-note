package com.gpl.design_pattern.facade;

/**
 * @author gongpulin
 * date 2021-02-04
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Rectangle: draw");
    }
}
