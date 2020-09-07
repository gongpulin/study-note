//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：组合
public class P77Combinations{
    public static void main(String[] args) {
        Solution solution = new P77Combinations().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] used = new boolean[n+1];
        backtrack(ans, new ArrayList<>(), n, k, used, 1);
        return ans;
    }
    private void backtrack(List<List<Integer>> ans, List<Integer> path, int n, int k, boolean[] used, int start) {
        if (path.size() == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n; i++) {
//            if (used[i]) {
//                continue;
//            }
            path.add(i);
//            used[i] = true;
            backtrack(ans, path, n, k, used, i+1);
            path.remove(path.size()-1);
//            used[i] = false;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}