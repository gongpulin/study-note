package com.gpl.flink.examples;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Iterator;

/**
 * @author gongpulin
 * date 2020-06-10
 */
public class WindowWordCount {
    public static void main(String[] args) throws Exception {
        final ParameterTool params = ParameterTool.fromArgs(args);
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> text;
        if (params.has("input")) {
            text = env.readTextFile(params.get("input"));
        } else {
            System.out.println("Executing WindowWordCount example with default input data set");
            System.out.println("Use --input to specify file input");
            text = env.fromElements(new String[] {"aa","bb"});
        }
        env.getConfig().setGlobalJobParameters(params);

        final int windowsSize = params.getInt("window",10);
        final int slideSize = params.getInt("slide",5);

        DataStream<Tuple2<String,Integer>>  counts = text.flatMap(new Tokenizer())
                .keyBy(0)
                .countWindow(windowsSize,slideSize)
                .sum(1);

        if (params.has("output")) {
            counts.writeAsText(params.get("output"));
        } else {
            System.out.println("Printing result to stdout.---");
            counts.print();
        }
        env.execute("WindowWordCount");


    }

    public static class Tokenizer implements FlatMapFunction<String, Tuple2<String,Integer>> {

        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            String[] tokens = value.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Tuple2<String,Integer>(token,1));
                }
            }
        }
    }
}
