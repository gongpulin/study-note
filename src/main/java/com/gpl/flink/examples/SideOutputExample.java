package com.gpl.flink.examples;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;

/**
 * @author gongpulin
 * date 2020-07-09
 */
public class SideOutputExample {
    private static final OutputTag<String> rejectedWordsTag = new OutputTag<String>("rejected"){};
    public static void main(String[] args) throws Exception {
        final ParameterTool params = ParameterTool.fromArgs(args);
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setStreamTimeCharacteristic(TimeCharacteristic.IngestionTime);
        env.getConfig().setGlobalJobParameters(params);
        DataStream<String> text;
        if (params.has("input")) {
            text = env.readTextFile(params.get("input"));
        } else {
            System.out.println("Executing WordCount example with default input data set.");
            System.out.println("Use --input to specify file input.");
            text = env.fromElements(new String[]{"aa","bb","cc","aa"});
        }
        //，买也得买
        SingleOutputStreamOperator<Tuple2<String, Integer>> tokenized = text
                .keyBy(new KeySelector<String, Integer>() {
                    private static final long serialVersionUID = 1L;
                    @Override
                    public Integer getKey(String value) throws Exception {
                        return 0;
                    }
                })
                .process(new Tokenizer());
        DataStream<String> rejectedWords = tokenized
                .getSideOutput(rejectedWordsTag)
                .map(new MapFunction<String, String>() {
                    private static final long serialVersionUID = 1L;
                    @Override
                    public String map(String value) throws Exception {
                        return "rejected:" + value;
                    }
                });

        DataStream<Tuple2<String, Integer>> counts = tokenized
                .keyBy(0)
                .window(TumblingEventTimeWindows.of(Time.seconds(5)))
                .sum(1);

        if (params.has("output")) {
            counts.writeAsText(params.get("output"));
            rejectedWords.writeAsText(params.get("rejected-words-output"));
        } else {
            System.out.println("Printing result to stdout. Use --output to specify output path.");
            counts.print();
            rejectedWords.print();
        }
        env.execute("Streaming WorCount Sideoutput");

    }

    public static final class Tokenizer extends ProcessFunction<String, Tuple2<String, Integer>> {
        private static final long serialVersionUID = 1L;
        @Override
        public void processElement(String value, Context ctx, Collector<Tuple2<String, Integer>> out) throws Exception {
            String[] tokens = value.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length() > 5) {
                    ctx.output(rejectedWordsTag, token);
                } else if (token.length() > 0) {
                    out.collect(new Tuple2<>(token, 1));
                }
            }

        }
    }
}
