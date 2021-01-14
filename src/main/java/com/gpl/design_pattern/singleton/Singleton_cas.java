package com.gpl.design_pattern.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author gongpulin
 * date 2021-01-12
 * https://www.jianshu.com/p/f3fae8658f13
 */
public class Singleton_cas {
    private static final AtomicReference<Singleton_cas> INSTANCE = new AtomicReference();
    private Singleton_cas() {}
    public static Singleton_cas getInstance() {
        for(;;) {
            Singleton_cas singleton = INSTANCE.get();
            if (null != singleton) {
                return singleton;
            }
            singleton = new Singleton_cas();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}

































