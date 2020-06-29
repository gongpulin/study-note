package com.gpl.flink.hotitem;

import org.apache.flink.api.common.functions.AggregateFunction;

/**
 * @author gongpulin
 * date 2020-06-23
 */
public class CountAgg implements AggregateFunction<UserBehavior, Long, Long> {

    @Override
    public Long createAccumulator() {
        return 0L;
    }

    @Override
    public Long add(UserBehavior userBehavior, Long acc) {
        return acc + 1;
    }

    @Override
    public Long getResult(Long acc) {
        return acc;
    }

    @Override
    public Long merge(Long acc1, Long acc2) {
        return acc1 + acc2;
    }

}
