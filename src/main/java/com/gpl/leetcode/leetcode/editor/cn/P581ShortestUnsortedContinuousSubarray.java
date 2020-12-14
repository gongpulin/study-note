//给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。 
//
// 你找到的子数组应是最短的，请输出它的长度。 
//
// 示例 1: 
//
// 
//输入: [2, 6, 4, 8, 10, 9, 15]
//输出: 5
//解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
// 
//
// 说明 : 
//
// 
// 输入的数组长度范围在 [1, 10,000]。 
// 输入的数组可能包含重复元素 ，所以升序的意思是<=。 
// 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;

//Java：最短无序连续子数组
public class P581ShortestUnsortedContinuousSubarray{
    public static void main(String[] args) {
        Solution solution = new P581ShortestUnsortedContinuousSubarray().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] copyNums = nums.clone();
        Arrays.sort(copyNums);
        int left = nums.length, right = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] != copyNums[i]) {
                left = Math.min(left, i);
                right = Math.max(right, i);
            }
        }
        if (right - left >= 0) {
            return right - left + 1;
        } else {
            return 0;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}