//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划


package com.gpl.leetcode.leetcode.editor.cn;
//Java：最长回文子串
public class P5LongestPalindromicSubstring{
    public static void main(String[] args) {
        Solution solution = new P5LongestPalindromicSubstring().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int len = s.length();
        if (len < 2) {
            return s;
        }
        String res = s.substring(0,1);
        for (int i = 1; i < len; i++) {
            String oddStr = centerSpread(s,i,i);
            String evenStr = centerSpread(s,i, i+1);
            String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
            if (maxLenStr.length() > res.length()) {
                res = maxLenStr;
            }
        }
        return res;
    }
    private String centerSpread(String s, int left, int right) {
        int i = left, j = right;
        int len = s.length();
        while(i >= 0 && j < len) {
            if (s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            } else {
                break;
            }
        }
        return s.substring(i+1,j);
    }


        /**
         * dp[i][j]表示s[i:j]是否为回文串，若dp[i][j]为回文串，则dp[i+1][j-1]也为回文串并且s[i]=s[j]
         */
    public String longestPalindrome(String s) {
        if ( s == null || s.length() == 0 ) {
            return "";
        }
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        String ans = "";
        for (int l = 0; l < len; l++) {
            for (int i = 0; i + l < len; i++) {
                int j = i + l;
                if (l == 0) {
                    dp[i][j] = true;
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, i + l + 1);
                }
            }
        }
        return ans;



    }


}
//leetcode submit region end(Prohibit modification and deletion)

}