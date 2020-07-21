//输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。 
//
// 要求时间复杂度为O(n)。 
//
// 
//
// 示例1: 
//
// 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10^5 
// -100 <= arr[i] <= 100 
// 
//
// 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/ 
//
// 
// Related Topics 分治算法 动态规划


package com.gpl.leetcode.leetcode.editor.cn;
//Java：连续子数组的最大和
public class Offer_42LianXuZiShuZuDeZuiDaHeLcof{
    public static void main(String[] args) {
        Solution solution = new Offer_42LianXuZiShuZuDeZuiDaHeLcof().new Solution();
        // TO TEST
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(solution.maxSubArray(nums));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

/**
 * 状态方程：   dp[i] = dp[i-1] + nums[i]  ;     dp[i-1] > 0
 *             dp[i] = nums[i]            ;     dp[i-1] < 0
 */

class Solution {

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        int sum = nums[0]; // 临时sum
        int res = nums[0]; //最终结果
        for (int i = 1; i < nums.length; i++) {
            sum = sum > 0 ? sum + nums[i] : nums[i];
            res = res > sum ? res : sum;

        }
        return res;
    }







    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = nums[0]; //临时sum
        int res = nums[0]; //最终结果
        for (int i = 1; i < nums.length; i++) {
            sum = sum > 0 ? sum + nums[i] : nums[i];
            res = res > sum ? res : sum;
        }
        return res;
    }






}
//leetcode submit region end(Prohibit modification and deletion)

}