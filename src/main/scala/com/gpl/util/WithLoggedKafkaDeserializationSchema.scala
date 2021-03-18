package com.gpl.util

/**
 * @author gongpulin
 *         date 2021-01-05
 */
import java.nio.charset.StandardCharsets

import org.apache.flink.api.common.typeinfo.{TypeHint, TypeInformation}
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema
import org.apache.kafka.clients.consumer.ConsumerRecord

/**
 * 返回数据的 kafka 时间和数据本身
 */
class WithLoggedKafkaDeserializationSchema() extends KafkaDeserializationSchema[(Long,String)]
{
  override def isEndOfStream(nextElement: (Long, String)) = {
    false
  }

  // (kafkaLoggedTime,data)
  override def deserialize(record: ConsumerRecord[Array[Byte], Array[Byte]]) = {
    //    println(" -- Logged time --> " + record.timestamp() + " --record --> " + new String(record.value(), StandardCharsets.UTF_8))
    (record.timestamp(),new String(record.value(), StandardCharsets.UTF_8))
  }

  override def getProducedType = {
    TypeInformation.of(new TypeHint[(Long,String)](){})
  }
}
