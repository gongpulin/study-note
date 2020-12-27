//给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。 
//
// 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那
//么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,5,4,7]
//输出：3
//解释：最长连续递增序列是 [1,3,5], 长度为3。
//尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。 
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2]
//输出：1
//解释：最长连续递增序列是 [2], 长度为1。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 104 
// -109 <= nums[i] <= 109 
// 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;
//Java：最长连续递增序列
public class P674LongestContinuousIncreasingSubsequence{
    public static void main(String[] args) {
        Solution solution = new P674LongestContinuousIncreasingSubsequence().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return 1;
        }
        int ans = 0, left = 0;
        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] <= nums[i - 1]) {
                left = i;
            }
            ans = Math.max(ans, i - left + 1);
        }
        return ans;
    }
    public int findLengthOfLCIS1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) {
            return 1;
        }
        int left = 0, right = 1, ans = 1;
        while (right < len) {
            if (nums[right] > nums[right - 1]) {
                right++;
            } else {
                ans = Math.max(ans, right - left);
                left = right;
                right++;
            }
        }
        if (nums[len - 1] > nums[len - 2]) {
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}