package com.gpl.util
import com.gpl.flink.app.assistant.Utils
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction
import org.apache.http.client.methods.CloseableHttpResponse
import org.apache.http.impl.client.CloseableHttpClient

import scala.util.Random
/**
 * @author gongpulin
 *         date 2021-01-05
 */
class TODoris(headers: Map[String,String],
              dbName: String,
              userName: String,
              password: String,
              tblName: String,
              randomFeId: Boolean,
              maxFeId: Int,
              port:Int = 18030,
              debug:Boolean = true) extends RichSinkFunction[String]{

  val CONTENT_TYPE = "text/plain"
  var api = ""
  var httpClient: CloseableHttpClient = null
  var response: CloseableHttpResponse = null

  override def open(parameters: Configuration): Unit = {
    val dorisFe = if (randomFeId) {
      Utils.dorisFePrefix + s"${new Random().nextInt(maxFeId) + 1}"
    } else {
      Utils.dorisFePrefix + s"${maxFeId}"
    }
    api = s"http://${dorisFe}:${port}/api/${dbName}/${tblName}/_stream_load"
  }

  // 使用连接写数据
  override def invoke(value: String): Unit = {
    //    println("To doris mode ---> " + value)
    try{
      httpClient = PutUtil.clientGen(userName, password)
      val res = PutUtil.put(httpClient, value, api, CONTENT_TYPE, headers, debug)
      httpClient = res._2
      response = res._3
      //      println("Response status linse -----> " + response.getStatusLine)
    }catch {
      case ex:Exception => {
        println("### invoke ERROR:")
        ex.printStackTrace()
      }
    }
    try{
      httpClient.close()
      response.close()
    } catch {
      case ex:Exception => {
        println("### http close ERROR:")
        ex.printStackTrace()
      }
    }
  }

}

