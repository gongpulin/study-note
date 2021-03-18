//给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。 
//
// 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下： 
//
// 
// "123" 
// "132" 
// "213" 
// "231" 
// "312" 
// "321" 
// 
//
// 给定 n 和 k，返回第 k 个排列。 
//
// 说明： 
//
// 
// 给定 n 的范围是 [1, 9]。 
// 给定 k 的范围是[1, n!]。 
// 
//
// 示例 1: 
//
// 输入: n = 3, k = 3
//输出: "213"
// 
//
// 示例 2: 
//
// 输入: n = 4, k = 9
//输出: "2314"
// 
// Related Topics 数学 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import com.google.inject.internal.asm.$FieldVisitor;

import java.util.ArrayList;
import java.util.List;

//Java：第k个排列
public class P60PermutationSequence{
    public static void main(String[] args) {
        Solution solution = new P60PermutationSequence().new Solution();
        // TO TEST
        solution.getPermutation(4,9);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public String getPermutation(int n, int k) {
            List<String> ans = new ArrayList<>();
            boolean[] used = new boolean[n+1];
            backtrack(ans, new StringBuilder(), n, k, used);
//            return ans.get(k-1);
        }
        private void backtrack(List<String> ans, StringBuilder path, int n, int k, boolean[] used) {
            if (path.length() == n) {
                ans.add(path.toString());
            }
            for (int i = 1; i <= n; i++) {
                if (used[i]) {
                    continue;
                }
                used[i] = true;
                path.append(i);
                backtrack(ans, path, n, k, used);
                path.deleteCharAt(path.length()-1);
                used[i] = false;
            }
        }


//    public String getPermutation(int n, int k) {
//        List<List<Integer>> ans = new ArrayList<>();
//        boolean[] used = new boolean[n+1];
//        backtrack(ans, new ArrayList(), n, k, used);
//        StringBuilder sb = new StringBuilder();
//        for (int item : ans.get(k-1)) {
//            sb.append(item);
//        }
//        System.out.println(sb.toString());
//        return sb.toString();
//    }
//    private void backtrack(List<List<Integer>> ans, List<Integer> path, int n, int k, boolean[] used) {
//        if (path.size() == n) {
//            ans.add(new ArrayList<>(path));
//            return;
//        }
//        for (int i = 1; i <= n; i++) {
//            if (used[i]) {
//                continue;
//            }
//            path.add(i);
//            used[i] = true;
//            backtrack(ans, path, n, k, used);
//            path.remove(path.size()-1);
//            used[i] = false;
//        }
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}