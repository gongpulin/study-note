package com.gpl.flink.hotitem;

/**
 * @author gongpulin
 * date 2020-06-23
 */
public class ItemViewCount {
    public long itemId;
    public long windowEnd;
    public long viewCount;

    public static ItemViewCount of(long itemId, long windowEnd, long viewCount) {
        ItemViewCount result = new ItemViewCount();
        result.itemId = itemId;
        result.windowEnd = windowEnd;
        result.viewCount = viewCount;
        return result;
    }
}
