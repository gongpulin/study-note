package com.gpl.flink.trigger;

import com.gpl.flink.window.aggregate.StockPrice;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.triggers.TriggerResult;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;

import java.io.IOException;

/**
 * @author gongpulin
 * date 2020-09-24
 */

/**
 * 在股票或任何交易场景中，我们比较关注价格急跌的情况，默认窗口长度是60秒，如果价格跌幅超过5%，则立即执行Window Function，
 * 如果价格跌幅在1%到5%之内，那么10秒后触发Window Function。
 */
public class FastDownTrigger extends Trigger<StockPrice, TimeWindow> {

    /**
     * 在自定义Trigger时，如果使用了状态，一定要使用clear方法将状态数据清理，否则随着窗口越来越多，状态数据会越积越多。

     */
    @Override
    public TriggerResult onElement(StockPrice element, long timestamp, TimeWindow window, TriggerContext ctx) throws Exception {
        ValueState<Double> lastValueState = ctx.getPartitionedState(new ValueStateDescriptor<Double>("lastPriceState", Double.class));
        // 设置返回默认值为CONTINUE
        TriggerResult triggerResult = TriggerResult.CONTINUE;
        Double lastValue = lastValueState.value();
        // 第一次使用lastPriceState时状态是空的,需要先进行判断
        // 状态数据由Java端生成，如果是空，返回一个null
        // 如果直接使用Scala的Double，需要使用下面的方法判断是否为空
        if (lastValue != null) {
            if (lastValue - element.price > lastValue * 0.05) {
                triggerResult = TriggerResult.FIRE_AND_PURGE;
            } else if (lastValue - element.price > lastValue * 0.01) {
                long t = ctx.getCurrentProcessingTime() + (10 * 1000 - (ctx.getCurrentProcessingTime() % 10 * 1000));
                ctx.registerProcessingTimeTimer(t);
            }
        }
        lastValueState.update(element.price);
        return triggerResult;
    }

    @Override
    public TriggerResult onProcessingTime(long time, TimeWindow window, TriggerContext triggerContext) {
        return TriggerResult.CONTINUE;
    }

    @Override
    public TriggerResult onEventTime(long time, TimeWindow window, TriggerContext ctx) throws Exception {
        return TriggerResult.CONTINUE;
    }

    @Override
    public void clear(TimeWindow window, Trigger.TriggerContext triggerContext ) {
        ValueState<Double> lastValueState = triggerContext.getPartitionedState(new ValueStateDescriptor<Double>("lastPriceState", Double.class));
        lastValueState.clear();
    }
}














