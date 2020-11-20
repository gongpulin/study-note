package com.gpl.flink.orderjoingoods;



import com.alibaba.fastjson.JSON;

import com.gpl.flink.orderjoingoods.pojo.Goods;
import com.gpl.flink.orderjoingoods.pojo.Order;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.common.typeinfo.BasicTypeInfo;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.BroadcastProcessFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumerBase;
import org.apache.flink.util.Collector;

import java.util.Objects;
import java.util.Properties;

public class BroadcastOrderJoinGoodsName {
    private static final String KAFKA_CONSUMER_GROUP = "consumer-g1";
    private static final String JOB_NAME = "BroadcastOrderJoinGoodsName";

    public static void main(String[] args) throws Exception {
        final ParameterTool params = ParameterTool.fromArgs(args);
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        env.setParallelism(1);

        String topic = params.get("topic");
        String brokers = params.get("brokers");
        String group = params.get("group");
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers",brokers);
        prop.setProperty("group.id",group);

        FlinkKafkaConsumerBase<String> consumerBigOrder =
                new FlinkKafkaConsumer<>("order_topic_name",
                        new SimpleStringSchema(),
                        prop);

        SingleOutputStreamOperator<Order> orderStream = env.addSource(consumerBigOrder)
                .uid("order_topic_name")
                .filter(Objects::nonNull)
                .map(str -> JSON.parseObject(str, Order.class));

        FlinkKafkaConsumerBase<String>  consumerSmallOrder =
                new FlinkKafkaConsumer<String>("goods_dim_topic_name",
                        new SimpleStringSchema(),
                        prop);

        SingleOutputStreamOperator<Goods> goodsDimStream = env.addSource(consumerSmallOrder)
                .uid("goods_dim_topic_name")
                .filter(Objects::nonNull)
                .map(str -> JSON.parseObject(str, Goods.class));

        final MapStateDescriptor<Integer, String> GOODS_STATE = new MapStateDescriptor<>("GOODS_STATE", BasicTypeInfo.INT_TYPE_INFO,BasicTypeInfo.STRING_TYPE_INFO);

        SingleOutputStreamOperator<Tuple2<Order, String>> resStream = orderStream
                .connect(goodsDimStream.broadcast(GOODS_STATE))
                .process(new BroadcastProcessFunction<Order, Goods, Tuple2<Order, String>>() {

                    @Override
                    public void processElement(Order order, ReadOnlyContext ctx, Collector<Tuple2<Order, String>> out) throws Exception {
                        ReadOnlyBroadcastState<Integer, String> broadcastState = ctx.getBroadcastState(GOODS_STATE);
                        String goodsName = broadcastState.get(order.getGoodsId());
                        out.collect(Tuple2.of(order,goodsName));
                    }

                    @Override
                    public void processBroadcastElement(Goods goods, Context ctx, Collector<Tuple2<Order,String>> collector) throws Exception {
                        BroadcastState<Integer, String> broadcastState = ctx.getBroadcastState(GOODS_STATE);
                        if(goods.isRemove()) {
                            broadcastState.remove(goods.getGoodsId());
                        } else {
                            broadcastState.put(goods.getGoodsId(), goods.getGoodsName());
                        }
                    }
                });

        resStream.print();
        env.execute(JOB_NAME);
    }
}
