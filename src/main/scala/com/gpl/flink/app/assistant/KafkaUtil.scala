package com.gpl.flink.app.assistant

import java.util.Properties

import com.gpl.util.WithLoggedKafkaDeserializationSchema
import org.apache.flink.api.common.io.ratelimiting.GuavaFlinkConnectorRateLimiter
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011

/**
 * @author gongpulin
 *         date 2021-01-05
 */
object KafkaUtil {
  // 获取kafkaConsumer source
  def getConsumerObj(topic:String, prop: Properties, startFrom:String = "subscribe"): FlinkKafkaConsumer011[String] = {
    // kafka consumer
    val myConsumer = new FlinkKafkaConsumer011[String](topic, new SimpleStringSchema(), prop)
    startFrom match {
      case "subscribe" => myConsumer.setStartFromGroupOffsets() // 默认为该规则
      case "earliest" => myConsumer.setStartFromEarliest()
      case "latest" => myConsumer.setStartFromLatest()
      case _ if Character.isDigit(startFrom.trim()(0)) => myConsumer.setStartFromTimestamp(startFrom.toLong)
      case _ => myConsumer.setStartFromGroupOffsets()
    }
    myConsumer
  }

  /**
   * 获取kafkaConsumer source，带限速消费功能
   *
   * @param topic        :
   * @param prop         :
   * @param startFrom    :
   * @param rate         :     bytes per second
   * @return
   */
  def getConsumerObjWithLoggedTime(topic:String,
                                   prop: Properties,
                                   startFrom:String = "subscribe",
                                   rate: Long = -1) :FlinkKafkaConsumer011[(Long,String)] = {
    // kafka consumer
    val myConsumer = new FlinkKafkaConsumer011[(Long,String)](topic, new WithLoggedKafkaDeserializationSchema(), prop)
    startFrom match {
      case "subscribe" => myConsumer.setStartFromGroupOffsets() // 默认为该规则
      case "earliest" => myConsumer.setStartFromEarliest()
      case "latest" => myConsumer.setStartFromLatest()
      case _ if Character.isDigit(startFrom.trim()(0)) => myConsumer.setStartFromTimestamp(startFrom.toLong)
      case _ => myConsumer.setStartFromGroupOffsets()
    }
    //
    if(rate != -1){
      val rateLimiter = new GuavaFlinkConnectorRateLimiter
      rateLimiter.setRate(rate)
      myConsumer.setRateLimiter(rateLimiter)
    }
    myConsumer
  }

  // 获取kafkaConsumer source
  def getConsumerObjWithTopicList(topicList:List[String], prop: Properties, startFrom:String = "subscribe") :FlinkKafkaConsumer011[String] = {
    // kafka consumer
    val myConsumer = new FlinkKafkaConsumer011[String](topicList, new SimpleStringSchema(), prop)
    startFrom match {
      case "subscribe" => myConsumer.setStartFromGroupOffsets() // 默认为该规则
      case "earliest" => myConsumer.setStartFromEarliest()
      case "latest" => myConsumer.setStartFromLatest()
      case _ if Character.isDigit(startFrom.trim()(0)) => myConsumer.setStartFromTimestamp(startFrom.toLong)
      case _ => myConsumer.setStartFromGroupOffsets()
    }
    myConsumer
  }
}
