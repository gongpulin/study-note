//给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。如果不存在符合条件的子数组，返回
// 0。 
//
// 
//
// 示例： 
//
// 输入：s = 7, nums = [2,3,1,2,4,3]
//输出：2
//解释：子数组 [4,3] 是该条件下的长度最小的子数组。
// 
//
// 
//
// 进阶： 
//
// 
// 如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。 
// 
// Related Topics 数组 双指针 二分查找


package com.gpl.leetcode.leetcode.editor.cn;

import org.apache.flink.table.planner.expressions.In;

//Java：长度最小的子数组
public class P209MinimumSizeSubarraySum{
    public static void main(String[] args) {
        Solution solution = new P209MinimumSizeSubarraySum().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public int minSubArrayLen(int s, int[] nums) {
            if (nums == null) {
                return 0;
            }
            int ans = Integer.MAX_VALUE;
            int windowSum = 0;
            int left = 0, right = 0, len = nums.length;
            while (right < len) {
                windowSum += nums[right++];
                while (windowSum >= s) {
                    ans = Math.min(ans, right - left);
                    windowSum -= nums[left++];
                }
            }
            return ans == Integer.MAX_VALUE ? 0 : ans;
        }

















        public int minSubArrayLen1(int s, int[] nums) {
            int ans = Integer.MAX_VALUE;
            int len = nums.length;
            int left = 0, right = 0, windowSum = 0;
            while (right < len) {
                windowSum += nums[right++];
                while (windowSum >= s) {
                    ans = Math.min(ans, right - left);
                    windowSum -= nums[left++];
                }
            }
            return ans == Integer.MAX_VALUE ? 0 : ans;
        }
//        public int minSubArrayLen(int s, int[] nums) {
//            int ans = Integer.MAX_VALUE;
//            int len = nums.length;
//            int left = 0;
//            int right = 0;
//            int windowSum = 0;
//            while (right < len) {
//                while (right < len && windowSum < s) {
//                    windowSum += nums[right++];
//                }
//                while (left < right && windowSum >=s) {
//                    ans = Math.min(ans, right - left);
//                    windowSum -= nums[left++];
//                }
//            }
//            return ans == Integer.MAX_VALUE ? 0 : ans;
//        }

//    public int minSubArrayLen(int s, int[] nums) {
//        if (nums == null) {
//            return 0;
//        }
//        int ans = Integer.MAX_VALUE;
//        int len = nums.length;
//        for (int i = 0; i < len; i++) {
//            int sum = 0;
//            for (int j = i; j < len; j++) {
//                sum += nums[j];
//                if (sum >= s) {
//                    ans = Math.min(ans, j - i + 1);
//                    break;
//                }
//            }
//        }
//        return ans == Integer.MAX_VALUE ? 0 : ans;
//
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}