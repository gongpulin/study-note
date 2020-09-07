//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：全排列 II
public class P47PermutationsIi{
    public static void main(String[] args) {
        Solution solution = new P47PermutationsIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public List<List<Integer>> permuteUnique(int[] nums) {
            List<List<Integer>> ans = new ArrayList<>();
            if (nums == null || nums.length == 0) {
                return ans;
            }
            Arrays.sort(nums);
            int len = nums.length;
            boolean[] used = new boolean[len];
            backtrack(ans, new ArrayList(), nums, len, used);
            return ans;
        }
        private void backtrack(List<List<Integer>> ans, List<Integer> path, int[] nums, int len, boolean[] used) {
            if (path.size() == len) {
                ans.add(new ArrayList(path));
                return;
            }
            for (int i = 0; i < len; i++) {
                if (used[i]) {
                    continue;
                }
                if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) {
                    continue;
                }
                path.add(nums[i]);
                used[i] = true;
                backtrack(ans, path, nums, len, used);
                used[i] = false;
                path.remove(path.size()-1);
            }
        }
//    public List<List<Integer>> permuteUnique(int[] nums) {
//        List<List<Integer>> ans = new ArrayList<>();
//        if (nums == null || nums.length == 0) {
//            return ans;
//        }
//        Arrays.sort(nums);
//        int len = nums.length;
//        boolean[] used = new boolean[len];
//        backtrack(ans, new ArrayList(), nums, len, 0, used);
//        return ans;
//    }
//    private void backtrack(List<List<Integer>> ans, List<Integer> path, int[] nums, int len, int depth, boolean[] used) {
//        if (depth == len) {
//            ans.add(new ArrayList(path));
//            return;
//        }
//        for (int i = 0; i < len; i++) {
//            if (used[i]) {
//                continue;
//            }
//            if (i > 0 && nums[i] == nums[i-1] && !used[i-1]) {
//                continue;
//            }
//            path.add(nums[i]);
//            used[i] = true;
//            backtrack(ans, path, nums, len, depth+1, used);
//            used[i] = false;
//            path.remove(path.size()-1);
//        }
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}