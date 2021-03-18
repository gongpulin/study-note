//给定一个整数数组和一个整数 k，你需要在数组里找到不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。 
//
// 这里将 k-diff 数对定义为一个整数对 (nums[i], nums[j])，并满足下述全部条件： 
//
// 
// 0 <= i, j < nums.length 
// i != j 
// |nums[i] - nums[j]| == k 
// 
//
// 注意，|val| 表示 val 的绝对值。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3, 1, 4, 1, 5], k = 2
//输出：2
//解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
//尽管数组中有两个1，但我们只应返回不同的数对的数量。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1, 2, 3, 4, 5], k = 1
//输出：4
//解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5)。
// 
//
// 示例 3： 
//
// 
//输入：nums = [1, 3, 1, 5, 4], k = 0
//输出：1
//解释：数组中只有一个 0-diff 数对，(1, 1)。
// 
//
// 示例 4： 
//
// 
//输入：nums = [1,2,4,4,3,3,0,9,2,3], k = 3
//输出：2
// 
//
// 示例 5： 
//
// 
//输入：nums = [-1,-2,-3], k = 1
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 104 
// -107 <= nums[i] <= 107 
// 0 <= k <= 107 
// 
// Related Topics 数组 双指针


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.*;

//Java：数组中的 k-diff 数对
public class P532KDiffPairsInAnArray{
    public static void main(String[] args) {
        Solution solution = new P532KDiffPairsInAnArray().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
//    public int findPairs1(int[] nums, int k) {
//        if (nums == null || nums.length == 0) {
//            return 0;
//        }
//        Arrays.sort(nums);
//        int left = 0, len = nums.length;
//        int ans = 0;
//
//    }

        /**
         * 注意 测试用例:[1,3,1,5,4] 0
         */
        public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int left = 0, len = nums.length;
        int ans = 0;
        while (left < len) {
            if (binarySearch(nums, left + 1, len - 1, nums[left] + k)) {
                ans++;
            }
            left++;
            while (left < len && nums[left] == nums[left - 1]) {
                left++;
            }
        }
        return ans;
    }
    private boolean binarySearch(int[] nums, int left, int right, int target) {
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}