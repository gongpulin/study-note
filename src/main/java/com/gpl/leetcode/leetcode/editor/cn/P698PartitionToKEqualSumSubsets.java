//给定一个整数数组 nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。 
//
// 示例 1： 
//
// 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
//输出： True
//说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= len(nums) <= 16 
// 0 < nums[i] < 10000 
// 
// Related Topics 递归 动态规划


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Java：划分为k个相等的子集
public class P698PartitionToKEqualSumSubsets{
    public static void main(String[] args) {
        Solution solution = new P698PartitionToKEqualSumSubsets().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public boolean canPartitionKSubsets(int[] nums, int k) {
            int N = nums.length;
            Arrays.sort(nums);
            int sum = Arrays.stream(nums).sum();
            int target = sum / k;
            if (sum % k > 0 || nums[N - 1] > target) {
                return false;
            }

            boolean[] dp = new boolean[1 << N];
            dp[0] = true;
            int[] total = new int[1 << N];

            for (int state = 0; state < (1 << N); state++) {
                if (!dp[state]) continue;
                for (int i = 0; i < N; i++) {
                    int future = state | (1 << i);
                    if (state != future && !dp[future]) {
                        if (nums[i] <= target - (total[state] % target)) {
                            dp[future] = true;
                            total[future] = total[state] + nums[i];
                        } else {
                            break;
                        }
                    }
                }
            }
            return dp[(1 << N) - 1];
        }




        //错误答案
    public boolean canPartitionKSubsets1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        Map<Integer, Integer> times = new HashMap();
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
            times.put(num, times.getOrDefault(num, 0) + 1);
        }
        int sum = totalSum / k;
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}