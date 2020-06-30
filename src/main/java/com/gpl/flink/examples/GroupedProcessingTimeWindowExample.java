package com.gpl.flink.examples;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.util.Collector;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
/**
 * @author gongpulin
 * date 2020-06-30
 */
public class GroupedProcessingTimeWindowExample {
    public static void main(String[] args) throws Exception {
        final ParameterTool params = ParameterTool.fromArgs(args);
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.getConfig().setGlobalJobParameters(params);
        env.setParallelism(4);

        DataStream<Tuple2<Long, Long>> stream = env.addSource(new DataSource());
        stream.keyBy(0)
                .timeWindow(Time.of(2500, MILLISECONDS), Time.of(500, MILLISECONDS))
                .reduce(new SummingReducer())
                .addSink(new SinkFunction<Tuple2<Long, Long>>() {
                    @Override
                    public void invoke(Tuple2<Long, Long> value) {

                    }
                });

        env.execute();

    }


    private static class DataSource extends RichParallelSourceFunction<Tuple2<Long, Long>> {

        private volatile boolean isRunning = true;

        @Override
        public void run(SourceContext<Tuple2<Long, Long>> ctx) throws Exception {
            final long startTime = System.currentTimeMillis();
            final long numElements = 20000000;
            final long numKeys = 1000;
            long val = 1L;
            long count = 0L;
            while (isRunning && count < numElements) {
                count ++;
                ctx.collect(new Tuple2<>(val++, 1L));
                if (val > numKeys) {
                    val = 1L;
                }
            }
            final long endTime = System.currentTimeMillis();
            System.out.println("Took" + (endTime - startTime) + "msecs for " + numElements + " values");
        }

        @Override
        public void cancel() {
            isRunning = false;
        }
    }


    private static class SummingWindowFunction implements WindowFunction<Tuple2<Long, Long>,Tuple2<Long,Long>, Long, Window> {
        @Override
        public void apply(Long key, Window window, Iterable<Tuple2<Long, Long>> values, Collector<Tuple2<Long, Long>> out) {
            long sum = 0L;
            for (Tuple2<Long, Long> value : values) {
                sum += value.f1;
            }
            out.collect(new Tuple2<>(key, sum));
        }
    }

    private static class SummingReducer implements ReduceFunction<Tuple2<Long, Long>> {
        @Override
        public Tuple2<Long, Long> reduce(Tuple2<Long, Long> value1, Tuple2<Long, Long> value2) {
            return new Tuple2<>(value1.f0, value1.f1 + value2.f1);
        }
    }

    private static class FirstFieldKeyExtractor<Type extends Tuple, Key> implements KeySelector<Type, Key> {
        @Override
        public Key getKey(Type value) {
            return (Key) value.getField(0);
        }
    }



}
