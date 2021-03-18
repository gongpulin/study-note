//给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。 
//
// 返回 s 所有可能的分割方案。 
//
// 示例: 
//
// 输入: "aab"
//输出:
//[
//  ["aa","b"],
//  ["a","a","b"]
//] 
// Related Topics 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：分割回文串
public class P131PalindromePartitioning{
    public static void main(String[] args) {
        Solution solution = new P131PalindromePartitioning().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public List<List<String>> partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        int len = s.length();
        //dp[i][j] 表示是s[i][j]是否为回文串
        boolean dp[][] = new boolean[len][len];
        for (int right = 0; right < len; right++) {
            for (int left = 0; left <= right; left++) {
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left+1][right-1])) {
                    dp[left][right] = true;
                }
            }
        }
        backtrack(ans, new ArrayList<>(), s, 0, dp);
        return ans;
    }
    private void backtrack(List<List<String>> ans, List<String> path, String s, int start, boolean[][] dp) {
        if (start == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (!dp[start][i]) {
                continue;
            }
            path.add(s.substring(start, i+1));
            backtrack(ans, path, s, i+1, dp);
            path.remove(path.size()-1);
        }
    }




//    public List<List<String>> partition(String s) {
//        List<List<String>> ans = new ArrayList<>();
//        if (s == null || s.length() == 0) {
//            return ans;
//        }
//        backtrack(ans, new ArrayList<>(), s, 0);
//        return ans;
//    }
//    private void backtrack(List<List<String>> ans, List<String> path, String s, int start) {
//        if (start == s.length()) {
//            ans.add(new ArrayList<>(path));
//            return;
//        }
//        for (int i = start; i < s.length(); i++) {
//            if (!isPalindrime(s, start, i)) {
//                continue;
//            }
//            path.add(s.substring(start, i+1));
//            backtrack(ans, path, s, i+1);
//            path.remove(path.size()-1);
//        }
//    }
//    private boolean isPalindrime(String s, int left, int right) {
//        if (s == null || s.length() == 0) {
//            return false;
//        }
//        int len = s.length();
//        if (len == 1) {
//            return true;
//        }
//        while (left < right) {
//            if (s.charAt(left) != s.charAt(right)) {
//                return false;
//            } else {
//                left++;
//                right--;
//            }
//        }
//        return true;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}