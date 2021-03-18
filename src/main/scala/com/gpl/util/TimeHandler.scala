package com.gpl.util

/**
 * @author gongpulin
 *         date 2021-01-05
 */
import java.text.SimpleDateFormat
import org.joda.time.format.DateTimeFormat
import org.apache.commons.lang3.time.FastDateFormat
import org.joda.time.DateTime

object TimeHandler {
  def plusMinAfterNow(plusMinutes: Int): String = {

    val datetime: DateTime = new DateTime()
    datetime.plusMinutes(plusMinutes).toString("yyyyMMddHHmm")
  }


  def minusDayBeforeNow(minusDay: Int): String ={
    val datetime: DateTime = new DateTime()
    datetime.minusDays(minusDay).toString("YYYY-MM-dd")
  }

  def getNowLongSec(): Long ={
    getNowLongMs() /1000
  }

  def getNowLongMs(): Long ={
    val datetime: DateTime = new DateTime()
    datetime.getMillis
  }

  def changeTimeFromLongMilisec(time: Long, dateFormat: String): String = {

    val date = new DateTime(time)
    date.toString(dateFormat)
  }

  def getMinuteIntFromLongSec(time: Long): Int ={
    new DateTime(time * 1000).getMinuteOfHour
  }

  def getHourIntFromLongSec(time: Long): Int ={
    new DateTime(time * 1000).getHourOfDay
  }

  def getHourIntFromNow():Int ={
    new DateTime().getHourOfDay()
  }

  def getMinuteIntFromNow(): Int ={
    new DateTime().getMinuteOfHour
  }

  /**
   * 获取当天零点
   */
  def getMidNightMillions(processTime: Long = System.currentTimeMillis()): Long = {
    val format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    val dateStr = format.format(processTime)
    format.parse(dateStr.split(" ")(0) + " 00:00:00").getTime
  }

  // 日期转时间戳
  def secondDateToStamp(date: String) : Long = {
    val dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm")
    dateFormat.parse(date).getTime
  }

  // 时间戳转日期
  def tranTimeStampToStr(time:Long): String = {
    FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(time)
  }

  // 时间戳转日期
  def tranTimeStampToDayStr(time:Long): String = {
    FastDateFormat.getInstance("yyyy-MM-dd").format(time)
  }

  //日期转时间戳
  def tranTimeToLong(tm:String) :Long={
    val simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    simpleDateFormat.parse(tm).getTime/1000
  }

  def getyMdHmsTime(time : Long,sep:String = "-") : String = {

    val timeFormatter = DateTimeFormat.forPattern(s"yyyy${sep}MM${sep}dd HH:mm:ss")
    val date = new DateTime(time)
    date.toString(timeFormatter)

  }
  def getDateStrFromLongSec(time: Long, sep:String = "-"): String ={
    changeTimeFromLongMilisec(time * 1000, s"yyyy${sep}MM${sep}dd")
  }

  def main (args: Array[String] ): Unit = {
    val time:Long = 1568941522L // sec
    val dt = getDateStrFromLongSec(time )  // 2019-09-20
    println(dt)
    println(minusDayBeforeNow(1))
    println(plusMinAfterNow(3))  // 201909201205  ->12:05
    println(getNowLongSec())   // 1568952130 -> 12:02
    println(getHourIntFromLongSec(1569404603))  //  17:43:23 -> 17
    println(tranTimeToLong("2020-02-10 10:21:12"))

  }

}