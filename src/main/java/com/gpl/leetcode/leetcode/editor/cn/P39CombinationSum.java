//给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的数字可以无限制重复被选取。 
//
// 说明： 
//
// 
// 所有数字（包括 target）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1： 
//
// 输入：candidates = [2,3,6,7], target = 7,
//所求解集为：
//[
//  [7],
//  [2,2,3]
//]
// 
//
// 示例 2： 
//
// 输入：candidates = [2,3,5], target = 8,
//所求解集为：
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//] 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都是独一无二的。 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import com.google.inject.internal.util.$ObjectArrays;

import java.util.ArrayList;
import java.util.List;

//Java：组合总和
public class P39CombinationSum{
    public static void main(String[] args) {
        Solution solution = new P39CombinationSum().new Solution();
        // TO TEST
        int[] candidates = {2,3,5};
        int target = 8;
        solution.combinationSum(candidates, target);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        /**
         *结果：[2, 2, 2, 2]
         * [2, 3, 3]
         * [3, 5]
         */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backtrack(ans, new ArrayList(),  target, candidates, 0);
        return ans;
    }
    private void backtrack(List<List<Integer>> ans, List<Integer> path, int target, int[] candidates, int start) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            ans.add(new ArrayList<>(path));
        }
        for (int i = start; i < candidates.length; i++) {
            path.add(candidates[i]);
            backtrack(ans, path, target - candidates[i], candidates, i);
            path.remove(path.size()-1);
        }
    }





//    private void backtrack(List<List<Integer>> ans, List<Integer> path, int target, int[] candidates, int start) {
//        if (target < 0) {
//            return;
//        }
//        if (target == 0) {
//            ans.add(new ArrayList(path));
////            System.out.println(path.toString());
//            return;
//        }
//        for (int i = start; i < candidates.length; i++) {
//            path.add(candidates[i]);
//            backtrack(ans, path, target-candidates[i], candidates, i);
//            path.remove(path.size()-1);
//        }
//    }

        /**
         *
         * 错误答案：有重复列表
         * [2, 2, 2, 2]
         * [2, 3, 3]
         * [3, 2, 3]
         * [3, 3, 2]
         * [3, 5]
         * [5, 3]
         */
//    public List<List<Integer>> combinationSum(int[] candidates, int target) {
//        List<List<Integer>> ans = new ArrayList<>();
//        backtrack(ans, new ArrayList(),  target, candidates);
//        return ans;
//    }
//    private void backtrack(List<List<Integer>> ans, List<Integer> path, int target,int[] candidates) {
//        if (target < 0) {
//            return;
//        }
//        if (target == 0) {
//            ans.add(new ArrayList(path));
//           // System.out.println(path);
//            return;
//        }
//        for (int c : candidates) {
//            path.add(c);
//            backtrack(ans, path,  target-c, candidates);
//            path.remove(path.size()-1);
//        }
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}