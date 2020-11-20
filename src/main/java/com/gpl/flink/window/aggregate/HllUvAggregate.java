package com.gpl.flink.window.aggregate;

import net.agkn.hll.HLL;
import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;

/**
 * @author gongpulin
 * date 2020-09-23
 */
public class HllUvAggregate implements AggregateFunction<Tuple2<String, Long>, HLL, Long> {

    @Override
    public HLL createAccumulator() {
        return new HLL(14, 5);
    }

    /**
     * addRaw方法用于向HyperLogLog中插入元素，如果插入的元素非数值型的需要hash过后才能插入
     * @param value
     * @param accumulator
     * @return
     */
    @Override
    public HLL add(Tuple2<String, Long> value, HLL accumulator) {
        accumulator.addRaw(value.f1);
        return accumulator;
    }

    /**
     * accumulator.cardinality()方法用于计算HyperLogLog中元素的基数
     * @param accumulator
     * @return
     */
    @Override
    public Long getResult(HLL accumulator) {
        long cardinality = accumulator.cardinality();
        return cardinality;
    }

    @Override
    public HLL merge(HLL a, HLL b) {
        a.union(b);
        return a;
    }
}
