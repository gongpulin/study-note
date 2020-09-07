//找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。 
//
// 说明： 
//
// 
// 所有数字都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: k = 3, n = 7
//输出: [[1,2,4]]
// 
//
// 示例 2: 
//
// 输入: k = 3, n = 9
//输出: [[1,2,6], [1,3,5], [2,3,4]]
// 
// Related Topics 数组 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import org.apache.avro.generic.GenericData;

import java.util.ArrayList;
import java.util.List;

//Java：组合总和 III
public class P216CombinationSumIii{
    public static void main(String[] args) {
        Solution solution = new P216CombinationSumIii().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] used = new boolean[10];
        backtrack(ans, new ArrayList<>(), k, n, used, 1);
        return ans;
    }
    private void backtrack(List<List<Integer>> ans, List<Integer> path, int k, int n, boolean[] used, int start) {
        if (n < 0) {
            return;
        }
        if (n == 0 && path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i <= 9; i++) {
//            if (used[i]) {
//                continue;
//            }
            path.add(i);
//            used[i] = true;
            backtrack(ans, path, k, n - i, used, i+1);
            path.remove(path.size()-1);
//            used[i] = false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

//