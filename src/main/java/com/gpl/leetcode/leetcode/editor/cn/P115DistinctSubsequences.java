//给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。 
//
// 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列
//，而 "AEC" 不是） 
//
// 题目数据保证答案符合 32 位带符号整数范围。 
//
// 
//
// 示例 1： 
//
// 输入：S = "rabbbit", T = "rabbit"
//输出：3
//解释：
//
//如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
//(上箭头符号 ^ 表示选取的字母)
//
//rabbbit
//^^^^ ^^
//rabbbit
//^^ ^^^^
//rabbbit
//^^^ ^^^
// 
//
// 示例 2： 
//
// 输入：S = "babgbag", T = "bag"
//输出：5
//解释：
//
//如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。 
//(上箭头符号 ^ 表示选取的字母)
//
//babgbag
//^^ ^
//babgbag
//^^    ^
//babgbag
//^    ^^
//babgbag
//  ^  ^^
//babgbag
//    ^^^ 
// Related Topics 字符串 动态规划


package com.gpl.leetcode.leetcode.editor.cn;
//Java：不同的子序列
public class P115DistinctSubsequences{
    public static void main(String[] args) {
        Solution solution = new P115DistinctSubsequences().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        /**
         * dp[i][j]表示 T 字符串前i个字符 可以由 S 的前j个字符组成的个数
         */
    public int numDistinct(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        int sLen = s.length(), tLen = t.length();
        int[][] dp = new int[tLen + 1][sLen + 1];
        for (int j = 0; j < sLen + 1; j++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < tLen + 1; i++) {
            for (int j = 1; j < sLen + 1; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[tLen][sLen];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}