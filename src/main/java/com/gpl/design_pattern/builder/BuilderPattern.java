package com.gpl.design_pattern.builder;

/**
 *
 * https://www.runoob.com/w3cnote/builder-pattern.html
 * @author gongpulin
 * date 2021-02-02
 * 建造者模式使用多个简单的对象一步一步构建成一个复杂的对象。属于创建型模式，它提供了一种创建对象的最佳方式
 * 意图：将一个复杂的构建与其表示分离，使得同样的构建过程可以创建不同的表示。
 * 主要解决：主要解决在软件系统中，有时候面临一个复杂对象的创建工作，其通常由各个部分的子对象用一定的算法构成，由于需求的变化，这个复杂
 *          对象的各个部分经常面临着剧烈的变化，但是将他们组合在一起的算法却相对稳定
 * 何时使用：一些基本部件不会变，而组合经常变化
 * 如何解决：将变与不变分离开
 * 关键代码：建造者：创建和提供实例，导演：管理建造出来的实例的依赖关系
 * 应用实例：1、去肯德基，汉堡、可乐、薯条等是不变的，而其组合是经常变化的，生成所谓的套餐。2、Java中的StringBuilder
 * 优点：1、建造者独立，易扩展。2、便于控制细节风险
 * 缺点：1、产品必须有共同点，范围有限制。2、如内部变化复杂，会有很多建造者类
 * 使用场景：1、需要生成的对象具有复杂的内部结构。2、需要生成的对象内部属性本身相互依赖
 * 注意事项：与工厂模式的区别：建造者模式更加关注与零件装配的顺序
 *
 */
public class BuilderPattern {
    public static void main(String[] args) {
        Builder builder = new MacBookBuilder();
        Director pcDirector = new Director(builder);
        pcDirector.construct("英特尔主板","Retina显示器");
        Computer macBook = builder.build();
        System.out.println(macBook.toString());
    }
}




















