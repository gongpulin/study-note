package com.gpl.flink.examples;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gongpulin
 * date 2020-06-22
 */
public class SessionWindowing {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.getConfig().setGlobalJobParameters(params);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.setParallelism(1);

        final List<Tuple3<String, Long, Integer>> input = new ArrayList<>();

        input.add(new Tuple3<>("a", 1L, 1));
        input.add(new Tuple3<>("b", 1L, 1));
        input.add(new Tuple3<>("b", 3L, 1));
        input.add(new Tuple3<>("b", 5L, 1));
        input.add(new Tuple3<>("c", 6L, 1));
        input.add(new Tuple3<>("a", 10L, 1));
        input.add(new Tuple3<>("c", 11L, 1));

        DataStream<Tuple3<String, Long, Integer>> source = env.addSource(new SourceFunction<Tuple3<String, Long, Integer>> (){
            private static final long serialVersionUID = 1L;

            @Override
            public void run(SourceContext<Tuple3<String, Long, Integer>> ctx) throws Exception {
                for (Tuple3<String, Long, Integer> value : input) {
                    ctx.collectWithTimestamp(value, value.f1);
                    ctx.emitWatermark(new Watermark(value.f1 - 1));
                }
                ctx.emitWatermark(new Watermark(Long.MAX_VALUE));
            }

            @Override
            public void cancel() {

            }
        });


    }
}
