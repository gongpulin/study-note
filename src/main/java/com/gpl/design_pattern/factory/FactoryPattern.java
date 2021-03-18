package com.gpl.design_pattern.factory;

/**
 * @author gongpulin
 * date 2021-02-05
 * 工厂模式属于创建型模式，它提供了一种创建对象的最佳方式，在工厂模式中，我们在创建对象时不会对客户端暴露创建逻辑，并且通过一个共同的接口来指向
 * 新创建的对象
 * 意图：定义一个创建对象的接口，让其子类自己决定实例化哪一个工厂类，工厂模式使其创建过程延迟到子类进行
 * 主要解决：接口选择的问题
 * 如何解决：让其子类实现工厂接口，返回的也是一个抽象的产品
 * 关键代码：创建过程在其子类进行。
 * 优点：1、一个调用者想创建一个对象，只要知道其名称就可以了。2、扩展性高，如果想增加一个产品，只要扩展一个工厂类就可以。3、屏蔽产品的具体
 *       实现，调用者只关心产品的接口
 * 缺点：每次增加一个产品时，都要增加一个具体类和对象实现工厂，使得系统中的类的个数成倍增加，在一定程度上增加了系统的复杂度，同时也增加了系统
 *       具体类的依赖
 * 注意事项：作为一种创建类模式，在任何需要生成复杂对象的地方，都可以使用工厂模式。有一点需要注意的是复杂对象适合使用工厂模式，而简单
 * 对象，特别是只需要通过new就可以完成创建的对象，无需使用工厂模式。如果使用工厂模式，就需要引入一个工厂类，会增加系统的复杂度
 *
 */
public class FactoryPattern {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape circle = shapeFactory.getShape("circle");
        circle.draw();
        //获取 Rectangle 的对象，并调用它的 draw 方法
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        //调用 Rectangle 的 draw 方法
        shape2.draw();

        //获取 Square 的对象，并调用它的 draw 方法
        Shape shape3 = shapeFactory.getShape("SQUARE");

        //调用 Square 的 draw 方法
        shape3.draw();
    }
}
