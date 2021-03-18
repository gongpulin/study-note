//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。 
//
// 
//
// 示例 1: 
//
// 输入: [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 输入: [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
// Related Topics 数组 动态规划


package com.gpl.leetcode.leetcode.editor.cn;
//Java：乘积最大子数组
public class P152MaximumProductSubarray{
    public static void main(String[] args) {
        Solution solution = new P152MaximumProductSubarray().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int maxProduct(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int len = nums.length;
        int maxF = nums[0], minF = nums[0], ans = nums[0];
        for (int i = 1; i < len; i++) {
            int ma = maxF, mi = minF;
            maxF = Math.max(ma * nums[i], Math.max(mi * nums[i], nums[i]));
            minF = Math.min(mi * nums[i], Math.min(ma * nums[i], nums[i]));
            ans = Math.max(ans, maxF);
        }
        return ans;
    }

        public int maxProduct1(int[] nums) {
            if (nums == null) {
                return 0;
            }
            int len = nums.length;
            int[] maxF = new int[len];
            int[] minF = new int[len];
            System.arraycopy(nums, 0, maxF, 0, len);
            System.arraycopy(nums, 0, minF, 0, len);
            for (int i = 1; i < len; i++) {
                maxF[i] = Math.max(maxF[i - 1] * nums[i], Math.max(minF[i - 1] * nums[i], nums[i]));
                minF[i] = Math.min(minF[i - 1] * nums[i], Math.min(maxF[i - 1] * nums[i], nums[i]));
            }
            int ans = maxF[0];
            for (int i = 1; i < len; i++) {
                ans = Math.max(ans, maxF[i]);
            }
            return ans;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}