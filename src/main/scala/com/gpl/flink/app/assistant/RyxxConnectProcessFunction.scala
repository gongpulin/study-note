package com.gpl.flink.app.assistant
import org.apache.flink.api.common.state.{MapState, MapStateDescriptor}
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.co.KeyedCoProcessFunction
import org.apache.flink.types.Row
import org.apache.flink.util.Collector
/**
 * @author gongpulin
 *         date 2021-01-05
 */
class RyxxConnectProcessFunction extends KeyedCoProcessFunction[String,(String,(String,String)),(String,Row),Row] {

  /**
   * 人员信息缓存在state中
   */
  var ryxxState:MapState[String, Row] = _

  override def open(param: Configuration) = {
    super.open(param)
    val ryxxStateDesc = new MapStateDescriptor[String, Row]("ryxxStateDesc", classOf[String],classOf[Row])
    ryxxState = getRuntimeContext().getMapState(ryxxStateDesc)

  }

  override def processElement1(value: (String, (String, String)), ctx: KeyedCoProcessFunction[String, (String, (String, String)), (String, Row), Row]#Context, out: Collector[Row]): Unit = {
    try {

      val event = value._2._1
      val RYBH = value._1

      var eventDuration = "0"

      val split = value._2._2.split(",") //  sysName + "," + sTime + "," + proOsVersion + "," + os + "," + proAppVersion
      val APP_VERSION = split(4)
      val OS = split(3)
      val OS_VERSION = split(2)

      if (event == "AppEnd") {
        eventDuration = split(5)
      }

      var SOURCE_CODE = "aaaa"
      var SOURCE_NAME = "aaaaaaaa"

      val ansRow = new Row(51)
      ansRow.setField(0,event)
      ansRow.setField(1,APP_VERSION)
      ansRow.setField(2,OS)
      ansRow.setField(3,OS_VERSION)
      ansRow.setField(4,eventDuration)
      ansRow.setField(5,SOURCE_CODE)
      ansRow.setField(6,SOURCE_NAME)


      val row = ryxxState.get(value._1)
      if (row != null) {
        for (i <- 0 to 43) {
          val r = row.getField(i)
          if (r != null) {
            ansRow.setField(i+7, r.toString)
          } else {
            ansRow.setField(i+7, "")
          }

        }
      } else {
        for (i <- 0 to 43) {
          ansRow.setField(i+7, "")
        }
      }

      out.collect(ansRow)
    } catch {
      case e:Exception => {
        e.printStackTrace()
      }
    }
  }

  override def processElement2(value: (String, Row), ctx: KeyedCoProcessFunction[String, (String, (String, String)), (String, Row), Row]#Context, out: Collector[Row]): Unit = {
    try {
      ryxxState.put(value._1, value._2)
    } catch {
      case e:Exception => {
        e.printStackTrace()
      }
    }
  }
}
