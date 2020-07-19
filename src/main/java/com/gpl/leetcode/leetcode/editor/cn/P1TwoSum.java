//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Java：两数之和
public class P1TwoSum{
    public static void main(String[] args) {
        Solution solution = new P1TwoSum().new Solution();
        // TO TEST
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        solution.twoSum1(nums,target);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = {-1,-1};
        if (nums == null || nums.length == 0 ) {
            return res;
        }
        Map<Integer,Integer> map = new HashMap(nums.length/2);
        for (int i = 0; i < nums.length; i++) {
            int left = target - nums[i];
            int leftIndex = map.getOrDefault(left,-1);
            if (leftIndex != -1) {
                res[0] = leftIndex;
                res[1] = i;
                break;
            } else {
                map.put(nums[i],i);
            }
        }
        System.out.println(res[0]+":"+res[1]);
        return res;
    }


    public int[] twoSum1(int[] nums, int target) {
        int[] res = {-1,-1};
        if (nums == null || nums.length == 0) {
            return res;
        }
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int leftIndex = map.getOrDefault(target-nums[i],-1);
            if (leftIndex != -1) {
                res[0] = leftIndex;
                res[1] = i;
                break;
            }
            map.put(nums[i],i);
        }
        System.out.println(res[0]+":"+res[1]);
        return res;
    }




}
//leetcode submit region end(Prohibit modification and deletion)

}