package com.gpl.flink.app.assistant
import com.alibaba.fastjson.JSON
import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.util.Collector
/**
 * @author gongpulin
 *         date 2021-01-05
 */
class LogParseProcessFunction extends ProcessFunction[String,(String,(String,String))]{
  override def processElement(value: String, ctx: ProcessFunction[String, (String, (String, String))]#Context, collector: Collector[(String, (String, String))]): Unit = {
    try {
      if(value.trim.length != 0 && value.contains(Utils.specialSep)) {
        val Array(pdi, pm) = value.split(Utils.specialReg, -1)
        val Array(sysName, sTime) = pdi.split(Utils.hiveSep, -1)
        val rowsJsonArrayData = JSON.parseArray(pm)
        for (i <- 0 until rowsJsonArrayData.size()) {
          try {
            val jsonObject = rowsJsonArrayData.getJSONObject(i)
            val event = jsonObject.getString("event").replace("$", "")
            if (event.equals("AppStart")) {
              val loginId = jsonObject.getString("login_id")
              val properties = jsonObject.getJSONObject("properties")
              val proOsVersion = properties.getString("$os_version")
              val os = properties.getString("$os")
              val proAppVersion = properties.getString("$app_version")
              collector.collect((loginId,(event, sysName + "," + sTime + "," + proOsVersion + "," + os + "," + proAppVersion)))
            } else if (event.equals("AppViewScreen")) {
              val loginId = jsonObject.getString("login_id")
              val properties = jsonObject.getJSONObject("properties")
              val proOsVersion = properties.getString("$os_version")
              val os = properties.getString("$os")
              val proAppVersion = properties.getString("$app_version")
              val proUrl = properties.getString("$url")
              collector.collect((loginId,(event, sysName + "," + sTime + "," + proOsVersion + "," + os + "," + proAppVersion + "," + proUrl)))
            } else if (event.equals("SignUp")) {
              val loginId = jsonObject.getString("login_id")
              val properties = jsonObject.getJSONObject("properties")
              val proOsVersion = properties.getString("$os_version")
              val os = properties.getString("$os")
              val proAppVersion = properties.getString("$app_version")
              collector.collect((loginId,(event, sysName + "," + sTime + "," + proOsVersion + "," + os + "," + proAppVersion)))
            } else if (event.equals("AppClick")) {
              val loginId = jsonObject.getString("login_id")
              val properties = jsonObject.getJSONObject("properties")
              val proOsVersion = properties.getString("$os_version")
              val os = properties.getString("$os")
              val proAppVersion = properties.getString("$app_version")
              val elementId = properties.getString("$element_id")
              collector.collect((loginId,(event, sysName + "," + sTime + "," + proOsVersion + "," + os + "," + proAppVersion + "," + elementId)))
            } else if (event.equals("AppEnd")) {
              val loginId = jsonObject.getString("login_id")
              val properties = jsonObject.getJSONObject("properties")
              val proOsVersion = properties.getString("$os_version")
              val os = properties.getString("$os")
              val proAppVersion = properties.getString("$app_version")
              val eventDuration = properties.getString("event_duration")
              collector.collect((loginId,(event, sysName + "," + sTime + "," + proOsVersion + "," + os + "," + proAppVersion + "," + eventDuration)))
            }
          } catch {
            case ex : Exception => {
              println ( s"### raws LogParseProcessFunction: ${rowsJsonArrayData.toString()}" )
            }
          }

        }
      }
    } catch {
      case ex : Exception => {
        println ( s"### raws LogParseProcessFunction: ${value}" )
      }
    }

  }
}

