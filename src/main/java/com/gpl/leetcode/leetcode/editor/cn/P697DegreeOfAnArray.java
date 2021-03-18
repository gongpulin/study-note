//给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。 
//
// 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。 
//
// 示例 1: 
//
// 
//输入: [1, 2, 2, 3, 1]
//输出: 2
//解释: 
//输入数组的度是2，因为元素1和2的出现频数最大，均为2.
//连续子数组里面拥有相同度的有如下所示:
//[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
//最短连续子数组[2, 2]的长度为2，所以返回2.
// 
//
// 示例 2: 
//
// 
//输入: [1,2,2,3,1,4,2]
//输出: 6
// 
//
// 注意: 
//
// 
// nums.length 在1到50,000区间范围内。 
// nums[i] 是一个在0到49,999范围内的整数。 
// 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

//Java：数组的度
public class P697DegreeOfAnArray{
    public static void main(String[] args) {
        Solution solution = new P697DegreeOfAnArray().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int findShortestSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        Map<Integer, Integer> left = new HashMap<>();
        Map<Integer, Integer> right = new HashMap<>();
        Map<Integer, Integer> count = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (left.get(nums[i]) == null) {
                left.put(nums[i], i);
            }
            right.put(nums[i], i);
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
        }
        int degree = Collections.max(count.values());
        int ans = len;
        for (int key : count.keySet()) {
            if (count.get(key) == degree) {
                ans = Math.min(ans, right.get(key) - left.get(key) + 1);
            }
        }
        return ans;

    }

        /**
         * 错误答案，没考虑到可能有多个数据出现次数都是最大的，例如
         * 测试用例:[2,1,1,2,1,3,3,3,1,3,1,3,2]
         * 			测试结果:10
         * 			期望结果:7
         */

    public int findShortestSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        Map<Integer, Integer> ctMap = new HashMap<>();
        int maxCt = 0, maxCtNum = nums[0];
        for (int i = 0; i < len; i++) {
            int ct = ctMap.getOrDefault(nums[i], 0) + 1;
            ctMap.put(nums[i], ct);
            if (ct > maxCt) {
                maxCtNum = nums[i];
                maxCt = ct;
            }
        }
        int left = 0, right = len - 1;
        while (left <= right && nums[left] != maxCtNum) {
            left++;
        }
        while (left <= right && nums[right] != maxCtNum) {
            right--;
        }
        int ans = right - left + 1;
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}