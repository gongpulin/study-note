//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的
//句子。 
//
// 说明： 
//
// 
// 分隔时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//输出:
//[
//  "cats and dog",
//  "cat sand dog"
//]
// 
//
// 示例 2： 
//
// 输入:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//输出:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//解释: 注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出:
//[]
// 
// Related Topics 动态规划 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.*;

//Java：单词拆分 II
public class P140WordBreakIi{
    public static void main(String[] args) {
        Solution solution = new P140WordBreakIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);
        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }
//        for (int right = 1; right <= len; right++) {
//            // 如果单词集合中的单词长度都不长，从后向前遍历是更快的
//            for (int left = right - 1; left >= 0; left--) {
//                // substring 不截取 s[right]，dp[left] 的结果不包含 s[left]
//                if (set.contains(s.substring(left, right)) && dp[left]) {
//                    dp[right] = true;
//                    // 这个 break 很重要，一旦得到 dp[right] = True ，不必再计算下去
//                    break;
//                }
//            }
//        }

        List<String> ans = new ArrayList<>();
        if (dp[len]) {
            Deque<String> path = new ArrayDeque<>();
            backtrack(ans, s, path, dp, set, len);
            return ans;
        }
        return ans;
    }
    private void backtrack(List<String> ans, String s, Deque path, boolean[] dp, Set<String> set, int len) {
        if (len == 0) {
            ans.add(String.join(" ", path));
            return;
        }
        for (int i = len - 1; i >= 0; i--) {
            String suffix = s.substring(i, len);
            if (dp[i] && set.contains(suffix)) {
                path.addFirst(suffix);
                backtrack(ans, s, path, dp, set, i);
                path.removeFirst();
            }
        }
    }







//        public List<String> wordBreak(String s, List<String> wordDict) {
//            int len = s.length();
//            boolean[] dp = new boolean[len + 1];
//            dp[0] = true;
//            Set<String> set = new HashSet<>(wordDict);
//            for (int i = 1; i < len; i++) {
//                for (int j = 0; j < i; j++) {
//                    if (dp[j] && set.contains(s.substring(j, i))) {
//                        dp[i] = true;
//                    }
//                }
//            }
//            List<String> ans = new ArrayList<>();
//            backtrack(ans, s, new StringBuilder(), dp, set, 0);
//            return ans;
//        }
//        private void backtrack(List<String> ans, String s, StringBuilder sb, boolean[] dp, Set<String> set, int start) {
//            if (start == s.length()) {
//                ans.add(sb.toString().trim());
//            }
//            int len = s.length();
//            for (int i = len - 1; i >= 0; i++) {
//                String subStr = s.substring(i, len);
//                if (dp[i] && set.contains(subStr)) {
//                    sb.append(subStr);
//                    sb.append(" ");
//                    backtrack(ans, s, sb, dp, set, start + 1);
//                    sb.deleteCharAt(sb.length());
//                    sb.deleteCharAt(sb.length());
//                }
//            }
//        }
}
//leetcode submit region end(Prohibit modification and deletion)

}