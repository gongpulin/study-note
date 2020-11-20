package com.gpl.flink.window.aggregate;

import org.apache.flink.api.common.functions.AggregateFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;

/**
 * @author gongpulin
 * date 2020-09-23
 */

/**
 * 输入数据类型IN,输出类型OUT,中间状态ACC,这样复杂的设计主要目的是为了解决输入数据类型、中间状态和输出类型不一致的问题。同时ACC可以自定义，
 * 我们可以在ACC里面构建我们想要的数据结构。比如我们要计算一个窗口内某个字段的平均值，那么ACC中要保存总和及个数，下面是一个求平均值的实例
 */
public class AverageAggregate implements AggregateFunction<StockPrice, Tuple3<String, Double, Integer>, Tuple2<String, Double>> {

    /**
     * 在一次新的aggregate发起时，创建一个新的accumulator,accumulator是中间状态，简称ACC
     * @return
     */
    @Override
    public Tuple3<String, Double, Integer> createAccumulator() {
        return new Tuple3("", 0, 0);
    }

    /**
     * 当一个新元素流入时，将新元素与中间状态数据ACC合并，返回新的中间状态ACC
     * @param value
     * @param accumulator
     * @return
     */
    @Override
    public Tuple3<String, Double, Integer> add(StockPrice value, Tuple3<String, Double, Integer> accumulator) {
        return new Tuple3(value.symbol, accumulator.f1 + value.price, accumulator.f2 + 1);
    }

    /**
     * 将中间状态数据转化为结果数据
     * @param accumulator
     * @return
     */
    @Override
    public Tuple2<String, Double> getResult(Tuple3<String, Double, Integer> accumulator) {
        return new Tuple2(accumulator.f0, accumulator.f1 / accumulator.f2);
    }

    /**
     * 合并两个ACC
     * @param a
     * @param b
     * @return
     */
    @Override
    public Tuple3<String, Double, Integer> merge(Tuple3<String, Double, Integer> a, Tuple3<String, Double, Integer> b) {
        return new Tuple3(a.f0, a.f1 + b.f1, a.f2 + b.f2);
    }
}
