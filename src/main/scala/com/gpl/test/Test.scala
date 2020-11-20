package com.gpl.test

import java.text.SimpleDateFormat
import java.util.Date

import com.alibaba.fastjson.{JSON, JSONArray}



/**
 * @author gongpulin
 *         date 2020-06-11
 */
object Test {
  def main(args: Array[String]): Unit = {
    val rows = "[{\"_track_id\":98085574,\"time\":1591735536894,\"type\":\"track\",\"distinct_id\":\"10600426\",\"login_id\":\"10600426\",\"anonymous_id\":\"bcf04c362be23cbd\",\"lib\":{\"$lib\":\"Android\",\"$lib_version\":\"4.0.6\",\"$app_version\":\"1.1.01\",\"$lib_method\":\"code\",\"$lib_detail\":\"com.zd.partnerapp.ui.splash.SplashActivity######\"},\"event\":\"$AppStart\",\"properties\":{\"$lib\":\"Android\",\"$carrier\":\"中国联通\",\"$os_version\":\"9\",\"$device_id\":\"bcf04c362be23cbd\",\"$lib_version\":\"4.0.6\",\"$model\":\"PADM00\",\"$os\":\"Android\",\"$screen_width\":1080,\"$screen_height\":2280,\"$manufacturer\":\"OPPO\",\"$app_version\":\"1.1.01\",\"$wifi\":true,\"$network_type\":\"WIFI\",\"$resume_from_background\":false,\"$is_first_time\":false,\"$screen_name\":\"com.zd.partnerapp.ui.splash.SplashActivity\",\"$title\":\"智能助手\",\"$is_first_day\":false},\"_flush_time\":1591735591921},{\"_track_id\":-1980904470,\"time\":1591735538393,\"type\":\"track\",\"distinct_id\":\"10600426\",\"login_id\":\"10600426\",\"anonymous_id\":\"bcf04c362be23cbd\",\"lib\":{\"$lib\":\"Android\",\"$lib_version\":\"4.0.6\",\"$app_version\":\"1.1.01\",\"$lib_method\":\"code\",\"$lib_detail\":\"com.zd.partnerapp.ui.MainActivity######\"},\"event\":\"$AppViewScreen\",\"properties\":{\"$lib\":\"Android\",\"$carrier\":\"中国联通\",\"$os_version\":\"9\",\"$device_id\":\"bcf04c362be23cbd\",\"$lib_version\":\"4.0.6\",\"$model\":\"PADM00\",\"$os\":\"Android\",\"$screen_width\":1080,\"$screen_height\":2280,\"$manufacturer\":\"OPPO\",\"$app_version\":\"1.1.01\",\"$wifi\":true,\"$network_type\":\"WIFI\",\"$url\":\"com.zd.partnerapp.ui.main.home.HomeFragment\",\"$title\":\"智能助手\",\"$screen_name\":\"com.zd.partnerapp.ui.MainActivity|com.zd.partnerapp.ui.main.home.HomeFragment\",\"$is_first_day\":false},\"_flush_time\":1591735591922},{\"_track_id\":954956135,\"time\":1591735553417,\"type\":\"track\",\"distinct_id\":\"10600426\",\"login_id\":\"10600426\",\"anonymous_id\":\"bcf04c362be23cbd\",\"lib\":{\"$lib\":\"Android\",\"$lib_version\":\"4.0.6\",\"$app_version\":\"1.1.01\",\"$lib_method\":\"code\",\"$lib_detail\":\"com.zd.partnerapp.ui.MainActivity######\"},\"event\":\"$AppViewScreen\",\"properties\":{\"$lib\":\"Android\",\"$carrier\":\"中国联通\",\"$os_version\":\"9\",\"$device_id\":\"bcf04c362be23cbd\",\"$lib_version\":\"4.0.6\",\"$model\":\"PADM00\",\"$os\":\"Android\",\"$screen_width\":1080,\"$screen_height\":2280,\"$manufacturer\":\"OPPO\",\"$app_version\":\"1.1.01\",\"$wifi\":true,\"$network_type\":\"WIFI\",\"$referrer\":\"com.zd.partnerapp.ui.main.home.HomeFragment\",\"$url\":\"com.zd.partnerapp.ui.main.home.HomeFragment\",\"$title\":\"智能助手\",\"$screen_name\":\"com.zd.partnerapp.ui.MainActivity|com.zd.partnerapp.ui.main.home.HomeFragment\",\"$is_first_day\":false},\"_flush_time\":1591735591922},{\"_track_id\":1840312359,\"time\":1591735561782,\"type\":\"track\",\"distinct_id\":\"10600426\",\"login_id\":\"10600426\",\"anonymous_id\":\"bcf04c362be23cbd\",\"lib\":{\"$lib\":\"Android\",\"$lib_version\":\"4.0.6\",\"$app_version\":\"1.1.01\",\"$lib_method\":\"code\",\"$lib_detail\":\"com.zd.partnerapp.ui.MainActivity######\"},\"event\":\"$AppEnd\",\"properties\":{\"$lib\":\"Android\",\"$carrier\":\"中国联通\",\"$os_version\":\"9\",\"$device_id\":\"bcf04c362be23cbd\",\"$lib_version\":\"4.0.6\",\"$model\":\"PADM00\",\"$os\":\"Android\",\"$screen_width\":1080,\"$screen_height\":2280,\"$manufacturer\":\"OPPO\",\"$app_version\":\"1.1.01\",\"$wifi\":true,\"$network_type\":\"WIFI\",\"$screen_name\":\"com.zd.partnerapp.ui.MainActivity\",\"$title\":\"智能助手\",\"event_duration\":\"24.993\",\"$is_first_day\":false},\"_flush_time\":1591735591922}]"
    val rowsJsonArrayData:JSONArray = JSON.parseArray(rows);
    for (i <- 0 until rowsJsonArrayData.size()) {
      val jsonObject = rowsJsonArrayData.getJSONObject(i)
//      val properties = jsonObject.getJSONObject("properties")
//      println(jsonObject.getString("_track_id"))
//      println(jsonObject.getString("time"))
//      println(properties.getString("$device_id"))
//      println(properties.getString("$carrier"))
//      println(properties.toString)
//      println(timestamp2date(jsonObject.getString("time")))
      val jsonStr = jsonObject.toJSONString
      val jsonObject1 = JSON.parseObject(jsonStr)
      val properties = jsonObject1.getJSONObject("properties")
      println(jsonObject1.getString("event").replace("$",""))
//      println(properties.toString)
//      println(properties.toJSONString)
//      println(properties.toJSONString.substring(1,properties.toJSONString.length-1))
    }

  }

  def timestamp2date(tm:String) :String={
    val fm = new SimpleDateFormat("yyyy-MM-dd")
    val tim = fm.format(new Date(tm.toLong))
    tim
  }
}
