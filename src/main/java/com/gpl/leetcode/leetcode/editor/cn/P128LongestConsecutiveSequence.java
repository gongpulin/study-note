//给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。 
//
// 
//
// 进阶： 
//
// 
// 你可以设计并实现时间复杂度为 O(n) 的解决方案吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [100,4,200,1,3,2]
//输出：4
//解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。 
//
// 示例 2： 
//
// 
//输入：nums = [0,3,7,2,5,8,4,6,0,1]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 104 
// -231 <= nums[i] <= 231 - 1 
// 
// Related Topics 并查集 数组


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Java：最长连续序列
public class P128LongestConsecutiveSequence{
    public static void main(String[] args) {
        Solution solution = new P128LongestConsecutiveSequence().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {//[1, 2, 5, 6,7]
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int ans = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStack = 1;
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStack++;
                }
                ans = Math.max(ans, currentStack);
            }
        }
        return ans;
    }
        public int longestConsecutive1(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }
            Arrays.sort(nums);
            int left = 0, right = 1, ans = 1, len = nums.length;
            while (right < len) {
                if (nums[right] == nums[right - 1] + 1 || nums[right] == nums[right - 1]) {
                    right++;
                } else {
                    ans = Math.max(ans, nums[right - 1] - nums[left] + 1);
                    left = right;
                    right++;
                }
            }
            ans = Math.max(ans, nums[right - 1] - nums[left] + 1);
            return ans;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}