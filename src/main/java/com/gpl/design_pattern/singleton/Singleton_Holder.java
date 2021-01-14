package com.gpl.design_pattern.singleton;

/**
 * @author gongpulin
 * date 2021-01-12
 */
public class Singleton_Holder {
    private static class SingletonHolderClass {
        private static final Singleton_Holder INSTANCE = new Singleton_Holder();
    }
    private Singleton_Holder() {}
    public static Singleton_Holder getINstance() {
        return SingletonHolderClass.INSTANCE;
    }
}
