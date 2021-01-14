package com.gpl.design_pattern.singleton;

/**
 * @author gongpulin
 * date 2021-01-12
 */
public class Singleton_volatile {
    private volatile static Singleton_volatile INSTANCE;
    private Singleton_volatile() {}
    public static Singleton_volatile getInstance() {
        if (INSTANCE == null) {
            synchronized(Singleton_volatile.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Singleton_volatile();
                }
            }
        }
        return INSTANCE;
    }
}
