# ORC
ORC全称是（Optimized Row Columnar）,ORC文件格式是一种Hadoop生态圈中的列式存储格式，它的产生早在2013年初，
最初产生自Hive,用于降低Hadoop数据存储空间和加速Hive查询速度，和Parquet类似，它并不是一个单纯的列式存储格式，
仍然是首相根据行组切割整个表，在每个行组内进行按列存储，ORC文件是自描述的，它的元数据使用Protocol Buffers序列化，
并且文件中的数据尽可能的压缩以降低存储空间的消耗，目前也被Spark SQL,Presto等查询引擎支持，但是Impala目前对于ORC没有
支持，仍然使用Parquet作为主要的列式存储格式。2015年ORC项目被Apache项目基金会提升为顶级项目。
1、ORC是列式存储，有多种文件压缩方式，并且有很高的压缩比
2、文件是可切分的，因此在Hive中使用ORC作为表的文件存储格式，不仅节省HDFS的存储资源，查询任务的输入数据量减少
使用的Maptask也就减少。
3、提供了多种索引，row group index, bloom filter index.
4、ORC可以支持复杂的数据结构（比如Map)

