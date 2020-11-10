//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
// Related Topics 字符串 动态规划


package com.gpl.leetcode.leetcode.editor.cn;
//Java：编辑距离
public class P72EditDistance{
    public static void main(String[] args) {
        Solution solution = new P72EditDistance().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        //dp[i][j] 表示word1到i的位置转换到word2到j的位置需要的最少步数
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            return -1;
        }
        int m = word1.length(), n = word2.length();
        if (m * n == 0) {
            return m + n;
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                int min = Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1);
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = Math.min(min, dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = Math.min(min, dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp[m][n];
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}