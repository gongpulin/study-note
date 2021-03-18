package com.gpl.design_pattern.decorator;

/**
 * @author gongpulin
 * date 2021-02-04
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
