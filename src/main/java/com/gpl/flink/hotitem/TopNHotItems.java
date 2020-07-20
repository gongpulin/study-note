package com.gpl.flink.hotitem;

import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author gongpulin
 * date 2020-06-23
 */
public class TopNHotItems extends KeyedProcessFunction<Tuple, ItemViewCount, String> {

    private final int topSize;
    public TopNHotItems(int topSize) {
        this.topSize = topSize;
    }

    private ListState<ItemViewCount> itemState;

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        ListStateDescriptor<ItemViewCount> itemViewCountListStateDescriptor = new ListStateDescriptor<ItemViewCount>(
                "itemState-state",
                ItemViewCount.class
        );
        itemState = getRuntimeContext().getListState(itemViewCountListStateDescriptor);
    }

    @Override
    public void processElement(ItemViewCount input, Context context, Collector<String> out) throws Exception {
        itemState.add(input);
        context.getCurrentKey();
        context.timerService().registerEventTimeTimer(input.windowEnd + 1);

    }

    @Override
    public void onTimer(long timestamp, OnTimerContext ctx, Collector<String> out) throws Exception {
        List<ItemViewCount> allItems = new ArrayList<>();
        for (ItemViewCount item : itemState.get()) {
            allItems.add(item);
        }
        itemState.clear();
        allItems.sort(new Comparator<ItemViewCount>() {
            @Override
            public int compare(ItemViewCount o1, ItemViewCount o2) {
                return (int) (o2.viewCount - o1.viewCount);
            }
        });
        StringBuilder result = new StringBuilder();
        result.append("===========================================").append("\n");
        result.append("时间：").append(new Timestamp(timestamp - 1)).append("\n");
        for (int i = 0; i < topSize; i ++) {
            ItemViewCount currentItem = allItems.get(i);
            result.append("No").append(i).append(":").append("商品ID=").append(currentItem.itemId)
                    .append("浏览量=").append(currentItem.viewCount)
                    .append("\n");
        }
        result.append("=======================================");
        out.collect(result.toString());
    }
}
