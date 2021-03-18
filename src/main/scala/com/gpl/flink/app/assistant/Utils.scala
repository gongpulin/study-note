package com.gpl.flink.app.assistant

import java.io.{BufferedReader, InputStreamReader}
import java.util.Properties
import java.util.concurrent.TimeUnit

import org.apache.flink.api.common.restartstrategy.RestartStrategies
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.contrib.streaming.state.RocksDBStateBackend
import org.apache.flink.streaming.api.CheckpointingMode
import org.apache.flink.streaming.api.environment.CheckpointConfig
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.table.api.EnvironmentSettings
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment
import org.apache.flink.streaming.api.functions.source.SourceFunction
import org.apache.flink.types.Row
import org.apache.flink.util.StringUtils
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FileSystem, Path}
import org.apache.hadoop.hive.ql.io.orc.{OrcFile, OrcInputFormat}
import org.apache.hadoop.hive.serde2.objectinspector.StructObjectInspector

import scala.collection.JavaConverters._
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


/**
 * @author gongpulin
 *         date 2021-01-05
 */
object Utils {
  val specialSep = "^~$"
  val specialReg = "\\^~\\$"
  val hiveSep = "\t"

  def getKafkaConsumerParam(param: Properties) : Properties = {
    val retPro = new Properties()
    retPro.setProperty("bootstrap.servers",param.getProperty("brokers"))
    retPro.setProperty("group.id",param.getProperty("group"))
    retPro
  }

  def getStreamTableEnv(env: StreamExecutionEnvironment, param: Properties) : StreamTableEnvironment = {
    val settings = EnvironmentSettings.newInstance.useBlinkPlanner.inStreamingMode.build
    val tableEnvironment = StreamTableEnvironment.create(env,settings)
    tableEnvironment
  }

  /**
   * 获取 Flink 运行环境
   * @param param
   * @return
   */
  def getExecutionEnvironment(param: Properties):StreamExecutionEnvironment = {
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    env.setMaxParallelism(param.getProperty("maxParallel").toInt)
    env.setParallelism(param.getProperty("defaultParallel").toInt)
    // 失败重试
    env.setRestartStrategy(RestartStrategies.failureRateRestart(
      3, // 每个时间间隔的最大故障次数
      org.apache.flink.api.common.time.Time.of(5, TimeUnit.MINUTES), // 测量故障率的时间间隔
      org.apache.flink.api.common.time.Time.of(10, TimeUnit.SECONDS) // 延时
    ))
    //    env.setRestartStrategy(RestartStrategies.fixedDelayRestart(1, Time.seconds(5)))
    // checkpoint配置
    env.enableCheckpointing(1000 * 60 * 5)
    env.getCheckpointConfig.setCheckpointingMode(CheckpointingMode.AT_LEAST_ONCE)
    env.getCheckpointConfig.setMinPauseBetweenCheckpoints(500)
    env.getCheckpointConfig.setCheckpointTimeout(1000 * 60 * 10)
    env.getCheckpointConfig.setMaxConcurrentCheckpoints(1)
    env.getCheckpointConfig.enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.DELETE_ON_CANCELLATION)
    env.getCheckpointConfig.setTolerableCheckpointFailureNumber(Integer.MAX_VALUE)
    // 作业需要维持的状态很小，不需要增量chk
    //    env.setStateBackend(new FsStateBackend(param.getProperty("chkPath"), false).asInstanceOf[StateBackend])
    env.setStateBackend(new RocksDBStateBackend(param.getProperty("chkPath"), true).getCheckpointBackend)

