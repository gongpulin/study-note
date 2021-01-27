package com.gpl.flink.examples;

/**
 * @author gongpulin
 * date 2020-08-11
 */

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.util.Collector;

import java.io.IOException;
import java.util.Random;

/**
 * https://zhuanlan.zhihu.com/p/130708277
 *【示例】维护数据流中每个key的计数，并在每过一分钟(以事件时间)而未更新该key时，发出一个key/count对。 sessionwindow
 */
public class WordCountProcessFunction {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        DataStream<Tuple2<String, Long>> stream =  env
                .addSource(new MySourceFunction())
//                .fromElements("good good study","day day up")
//                .socketTextStream("192.168.1.140",9000)
                .flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
                    private static final long serialVersionUID = 1L;
                    @Override
                    public void flatMap(String value, Collector<Tuple2<String, Long>> out) throws Exception {
                        String[] words = value.split("\\W+");
                        for (String word : words) {
                            out.collect(new Tuple2(word, 1L));
                        }
                    }
                }).assignTimestampsAndWatermarks(new AscendingTimestampExtractor<Tuple2<String, Long>>(){
                    private static final long serialVersionUID = 1L;
                    @Override
                    public long extractAscendingTimestamp(Tuple2<String, Long> element) {
                        return System.currentTimeMillis();
                    }
                }).keyBy(x -> x.f0)
                .process(new CountWithTimeoutFunction());
        stream.print().setParallelism(1);
        env.execute("WordCountProcessFunction");
    }

    private static final class CountWithTimeoutFunction extends KeyedProcessFunction<String, Tuple2<String, Long>, Tuple2<String, Long>> {
        private ValueState<CountWithTimestamp> state;

        @Override
        public void open(Configuration parameters) {
            state = getRuntimeContext().getState(new ValueStateDescriptor<>("myState",CountWithTimestamp.class));
        }

        @Override
        public void processElement(Tuple2<String, Long> value, Context ctx, Collector<Tuple2<String, Long>> out) throws Exception {
            CountWithTimestamp current = state.value();
            if (current == null) {
                current = new CountWithTimestamp();
                current.key = value.f0;
            }
            current.count++;
            if (ctx != null) {
                current.lastModified = ctx.timestamp();
            }
            state.update(current);
            System.out.println("currentkey:"+current.key+",currentcount:"+current.count);
            //注册一个当前事件事件后60秒的定时器
            ctx.timerService().registerEventTimeTimer(current.lastModified + 60000);
        }

        @Override
        public void onTimer(long timestamp, OnTimerContext ctx, Collector<Tuple2<String, Long>> out) throws IOException {
            CountWithTimestamp result = state.value();
            if (timestamp == result.lastModified + 60000) {
                System.out.println("key:"+result.key+",count:"+result.count);
                out.collect(new Tuple2(result.key, result.count));
            }
        }
    }


    private static final class MySourceFunction implements SourceFunction<String> {

        @Override
        public void run(SourceContext<String> ctx) throws Exception {
            while (true) {
                String[] wl = {"aa", "bb" , "cc", "dd", "ee"};
                int num = new Random().nextInt(5);
                System.out.println("collect:"+wl[num]+"_________________________________-");
                ctx.collect(wl[num]);
                Thread.sleep(10000);
            }
        }

        @Override
        public void cancel() {

        }
    }
}
