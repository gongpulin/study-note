package com.gpl.design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gongpulin
 * date 2021-02-02
 * 观察者模式：当对象存在一对多关系时，使用观察者模式，比如当一个对象被修改时，则会自动通知依赖它的对象。观察者模式属于行为型模式
 *
 * 意图：定义对象间一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖它的对象都得到通知并自动更新
 * 主要解决：一个对象状态改变给其它对象通知的问题，而且要考虑到易用和低耦合，保证高度的协作
 * 何时使用：一个对象的状态发生改变，所有依赖对象（观察者）都将得到通知，进行广播通知
 * 关键代码：在抽象类里面有一个ArrayList存放观察者
 *
 * 优点：1、观察者和被观察者是抽象耦合的。2、建立一套触发机制
 * 缺点：1、如果一个被观察者对象有很多的直接和间接的观察者，将所有的观察者都通知到会花费很多时间。2、如果在观察者和观察目标之间
 *       有循环依赖的话，观察目标会触发他们之间进行循环调用，可能导致系统崩溃。3、观察者模式没有相应的机制让观察者知道所观察的目标对象
 *       是怎么发生变化的，而仅仅只是知道观察目标发生了变化。
 *
 *
 *  使用场景：一个抽象模型有两个方面，其中一个方面依赖于另一个方面。将这些方面封装在独立的对象中使他们可以各自独立的改变和复用
 *           一个对象的改变将导致其他一个或多个对象也发生改变，而不知道具体有多少对象发生改变，可以降低对象之间的耦合度
 *           一个对象必须通知其他对象，而并不知道这些对象是谁
 *
 */
public class ObserverPattern {
    static class Subject {
        private List<Observer> observers = new ArrayList();
        private int state;
        public int getState() {
            return state;
        }
        public void setState(int state) {
            this.state = state;
            notifyAllObservers();
        }
        public void attach(Observer observer) {
            observers.add(observer);
        }
        public void notifyAllObservers() {
            for (Observer observer : observers) {
                observer.update();
            }
        }
    }
    abstract class Observer {
        protected Subject subject;
        public abstract void update();
    }

    class ObserverOne extends Observer {
        public ObserverOne(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }
        @Override
        public void update() {
            System.out.println("ObserverOne update");
        }
    }

    class ObserverTwo extends Observer {
        public ObserverTwo(Subject subject) {
            this.subject = subject;
            this.subject.attach(this);
        }
         @Override
        public void update() {
            System.out.println("ObserverTwo update");
         }
    }


    public static void main(String[] args) {
        Subject subject = new Subject();

    }
}













