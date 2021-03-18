package com.gpl.design_pattern.decorator;

/**
 * @author gongpulin
 * date 2021-02-04
 */
public class RedShapeDecorator extends ShapeDecorator {
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBoard(decoratedShape);
    }

    private void setRedBoard(Shape decoratedShape) {
        System.out.println("Border Color: Red");
    }
}
