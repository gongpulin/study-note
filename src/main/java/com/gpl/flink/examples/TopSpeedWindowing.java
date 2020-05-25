package com.gpl.flink.examples;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.tuple.Tuple4;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.Random;

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


    }





}
