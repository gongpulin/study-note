package com.gpl.flink.app.assistant
import com.gpl.util.TimeHandler
import org.apache.flink.configuration.Configuration
import org.apache.flink.streaming.api.functions.source.{RichSourceFunction, SourceFunction}
import org.apache.flink.types.Row
/**
 * @author gongpulin
 *         date 2021-01-05
 */
class OrcFileFlinkSource(filePath: String,
                         includeFileList: List[String],
                         duration: Long) extends RichSourceFunction[Row]{

  //  val path = "src/main/scala/Test/Flink/part-00001-ded8ef18-b70f-409c-9086-578ab2588190-c000"
  //  val path = "hdfs://hadoop102:9000/tmp/part-00001-ded8ef18-b70f-409c-9086-578ab2588190-c000"
  val emptyStr = ""
  var lastLoadDate = emptyStr
  val prefix = filePath
  // "hdfs://ns/user/hive/warehouse/table1/oedate="
  val oneDayMillis = 86400000L

  override def open(parameters: Configuration): Unit = {
    super.open(parameters)
  }

  override def run(ctx: SourceFunction.SourceContext[Row]) = {
    while(true){
      // 初次启动或跨天
      if(lastLoadDate.equals(emptyStr) || TimeHandler.tranTimeStampToDayStr(System.currentTimeMillis() - oneDayMillis * 2).equals(lastLoadDate)){
        lastLoadDate = TimeHandler.tranTimeStampToDayStr(System.currentTimeMillis() - oneDayMillis)
        System.out.println(" --> " + System.currentTimeMillis() + " --> " + prefix + lastLoadDate)
        Utils.readOrc(prefix + lastLoadDate,includeFileList,ctx)
      }
      Thread.sleep(duration)
    }
  }

  override def cancel() = {

  }


}
