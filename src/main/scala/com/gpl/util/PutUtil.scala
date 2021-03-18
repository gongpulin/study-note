package com.gpl.util
import java.io.{BufferedReader, InputStreamReader}
import java.nio.charset.{Charset, StandardCharsets}

import org.apache.commons.net.util.Base64
import org.apache.http.HttpHeaders
import org.apache.http.client.config.RequestConfig
import org.apache.http.client.methods.{CloseableHttpResponse, HttpPut}
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.{CloseableHttpClient, DefaultConnectionKeepAliveStrategy, DefaultRedirectStrategy, HttpClientBuilder}
import org.joda.time.DateTime
/**
 * @author gongpulin
 *         date 2021-01-05
 */
/**
 * https://github.com/apache/incubator-doris/blob/master/samples/stream_load/java/DorisStreamLoad.java
 */
object PutUtil {
  var CHARSET = "UTF-8"
  val BINARY_CT = "application/octet-stream"
  var CONTENT_TYPE = s"text/plain; charset=${CHARSET}"
  var TIMEOUT = 30000
  var userName = "root"
  var password = ""

  def basicAuthHeader( username:String,  password:String):String =  {
    val  tobeEncode:String = username + ":" + password
    val encoded = Base64.encodeBase64(tobeEncode.getBytes(StandardCharsets.UTF_8))
    val res = "Basic " + new String(encoded)
    //      println("here:"+res)
    res
  }

  def clientGen(user:String = "root", passwd:String = ""): CloseableHttpClient ={
    this.userName = user
    this.password = passwd
    /*    val provider:CredentialsProvider = new BasicCredentialsProvider()
        val credentials:UsernamePasswordCredentials = new UsernamePasswordCredentials(user, passwd);
        provider.setCredentials(AuthScope.ANY, credentials)*/
    var httpClient: Any = null
    HttpClientBuilder.create()
      //      .setDefaultCredentialsProvider(provider)
      .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy)
      .setRedirectStrategy(new DefaultRedirectStrategy(){  // 对应 curl命令里的 --location-trusted 参数
        override def isRedirectable(method: String): Boolean = {
          super.isRedirectable(method)
          true
        }
      })
      .build()
  }

  /**
   * HTTP put
   *
   * @param payload      要发送的数据(string)
   * @param api          doris的API
   * @param contentType  设置put内容类型为json还是二进制流
   */
  def put( httpclient:CloseableHttpClient,
           payload:String,
           api:String ,
           contentType:String = CONTENT_TYPE ,
           headers: Map[String, String] = null,
           debug: Boolean = false): (Boolean,CloseableHttpClient, CloseableHttpResponse) ={
    //    val httpclient = clientGen()
    var response:CloseableHttpResponse = null
    var status = true
    try{
      val httpPut = new HttpPut(api)
      val requestConfig = RequestConfig.custom()
        .setAuthenticationEnabled(true)
        .setCircularRedirectsAllowed(true)
        .setRedirectsEnabled(true)
        .setRelativeRedirectsAllowed(true)
        .setExpectContinueEnabled(true)
        .setConnectTimeout(TIMEOUT).setConnectionRequestTimeout(TIMEOUT)
        .setSocketTimeout(TIMEOUT).build()
      httpPut.setConfig(requestConfig)
      httpPut.setHeader(HttpHeaders.EXPECT,"100-continue")  // .setExpectContinueEnabled(true)
      httpPut.setHeader(HttpHeaders.AUTHORIZATION, basicAuthHeader(this.userName, this.password))  // Authorization: Basic cm9vdDo=

      if (headers != null && headers.size > 0) {
        headers.foreach(entry =>{
          httpPut.setHeader(entry._1, entry._2)
        })
      }

      val content =new StringEntity(payload, Charset.forName(CHARSET))
      content.setContentType(contentType)
      content.setContentEncoding(CHARSET)
      httpPut.setEntity(content)
      response = httpclient.execute(httpPut)
      if(debug) {
        println("### Debug: "+new DateTime())
        println(response.getStatusLine())
        val br = new BufferedReader(new InputStreamReader(response.getEntity.getContent()))
        val sb = new StringBuffer()
        var str = ""
        while( str != null){
          sb.append(str.trim)
          str = br.readLine()
        }
        httpPut.getAllHeaders.foreach(println)
        println(sb.toString)
        println("### payload: ")
        println(payload)
      }
      //      response.close()
      status = true
      (status, httpclient, response)
    }catch {
      case ex:Exception => {
        println(s"### post err: @ ${new DateTime().toLocalDateTime()}")
        ex.printStackTrace()}
        status = false
        (status, httpclient, response)
    }
    (status, httpclient, response)
  }

  def postRetry(httpclient: CloseableHttpClient, payload:String, api:String , contentType:String = CONTENT_TYPE, maxRetry:Int = 5): Unit = {
    var fail = true
    var tried = 0
    while (tried<maxRetry && fail){
      fail = ! put(httpclient, payload, api , contentType)._1
      tried +=1
    }
  }

  def main(args: Array[String]): Unit = {
    // duplicate table1
    val api = "http://master1:18030/api/flink2doris/table1/_stream_load"
    val payload = "20190903_11_1_tom_130\n20190903_11_2_jerry_838"
    val headers = Map(   //"label"->"label123"  , 使用指定的label需注意唯一性，否则会提示已经存在而无法导入
      "max_filter_ratio"->"0.2",
      "columns"->"date,hour,id,name,metric",
      "column_separator"->"_"
    )
    put(clientGen(), payload, api, this.CONTENT_TYPE,headers, true)._3.close()

    // agg replace col table2
    val api2 = "http://master1:18030/api/flink2doris/table2/_stream_load"
    val payload2 = "1_tom_313\n1_tom_318"
    val headers2 = Map(   //"label"->"label123"  , 使用指定的label需注意唯一性，否则会提示已经存在而无法导入
      "max_filter_ratio"->"0.2",
      "columns"->"id,name,metric",
      "column_separator"->"_"
    )
    put(clientGen(), payload2, api2, this.CONTENT_TYPE,headers2, true)._3.close()

  }
}

