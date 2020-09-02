package com.gpl.flink.utils;

import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.runtime.state.FunctionInitializationContext;
import org.apache.flink.runtime.state.FunctionSnapshotContext;
import org.apache.flink.streaming.api.checkpoint.CheckpointedFunction;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.util.concurrent.TimeUnit;

/**
 * @author gongpulin
 * date 2020-06-29
 */
public class DataGenerator extends RichParallelSourceFunction implements CheckpointedFunction {
    private int subtaskIndex = -1;
    private volatile boolean isRunning = true;
    ListState<String> listState;

    @Override
    public void run(SourceContext sourceContext) throws Exception {
        while (isRunning) {
            TimeUnit.SECONDS.sleep(10);
            sourceContext.collect("data");
        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }

    @Override
    public void snapshotState(FunctionSnapshotContext context) throws Exception {
        listState.clear();
        listState.add("<" + subtaskIndex + "," + context.getCheckpointId() + ">");
        System.out.println("snapshotState subtask: " + subtaskIndex + " ---CheckPointId: " + context.getCheckpointId());
    }

    @Override
    public void initializeState(FunctionInitializationContext context) throws Exception {
        subtaskIndex = getRuntimeContext().getIndexOfThisSubtask();
        listState = context.getOperatorStateStore().getListState(new ListStateDescriptor<>("liststate",String.class));
        if (context.isRestored()) {
            for (String indexOfSubtaskState : listState.get()) {
                System.out.println("restore ListState currentSubtask: " + subtaskIndex + "restoreSubtask and restoreCheckPointId +" + indexOfSubtaskState);
            }
        }
        System.out.println("subtask: " + subtaskIndex + "complete restore");
    }
}
