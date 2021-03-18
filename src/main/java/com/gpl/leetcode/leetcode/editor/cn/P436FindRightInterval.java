//给定一组区间，对于每一个区间 i，检查是否存在一个区间 j，它的起始点大于或等于区间 i 的终点，这可以称为 j 在 i 的“右侧”。 
//
// 对于任何区间，你需要存储的满足条件的区间 j 的最小索引，这意味着区间 j 有最小的起始点可以使其成为“右侧”区间。如果区间 j 不存在，则将区间 i 存
//储为 -1。最后，你需要输出一个值为存储的区间值的数组。 
//
// 注意: 
//
// 
// 你可以假设区间的终点总是大于它的起始点。 
// 你可以假定这些区间都不具有相同的起始点。 
// 
//
// 示例 1: 
//
// 
//输入: [ [1,2] ]
//输出: [-1]
//
//解释:集合中只有一个区间，所以输出-1。
// 
//
// 示例 2: 
//
// 
//输入: [ [3,4], [2,3], [1,2] ]
//输出: [-1, 0, 1]
//
//解释:对于[3,4]，没有满足条件的“右侧”区间。
//对于[2,3]，区间[3,4]具有最小的“右”起点;
//对于[1,2]，区间[2,3]具有最小的“右”起点。
// 
//
// 示例 3: 
//
// 
//输入: [ [1,4], [2,3], [3,4] ]
//输出: [-1, 2, -1]
//
//解释:对于区间[1,4]和[3,4]，没有满足条件的“右侧”区间。
//对于[2,3]，区间[3,4]有最小的“右”起点。
// 
// Related Topics 二分查找


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Java：寻找右区间
public class P436FindRightInterval{
    public static void main(String[] args) {
        Solution solution = new P436FindRightInterval().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        private int leftBound(int[][] intervals, int target, int left, int right, Map<int[], Integer> map) {
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (intervals[mid][0] == target) {
                    right = mid - 1;
                } else if (intervals[mid][0] > target) {
                    right = mid - 1;
                } else if (intervals[mid][0] < target) {
                    left = mid + 1;
                }
            }
            if (left >= intervals.length || intervals[left][0] < target) {
                return -1;
            }
            return map.get(intervals[left]);
        }

        public int[] findRightInterval(int[][] intervals) {
            if (intervals == null || intervals.length == 0) {
                return new int[0];
            }
            int len = intervals.length;
            Map<int[], Integer> map = new HashMap<>(); //注意这个用法,map的key可以为int[]
            for (int i = 0; i < len; i++) {
                map.put(intervals[i], i);
            }
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            int[] ans = new int[len];
            Arrays.fill(ans, -1);
            for (int i = 0; i < len - 1; i++) {
                int left = i + 1, right = len - 1;
                ans[map.get(intervals[i])] = leftBound(intervals, intervals[i][1], left, right, map);
            }
            return ans;
        }

        public int[] findRightInterval1(int[][] intervals) {
            if (intervals == null || intervals.length == 0) {
                return new int[0];
            }
            int len = intervals.length;
            Map<int[], Integer> map = new HashMap<>(); //注意这个用法,map的key可以为int[]
            for (int i = 0; i < len; i++) {
                map.put(intervals[i], i);
            }
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            int[] ans = new int[len];
            Arrays.fill(ans, -1);
            for (int i = 0; i < len - 1; i++) {
                int left = i + 1, right = len - 1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (intervals[mid][0] >= intervals[i][1]) {
                        right = mid -1 ;
                        ans[map.get(intervals[i])] = map.get(intervals[mid]);  //注意map.get(intervals[i]), 运行不到len-1,最后一个有一定为-1
                    } else {
                        left = mid + 1;
                    }
                }
            }
            return ans;
        }

    public int[] findRightInterval2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0];
        }
        int len = intervals.length;
        int[] ans = new int[len];
        for (int i = 0; i < len; i++) {
            int minVlaue = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < len; j++) {
                if (intervals[j][0] >= intervals[i][1] && minVlaue > intervals[j][0]) {
                    minIndex = j;
                    minVlaue = intervals[j][0];
                }
            }
            ans[i] = minIndex;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}