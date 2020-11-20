package com.gpl.flink.partitioner;

import org.apache.flink.api.common.functions.Partitioner;

import java.util.Random;

/**
 * @author gongpulin
 * date 2020-09-25
 */
public class PartitionCustomExample implements Partitioner<String> {
    private Random random = new Random();
    @Override
    public int partition(String key, int numPartitions) {
        int randomNum = random.nextInt(numPartitions / 2);
        return randomNum;
    }
}
