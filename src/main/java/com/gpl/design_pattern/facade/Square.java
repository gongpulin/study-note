package com.gpl.design_pattern.facade;

/**
 * @author gongpulin
 * date 2021-02-04
 */
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Square:draw");
    }
}
