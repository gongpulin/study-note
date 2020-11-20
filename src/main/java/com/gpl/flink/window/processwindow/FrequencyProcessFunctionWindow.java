package com.gpl.flink.window.processwindow;

import com.gpl.flink.window.aggregate.StockPrice;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

import java.util.*;

/**
 * @author gongpulin
 * date 2020-09-24
 */

/**
 * ProcessWindowFunction全局窗口 对价格出现的次数做了统计
 */
public class FrequencyProcessFunctionWindow extends ProcessWindowFunction<StockPrice, Tuple2<String, Double>, String, TimeWindow> {


    @Override
    public void process(String s, Context context, Iterable<StockPrice> elements, Collector<Tuple2<String, Double>> out) throws Exception{
        Map<Double, Integer> countMap = new HashMap<>();
        for (StockPrice element : elements) {
            countMap.put(element.price, countMap.getOrDefault(element.price, 0)+1);
        }
        List<Map.Entry<Double,Integer>> list = new ArrayList<Map.Entry<Double, Integer>>(countMap.entrySet());
        list.sort(new Comparator<Map.Entry<Double, Integer>>() {
            @Override
            public int compare(Map.Entry<Double, Integer> o1, Map.Entry<Double, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        Collections.sort(list, new Comparator<Map.Entry<Double, Integer>>() {
            @Override
            public int compare(Map.Entry<Double, Integer> o1, Map.Entry<Double, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

    }




}
























