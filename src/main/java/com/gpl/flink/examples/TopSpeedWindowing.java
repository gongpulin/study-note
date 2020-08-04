package com.gpl.flink.examples;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.windowing.delta.DeltaFunction;
import org.apache.flink.streaming.api.windowing.assigners.GlobalWindows;
import org.apache.flink.streaming.api.windowing.evictors.TimeEvictor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.DeltaTrigger;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author gongpulin
 * date 2020-05-25
 */
public class TopSpeedWindowing {
    public static void main(String[] args) {
        final ParameterTool params = ParameterTool.fromArgs(args);
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.getConfig().setGlobalJobParameters(params);

        DataStream<Tuple4<Integer, Integer, Double, Long>> carData;
        if (params.has("input")) {
            carData = env.readTextFile(params.get("input")).map(new ParseCarData());
        } else {
            System.out.println("Executing TopSpeedWindowing example with default input data set.");
            System.out.println("Use --input to specify file input");
            carData = env.addSource(CarSource.create(2));
        }
        /**
         * 他给我发个消息之后，我立马给他回消息，他不回
         * 我咋打包啊，现在机器登陆不上去
         * 你还有其他要备份的吗？我的代码都在/home目录下，备份/home目录和备份数据库我这边就完事了，你还要备份啥吗？
         *我找到一个刷题神器，idea有leetcode插件，在idea中刷题美滋滋，摸鱼完全不会被看破
         */


        int evictionSec = 10;
        int triggerMeters = 50;
        DataStream<Tuple4<Integer, Integer, Double, Long>> topSpeeds = carData.assignTimestampsAndWatermarks(new CarTimestamp())
                .keyBy(0)
                .window(GlobalWindows.create())
                .evictor(TimeEvictor.of(Time.of(evictionSec, TimeUnit.SECONDS)))
                .trigger(DeltaTrigger.of(triggerMeters,
                        new DeltaFunction<Tuple4<Integer, Integer, Double, Long>>() {
                            private static final long serialVersionUID = 1L;
                            @Override
                            public double getDelta(Tuple4<Integer, Integer, Double, Long> oldDataPoint, Tuple4<Integer, Integer, Double, Long> newDataPoint) {
                                return newDataPoint.f2 - oldDataPoint.f2;
                            }
                        },carData.getType().createSerializer(env.getConfig())))
                .maxBy(1);
        if (params.has("output")) {
            topSpeeds.writeAsText(params.get("output"));
        } else {
            topSpeeds.print();
        }


    }


    private static class ParseCarData extends RichMapFunction<String, Tuple4<Integer, Integer, Double, Long>> {
        private static final long serialVersionUID = 1L;

        @Override
        public Tuple4<Integer, Integer, Double, Long> map(String recoder) {
            String rawData = recoder.substring(1, recoder.length() -1);
            String[] data = rawData.split(",");
            return new Tuple4<>(Integer.valueOf(data[0]),Integer.valueOf(data[1]),Double.valueOf(data[2]),Long.valueOf(data[3]));
        }
    }


    private static class CarSource implements SourceFunction<Tuple4<Integer, Integer, Double, Long>> {
        private static final long serialVersionUID = 1L;
        private Integer[] speeds;
        private Double[] distances;

        private Random random = new Random();

        private volatile boolean isRunning = true;

        private CarSource(int numOfCars) {
            speeds = new Integer[numOfCars];
            distances = new Double[numOfCars];
            Arrays.fill(speeds, 50);
            Arrays.fill(distances, 50);
        }

        public static CarSource create(int cars) {
            return new CarSource(cars);
        }

        @Override
        public void run(SourceContext<Tuple4<Integer, Integer, Double, Long>> ctx) throws Exception {
            while(isRunning) {
                Thread.sleep(100);
                for (int carId = 0; carId < speeds.length; carId++) {
                    if (random.nextBoolean()) {
                        speeds[carId] = Math.min(100, speeds[carId] + 5);
                    } else {
                        speeds[carId] = Math.max(0, speeds[carId] - 5);
                    }
                    distances[carId] += speeds[carId] / 3.6d;
                    Tuple4<Integer, Integer, Double, Long> recoder = new Tuple4<>(carId, speeds[carId], distances[carId], System.currentTimeMillis());
                    ctx.collect(recoder);
                }
            }

        }

        @Override
        public void cancel() {
            isRunning = false;
        }


    }


    private static class CarTimestamp extends AscendingTimestampExtractor<Tuple4<Integer, Integer, Double, Long>> {
        private static final long serialVersionUID = 1L;

        @Override
        public long extractAscendingTimestamp(Tuple4<Integer, Integer, Double, Long> element) {
            return element.f3;
        }
    }





}