    env
  }


  /**
   * 解析从 args 获取单个入参
   * @param args
   * @param key
   * @param default
   * @return
   */
  def parseArgs(args: Array[String],key: String, default: String) = {
    ParameterTool.fromArgs(args).get(key,default)
  }

  /**
   * 解析 args，获取全部入参
   * @param args
   * @return
   */
  def parseArgs(args: Array[String]): Properties = {
    val pro = new Properties()

    val parameterTool = ParameterTool.fromArgs(args)
    pro.put("debug",parameterTool.get("debug","false"))
    pro.put("includeFileList",parameterTool.get("includeFileList","data"))
    pro.put("chkPath",parameterTool.get("chkPath","file:///C://Users//Lenovo//Desktop//chk"))
    pro.put("ryxx_orc_path",parameterTool.get("ryxx_orc_path","C:\\Users\\Lenovo\\Desktop\\TD\\RYXX\\oedate="))
    pro.put("url_page_orc_path",parameterTool.get("url_page_orc_path","C:\\Users\\Lenovo\\Desktop\\TD\\URL\\oedate="))
    pro.put("orcLoadDuration",parameterTool.get("orcLoadDuration",(60 * 60 * 1000).toString))

    // flink
    pro.put("maxParallel",parameterTool.get("maxParallel", "6"))
    pro.put("defaultParallel",parameterTool.get("defaultParallel", "1"))
    pro.put("sourceParallel",parameterTool.get("sourceParallel", "3"))
    pro.put("computeBuckets",parameterTool.get("computeBuckets", "6"))
    pro.put("sinkParallelism",parameterTool.get("sinkParallelism", "1"))
    pro.put("sinkMapParallel",parameterTool.get("sinkMapParallel", "3"))
    pro.put("ideaMinTimeHour",parameterTool.get("ideaMinTimeHour", "25"))
    pro.put("ideaMaxTimeHour",parameterTool.get("ideaMaxTimeHour", "26"))

    // Kafka
    pro.put("brokers",parameterTool.get("brokers","bigdata-kafka1:9092"))
    pro.put("group",parameterTool.get("group","app_join"))
    pro.put("topic",parameterTool.get("topic","PROD_APPLOG_SRC"))
    pro.put("startFrom",parameterTool.get("startFrom","latest"))

    //Doris
    pro.put("maxFeId",parameterTool.get("maxFeId", "3"))
    pro.put("randomFeId",parameterTool.get("randomFeId", "false"))
    pro.put("sinkParallelism",parameterTool.get("sinkParallelism", "1"))
    pro.put("filterRatio",parameterTool.get("filterRatio", "0.8"))
    pro.put("fePort",parameterTool.get("fePort", "18030"))
    pro.put("dbName",parameterTool.get("dbName", "dev_test_db1"))
    pro.put("tableName",parameterTool.get("tableName", "TA_APP_ASSISTANT_USER_BEHAVIOR_MI"))
    pro.put("userName",parameterTool.get("userName", "root"))
    pro.put("password",parameterTool.get("password", "doris"))
    //    pro.put("aggDuration",parameterTool.get("aggDuration", "8000"))
    pro.put("dorisAggDuration",parameterTool.get("dorisAggDuration", "5000"))
    pro.put("userBehaviorTableName",parameterTool.get("userBehaviorTableName", "RQ,YYXTBH,YYXTMC,RYID,XM,NL,RYBH,GMSFHM,CSRQ,CKJGID,CKSZBM,RYLBID,RYLB,RYLBPX,RYFLID,RYFL,RYFLPX,ZWJBID,ZWJB,ZWJBPX,GZID,GZ,GZPX,GWMCID,GWMC,GWMCPX,GRPYDJID,GRPYDJ,GRPYDJPX,XBID,XB,XBPX,MZID,MZ,MZPX,ZZMMID,ZZMM,ZZMMPX,DWID,DWMC,DWPX,CDID,CDMC,CDPX,XLID,XLMC,XLPX,YYXTBB,OS,OS_VERSION,DRFWCS,DRFWYMSL,SRFWSC,DRYSDJL,DRLL,ZCRS,WRTIME"))
    pro.put("loginDetailTableName",parameterTool.get("loginDetailTableName", "RQ,SOURCE_CODE,SOURCE_NAME,DWID,DWMC,DWPX,CDID,CDMC,CDPX,XLID,XLMC,XLPX,APP_VERSION,LOGIN_ID,LONGITUDE,LATITUDE,event_time"))

    //    val dq2DorisTableConf = DorisUtil.parseConf(parameterTool.get("dv2DorisConfPath", dv2DorisConf)) // hdfs://ns/app/conf/dv2Doris.conf
    //    pro.put("dorisFields",dq2DorisTableConf.getOrElse(tableName,"OEDATE,OEHOUR,BUSID,BUSTYPE,MODULECODE,TOPIC,FIELDCODE,FIELDINDEX,ERRORID,FIELDNAME,MODULENAME,ERRORNAME,ERRORINFO,ETLRANGE,BUSINESSRANGE,CHECKRULE,FIELDVALUE,OETIME,PRTIME,PSTIME,ETLTIME,WRTIME"))
    //    pro.put("parseErrorIDBlack",dq2DorisTableConf.getOrElse("PARSE_ERROR_ID_BLACK","error0,error1,error2"))
    pro
  }


  def toRow(list: ArrayBuffer[String]): Row = {
    val row = new Row(list.length);
    for (i <- 0 to list.length - 1) {
      row.setField(i, list(i));
    }
    row
  }

  def readOrc(path: String,includeFileList: List[String],ctx: SourceFunction.SourceContext[Row]) = {
    val conf = new Configuration()
    val file_in = new Path(path)
    val fs = FileSystem.get(file_in.toUri, conf)
    val childFiles = fs.listStatus(file_in)
    val filesSize = childFiles.size
    if(filesSize > 0){
      for(i <- 0 to filesSize - 1){
        val childFile = childFiles(i).getPath
        if(isInclude(childFile.getName,includeFileList)){
          val reader = OrcFile.createReader(fs, new Path(childFile.toString))
          val schema = reader.getSchema()  // 获取ORC文件的schema文件
          //                val fieldNameList = schema.getFieldNames
          //                System.out.println(schema.toJson())
          System.out.println(schema.getFieldNames)
          val inspector = reader.getObjectInspector().asInstanceOf[StructObjectInspector]
          val records = reader.rows()
          var row: Any = null
          while (records.hasNext()) {
            row = records.next(row)
            val parse = inspector.getStructFieldsDataAsList(row).asScala.toList
            val add = new ArrayBuffer[String]()
            for(field <- parse){
              if(field != null){
                add.append(String.valueOf(field))
              }else{
                add.append(null)
              }
            }
            ctx.collect(toRow(add))
          }
        }
      }
    }

  }

  def isInclude(fileName: String,includeFileList: List[String]): Boolean ={
    var retFlag = false
    for(includeFile <- includeFileList){
      if(fileName.contains(includeFile)){
        retFlag = true
      }
    }
    retFlag
  }


  val dorisFePrefix: String = "bigdata-doris"

  /**
   * 将HDFS上的配置文件解析为map：
   *  - key   :topic即doris表名
   *  - value :doris表的字段列表String类型，逗号分割
   *
   * @param confFile
   * @return
   */
  def parseConf(confFile:String): Map[String, String] ={
    val hadoopConf = new  org.apache.hadoop.conf.Configuration()
    val fs = FileSystem.get(new Path(confFile).toUri,hadoopConf)
    val fh = fs.open(new Path(confFile))
    val confMap = new mutable.HashMap[String, String]
    val isr = new InputStreamReader(fh)
    val bReader = new BufferedReader(isr)
    var line:String = ""
    while(null!=line) {
      if (line.trim.length != 0) {
        val Array(tp, fields) = line.split("=")
        confMap.put(tp.trim, fields.trim)
      }
      line = bReader.readLine()
    }
    val res = confMap.toMap
    confMap.clear()
    res
  }

  def row2StringDoris(row: Row): String ={
    val sb = new StringBuilder()
    for (i <- 0 until row.getArity) {
      if (i > 0) sb.append("\t")
      sb.append(StringUtils.arrayAwareToString(row.getField(i)))
    }
    sb.toString().replaceAll("null","\\\\N")
  }

  def row2String(row: Row): String ={
    val sb = new StringBuilder()
    for (i <- 0 until row.getArity) {
      if (i > 0) sb.append("\t")
      sb.append(StringUtils.arrayAwareToString(row.getField(i)))
    }
    sb.toString()
  }





}
