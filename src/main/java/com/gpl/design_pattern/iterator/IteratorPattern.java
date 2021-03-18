package com.gpl.design_pattern.iterator;

import io.netty.resolver.NameResolver;

import javax.naming.Name;

/**
 * @author gongpulin
 * date 2021-02-04
 * 迭代器模式是Java和.NET编程环境中非常常用的设计模式。这种设计模式用于顺序访问集合对象的元素。不需要知道集合对象的底层表示
 * 迭代器模式属于行为型模式
 * 意图：提供一种顺序访问一个集合对象中各个元素，而又无须暴露该对象的内部表示
 * 主要解决：不同的方式来遍历整个集合对象
 * 何时使用：遍历一个集合
 * 如何解决：把在元素之间游走的责任交给迭代器，而不是集合
 * 关键代码：定义接口:hasNext , next
 * 应用实例：Java中的iterator
 *
 * 优点：1、支持以不同的方式遍历一个聚合对象。2、迭代器简化了集合。3、在同一个集合上可以有多个遍历。4、在迭代器模式中，增加新的集合和迭代器
 * 都很方便，无需修改原有代码
 * 缺点：由于迭代器模式将存储数据和遍历数据的职责分离，增加新的聚合类需要对应的增加新的迭代器类，类的个数成对增加，在一定程度上增加了系统的
 * 复杂性
 *
 * 使用场景：1、访问一个集合的内容而无须暴露它的内部表示。2、需要为集合对象提供多种不同的遍历方式。3、为遍历不同的集合提供一个统一的接口
 *
 * 注意事项：迭代器模式就是分离了集合对象的遍历行为，抽象出一个迭代器类来负责，这样既可以做到不暴露集合内部结构，又可以让外部代码透明的访问集合
 * 内部的数据
 *
 *
 */
public class IteratorPattern {
    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository();
        for (Iterator iter = nameRepository.getIterator(); iter.hasNext();) {
            String name = (String) iter.next();
            System.out.println("Name:"+name);
        }
    }
}



































