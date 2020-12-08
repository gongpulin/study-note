//给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。 
//
// 示例 1: 
//
// 输入: [1,12,-5,-6,50,3], k = 4
//输出: 12.75
//解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
// 
//
// 
//
// 注意: 
//
// 
// 1 <= k <= n <= 30,000。 
// 所给数据范围 [-10,000，10,000]。 
// 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;
//Java：子数组最大平均数 I
public class P643MaximumAverageSubarrayI{
    public static void main(String[] args) {
        Solution solution = new P643MaximumAverageSubarrayI().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || nums.length < k) {
            return 0.0;
        }
        double sum = 0.0;
        double ans = -100001; //Double.MIN_VALUE > -1
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (i < k - 1) {
                continue;
            }
            ans = Math.max(ans, sum / k);
            sum = sum - nums[i - k + 1];
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}