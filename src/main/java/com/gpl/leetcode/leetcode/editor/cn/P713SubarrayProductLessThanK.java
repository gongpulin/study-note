//给定一个正整数数组 nums。 
//
// 找出该数组内乘积小于 k 的连续的子数组的个数。 
//
// 示例 1: 
//
// 
//输入: nums = [10,5,2,6], k = 100
//输出: 8
//解释: 8个乘积小于100的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]。
//需要注意的是 [10,5,2] 并不是乘积小于100的子数组。
// 
//
// 说明: 
//
// 
// 0 < nums.length <= 50000 
// 0 < nums[i] < 1000 
// 0 <= k < 10^6 
// 
// Related Topics 数组 双指针


package com.gpl.leetcode.leetcode.editor.cn;
//Java：乘积小于K的子数组
public class P713SubarrayProductLessThanK{
    public static void main(String[] args) {
        Solution solution = new P713SubarrayProductLessThanK().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 1) {
            return 0;
        }
        int window = 1, ans = 0;
        int left = 0, len = nums.length;
        for (int right = 0; right < len; right++) {
            window *= nums[right];
            while (window >= k) {
                window /= nums[left];
                left++;
            }
            ans += right - left + 1;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}