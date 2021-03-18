//给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c +
// d 的值与 target 相等？找出所有满足条件且不重复的四元组。 
//
// 注意： 
//
// 答案中不可以包含重复的四元组。 
//
// 示例： 
//
// 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
//
//满足要求的四元组集合为：
//[
//  [-1,  0, 0, 1],
//  [-2, -1, 1, 2],
//  [-2,  0, 0, 2]
//]
// 
// Related Topics 数组 哈希表 双指针


package com.gpl.leetcode.leetcode.editor.cn;

import com.google.inject.internal.util.$ImmutableSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：四数之和
public class P18FourSum{
    public static void main(String[] args) {
        Solution solution = new P18FourSum().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> ans = new ArrayList<>();
            if (nums == null) {
                return ans;
            }
            Arrays.sort(nums);
            int len = nums.length;
            for (int first = 0; first < len - 3; first++) {
                if (first > 0 && nums[first] == nums[first - 1]) {
                    continue;
                }
                for (int second = first + 1; second < len - 2; second++) {
                    if (second > first + 1 && nums[second] == nums[second - 1]) {
                        continue;
                    }
                    int third = second + 1;
                    int four = len - 1;
                    while(third < four) {
                        int sum = nums[first] + nums[second] + nums[third] + nums[four];
                        if (target == sum) {
                            List<Integer> item = new ArrayList<>();
                            item.add(nums[first]);
                            item.add(nums[second]);
                            item.add(nums[third]);
                            item.add(nums[four]);
                            ans.add(item);
                            while (third < four && nums[third] == nums[third+1]) {
                                third++;
                            }
                            while (third < four && nums[four] == nums[four-1]) {
                                four--;
                            }
                            third++;
                            four--;
                        } else if (target < sum) {
                            four--;
                        } else {
                            third++;
                        }
                    }
                }

            }
            return ans;
        }

//    public List<List<Integer>> fourSum(int[] nums, int target) {
//        List<List<Integer>> ans = new ArrayList<>();
//        if (nums == null) {
//            return ans;
//        }
//        Arrays.sort(nums);
//        int len = nums.length;
//        for (int first = 0; first < len - 3; first++) {
//            if (first > 0 && nums[first] == nums[first-1]) {
//                continue;
//            }
//            for (int second = first + 1; second < len - 2; second++) {
//                if (second > first + 1 && nums[second] == nums[second-1]) {
//                    continue;
//                }
//                for (int third = second + 1; third < len - 1; third++) {
//                    if (third > second + 1 && nums[third] == nums[third-1]) {
//                        continue;
//                    }
//                    for (int four = third + 1; four < len; four++) {
//                        if (four > third + 1 && nums[four] == nums[four-1]) {
//                            continue;
//                        }
//                        if (target == nums[first] + nums[second] + nums[third] + nums[four]) {
//                            List<Integer> item = new ArrayList<>();
//                            item.add(nums[first]);
//                            item.add(nums[second]);
//                            item.add(nums[third]);
//                            item.add(nums[four]);
//                            ans.add(item);
//                        }
//                    }
//                }
//            }
//        }
//        return ans;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}