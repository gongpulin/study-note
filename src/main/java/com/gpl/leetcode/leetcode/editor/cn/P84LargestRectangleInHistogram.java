//给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。 
//
// 求在该柱状图中，能够勾勒出来的矩形的最大面积。 
//
// 
//
// 
//
// 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。 
//
// 
//
// 
//
// 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。 
//
// 
//
// 示例: 
//
// 输入: [2,1,5,6,2,3]
//输出: 10 
// Related Topics 栈 数组


package com.gpl.leetcode.leetcode.editor.cn;
//Java：柱状图中最大的矩形
public class P84LargestRectangleInHistogram{
    public static void main(String[] args) {
        Solution solution = new P84LargestRectangleInHistogram().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null) {
            return -1;
        }
        int ans = 0;
        int len = heights.length;
//        if (len == 1) {
//            return heights[0];
//        }
        for (int i = 0; i < len; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < len; j++) {
                minHeight = Math.min(minHeight, heights[j]);
//                for (int p = i; p < j; p++) {
//                    if (heights[p] < minHeight) {
//                        minHeight = heights[p];
//                    }
//                }
                int area = (j - i + 1) * minHeight;
                ans = Math.max(ans, area);
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}