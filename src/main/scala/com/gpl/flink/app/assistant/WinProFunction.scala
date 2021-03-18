package com.gpl.flink.app.assistant

/**
 * @author gongpulin
 *         date 2021-01-05
 */
import org.apache.flink.streaming.api.scala.function.ProcessWindowFunction
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.util.Collector

import scala.collection.mutable.ArrayBuffer

class WinProFunction extends ProcessWindowFunction[(Int, String), String, Int, TimeWindow]{

  override def process(key: Int, context: Context,
                       elements: Iterable[(Int, String)], out: Collector[String]): Unit = {
    val resArr = new ArrayBuffer[String]()
    elements.foreach(rd =>{
      resArr.append(rd._2.trim)
    })
    if (resArr.nonEmpty) {
      out.collect(resArr.mkString("\n").replaceAll("null","\\\\N"))
      resArr.clear()
    }
  }

}