//给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。 
//
// 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。 
//
// 
//
// 示例 1: 
//
// 输入: nums = [4,2,3]
//输出: true



//解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
// 
//
// 示例 2: 
//
// 输入: nums = [4,2,1]
//输出: false
//解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
// 
//
// 
//
// 说明： 
//
// 
// 1 <= n <= 10 ^ 4 
// - 10 ^ 5 <= nums[i] <= 10 ^ 5 
// 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;
//Java：非递减数列
public class P665NonDecreasingArray{
    public static void main(String[] args) {
        Solution solution = new P665NonDecreasingArray().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        if (nums.length <= 2) {
            return true;
        }
        int left = 0, right =  nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] <= nums[left + 1]) {
                left++;
            }
            while (left < right && nums[right - 1] <= nums[right]) {
                right--;
            }
            return (right - left <= 1) && (left == 0 || right == nums.length - 1 || nums[left + 1] >= nums[left - 1] || nums[right + 1] >= nums[right - 1]);
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}