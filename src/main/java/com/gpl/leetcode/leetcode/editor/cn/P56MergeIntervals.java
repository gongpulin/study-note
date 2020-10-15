//给出一个区间的集合，请合并所有重叠的区间。 
//
// 
//
// 示例 1: 
//
// 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: intervals = [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。 
//
// 
//
// 提示： 
//
// 
// intervals[i][0] <= intervals[i][1] 
// 
// Related Topics 排序 数组


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;

//Java：合并区间
public class P56MergeIntervals{
    public static void main(String[] args) {
        Solution solution = new P56MergeIntervals().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int[][] merge(int[][] intervals) {
            int[][] ans = new int[intervals.length][2];
            if (intervals == null) {
                return ans;
            }
            Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
            int index = -1;
            for (int[] interval : intervals) {
                if (index == -1 || interval[0] > ans[index][1]) {
                    ans[++index] = interval;
                } else {
                    ans[index][1] = Math.max(ans[index][1], interval[1]);
                }
            }
            return Arrays.copyOf(ans,index+1);
        }

    public int[][] merge1(int[][] intervals) {
        int[][] ans = new int[intervals.length][2];
        if (intervals == null) {
            return ans;
        }
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        int index = -1;
        for (int[] interval : intervals) {
            if (index == -1 || interval[0] > ans[index][1]) {
                ans[++index] = interval;
            } else {
                ans[index][1] = Math.max(ans[index][1], interval[1]);
            }
        }
        return Arrays.copyOf(ans, index+1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}