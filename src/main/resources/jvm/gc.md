##### 1、什么时候触发Minor GC
   大多数情况下，对象直接进入年轻代的Eden区进行分配，如果Eden区没有足够的空间就触发Minor GC,  Minor GC
处理的区域只有年轻代。因为大多数对象在短时间内是可以回收的，Minor GC后只有极少数的对象能够存活下来而被移送到
S0区（复制算法）。当出发下一次Minor GC时会将Eden区和S0区存活的对象移送到S1区，同时清空Eden区和S0区，当
再次触发Minor GC时，这时处理的区域就变成了Eden区和S1区（即S0和S1区进行交换）
##### 2、什么时候触发Full GC
   四种情况触发Full GC
    1、分配担保失败。
    2、老年代的内存使用达到一定阈值（可以通过参数调整），直接触发Full GC.
    3、Meta space 元数据区空间不足会进行扩容，当扩容到 -XX:MetaspaceSize参数指定的值时触发Full GC,
    4、调用System.gc()或者Runtime.gc()时被显式调用时触发Full GC.
    