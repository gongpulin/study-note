//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics 位运算 数组 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：子集
public class P78Subsets{
    public static void main(String[] args) {
        Solution solution = new P78Subsets().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        boolean[] used = new boolean[nums.length];
        backtrack(ans, new ArrayList(), nums, 0, used);
        return ans;
    }
    private void backtrack(List<List<Integer>> ans, List<Integer> path, int[] nums, int start, boolean[] used) {
        ans.add(new ArrayList(path));

        for (int i = start; i < nums.length; i++) {
//            if (used[i]) {
//                continue;
//            }
            path.add(nums[i]);
//            used[i] = true;
            backtrack(ans, path, nums, i+1, used);
            path.remove(path.size()-1);
//            used[i] = false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}