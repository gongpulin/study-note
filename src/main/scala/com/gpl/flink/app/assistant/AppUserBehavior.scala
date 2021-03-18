package com.gpl.flink.app.assistant

import com.gpl.util.TODoris
import org.apache.flink.api.common.typeinfo.TypeInformation
import org.apache.flink.api.java.typeutils.RowTypeInfo
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.table.api._
import org.apache.flink.table.api.bridge.scala._
import org.apache.flink.types.Row

import scala.util.Random

/**
 * @author gongpulin
 *         date 2021-01-05
 */
object AppUserBehavior {
  def main(args: Array[String]): Unit = {
    val param = Utils.parseArgs(args)
    val env = Utils.getExecutionEnvironment(param)
    val streamTableEnv = Utils.getStreamTableEnv(env, param)

    val prop = Utils.getKafkaConsumerParam(param)

    val parsedLogStream = env
      .addSource(KafkaUtil.getConsumerObj(param.getProperty("topic"),prop,param.getProperty("startFrom")))
      .uid("app-kafka-source-uid").name("app-kafka-source")
      .setParallelism(param.getProperty("sourceParallel").toInt)
      .process(new LogParseProcessFunction())
      .uid("app-logparse-function-uid").name("app-logparse-function")
      .setParallelism(param.getProperty("sourceParallel").toInt)

    // 读取人员信息 orc 文件 source
    val ryxxOrcStream = env.addSource(new OrcFileFlinkSource(param.getProperty("ryxx_orc_path"),param.getProperty("includeFileList").split(",").toList,param.getProperty("orcLoadDuration").toLong))(Types.ROW(Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING(),Types.STRING()))
      .map(row => {
        val r = row.getField(3)
        if (r != null) {
          (r.toString, row)
        } else {
          ("unknow",row)
        }
      }).filter(row => row._1 != "unknow")

    /**
     * 日志DataStream connect 维度人员信息orc格式Stream
     */
    val appUserBehaviorDStream = parsedLogStream.connect(ryxxOrcStream)
      .keyBy(0, 0)
      .process(new RyxxConnectProcessFunction())(new RowTypeInfo(
        TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),
        TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),
        TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),
        TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),
        TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),
        TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),
        TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),
        TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String]),TypeInformation.of(classOf[String])))
      .uid("app-connect-function-uid").name("app-connect-function")
    val appUserBehaviorTable = streamTableEnv.fromDataStream(appUserBehaviorDStream,'Event,'APP_VERSION,'OS,'OS_VERSION,'EventDuration,'SOURCE_CODE,'SOURCE_NAME,'RYID,'XM,'NL,'RYBH,'GMSFHM,'CSRQ,'CKJGID,'CKSZBM,'RYLBID,'RYLB,'RYLBPX,'RYFLID,'RYFL,'RYFLPX,'ZWJBID,'ZWJB ,'ZWJBPX,
      'GZID,'GZ,'GZPX,'GWMCID,'GWMC,'GWMCPX,'GRPYDJID,'GRPYDJ,'GRPYDJPX,'XBID,'XB,'XBPX,'MZID,'MZ,'MZPX,'ZZMMID,'ZZMM,'ZZMMPX,'DWID,'DWMC,'DWPX,'CDID,'CDMC,'CDPX,'XLID,'XLMC,'XLPX,'PROCTIME.proctime())

    //      appUserBehaviorTable.toTable(streamTableEnv,'Event,'APP_VERSION,'OS,'OS_VERSION,'EventDuration,'SOURCE_CODE,'SOURCE_NAME,'RYID,'XM,'NL,'RYBH,'GMSFHM,'CSRQ,'CKJGID,'CKSZBM,'RYLBID,'RYLB,'RYLBPX,'RYFLID,'RYFL,'RYFLPX,'ZWJBID,'ZWJB ,'ZWJBPX,
    //      'GZID,'GZ,'GZPX,'GWMCID,'GWMC,'GWMCPX,'GRPYDJID,'GRPYDJ,'GRPYDJPX,'XBID,'XB,'XBPX,'MZID,'MZ,'MZPX,'ZZMMID,'ZZMM,'ZZMMPX,'DWID,'DWMC,'DWPX,'CDID,'CDMC,'CDPX,'XLID,'XLMC,'XLPX,'PROCTIME.proctime())

    streamTableEnv.createTemporaryView("appUserBehaviorTable", appUserBehaviorTable)

    //    val at = streamTableEnv.sqlQuery("select * from appUserBehaviorTable")
    //    streamTableEnv.toAppendStream[Row](at).print().setParallelism(1)

    val appUserBehaviorRes = streamTableEnv.sqlQuery(
      """
        |SELECT CAST(CURRENT_DATE as VARCHAR) as RQ,SOURCE_CODE,SOURCE_NAME,
        | RYID,XM,NL,RYBH,GMSFHM,CSRQ,CKJGID,CKSZBM,RYLBID,RYLB,RYLBPX,RYFLID,RYFL,RYFLPX,ZWJBID,ZWJB ,ZWJBPX,
        | GZID,GZ,GZPX,GWMCID,GWMC,GWMCPX,GRPYDJID,GRPYDJ,GRPYDJPX,XBID,XB,XBPX,MZID,MZ,MZPX,ZZMMID,ZZMM,ZZMMPX,
        | DWID,DWMC,DWPX,CDID,CDMC,CDPX,XLID,XLMC,XLPX,
        | APP_VERSION, OS, OS_VERSION, sum(CASE WHEN Event='AppStart' THEN 1 else 0 end) as VISIT_NUM,
        | sum(CASE WHEN Event='AppViewScreen' THEN 1 else 0 end) as PAGE_VIEW_NUM,
        | sum(CASE WHEN Event='AppEnd' THEN CAST(EventDuration as DOUBLE) else 0 end) as VISIT_TIME_NUM,
        | CAST('0' as VARCHAR) as ELEMENT_CLICK_NUM,CAST('0' as VARCHAR) as PAGE_FLOW_NUM,CAST('0' as VARCHAR) as REGISTE_NUM,CAST(CURRENT_TIMESTAMP as bigint) as wrtime
        | from appUserBehaviorTable where RYBH is not null and RYBH <> '' group by TUMBLE(PROCTIME, INTERVAL '5' MINUTES),SOURCE_CODE,SOURCE_NAME,
        | RYID,XM,NL,RYBH,GMSFHM,CSRQ,CKJGID,CKSZBM,RYLBID,RYLB,RYLBPX,RYFLID,RYFL,RYFLPX,ZWJBID,ZWJB ,ZWJBPX,
        | GZID,GZ,GZPX,GWMCID,GWMC,GWMCPX,GRPYDJID,GRPYDJ,GRPYDJPX,XBID,XB,XBPX,MZID,MZ,MZPX,ZZMMID,ZZMM,ZZMMPX,
        | DWID,DWMC,DWPX,CDID,CDMC,CDPX,XLID,XLMC,XLPX,
        | APP_VERSION, OS, OS_VERSION
        |""".stripMargin
    )


    streamTableEnv.toAppendStream[Row](appUserBehaviorRes)
      .map(x => (new Random().nextInt(param.getProperty("sinkParallelism").toInt), Utils.row2StringDoris(x)))
      .uid("app-sinkDoris-map-uid").name("app-sinkDoris-map")
      .setParallelism(param.getProperty("sinkMapParallel").toInt)
      .keyBy(_._1)
      .timeWindow(Time.milliseconds(param.getProperty("dorisAggDuration").toLong))
      .process(new WinProFunction()).uid("app-sinkDoris-window-uid").name("app-sinkDoris-window")
      .setParallelism(param.getProperty("sinkParallelism").toInt)
      .addSink(new TODoris(
        Map("max_filter_ratio" -> s"${param.getProperty("filterRatio")}","columns" -> param.getProperty("userBehaviorTableName"),"column_separator" -> "\t"),
        param.getProperty("dbName"),param.getProperty("userName"),param.getProperty("password"),
        param.getProperty("tableName"),param.getProperty("randomFeId").toBoolean,param.getProperty("maxFeId").toInt,
        param.getProperty("fePort").toInt,param.getProperty("debug").toBoolean)
      )
      .uid("app-sinkDoris-uid").name("app-sinkDoris")
      .setParallelism(param.getProperty("sinkParallelism").toInt)




    env.execute("AppUserBehavior")
  }
}
