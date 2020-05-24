package com.gpl.spark

import org.apache.spark.sql.{SaveMode, SparkSession}

object ApiTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().appName("ApiTest").master("local[2]").enableHiveSupport().getOrCreate()
    val sc = spark.sparkContext
    import spark.implicits._
    val l =List(("1",1),("b",2)).toDF()


    /**
     * spark操作 mysql
     * https://dongkelun.com/2018/03/21/sparkMysql/
     */
    val mysqldf = spark.read.format("jdbc")
      .option("url","jdbc:mysql://10.55.202.173:3306/hive?useUnicode=true&characterEncoding=utf-8")
      .option("user","hive")
      .option("password","hive")
      .option("driver","com.mysql.jdbc.Driver")
      .option("numPartitions",1)
      .option("partitionColumn","PART_ID")
      .option("lowerBound",1)
      .option("upperBound",100)
      .option("dbtable","(select * from PARTITIONS limit 5) as mytable").load().show()

    val jsondf = spark.read.json("/user/hive/a.json")
//    jsondf.write.mode(SaveMode.Append).jdbc("asxa")

    spark.close()
  }


}
