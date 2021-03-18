//给定字符串 s 和 t ，判断 s 是否为 t 的子序列。 
//
// 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。 
//
// 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"ae
//c"不是）。 
//
// 示例 1: 
//s = "abc", t = "ahbgdc" 
//
// 返回 true. 
//
// 示例 2: 
//s = "axc", t = "ahbgdc" 
//
// 返回 false. 
//
// 后续挑战 : 
//
// 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码
//？ 
//
// 致谢: 
//
// 特别感谢 @pbrother 添加此问题并且创建所有测试用例。 
// Related Topics 贪心算法 二分查找 动态规划


package com.gpl.leetcode.leetcode.editor.cn;
//Java：判断子序列
public class P392IsSubsequence {
    public static void main(String[] args) {
        Solution solution = new P392IsSubsequence().new Solution();
        // TO TEST
    }

    /**
     * 令 f[i][j] 表示字符串 t 中从位置 i 开始往后字符 j 第一次出现的位置。在进行状态转移时，如果 t 中位置 i 的字符就是 j，
     * 那么 f[i][j]=i，否则 j 出现在位置 i+1 开始往后，即 f[i][j]=f[i+1][j]，因此我们要倒过来进行动态规划，从后往前枚举 i。
     *
     * dp[i][j]  =  i            t[i] == j
     *           =  dp[i+1][j]   t[i] != j
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {


//        public boolean isSubsequence(String s, String t) {
//            int n = s.length(), m = t.length();
//
//            int[][] f = new int[m + 1][26];
//            for (int i = 0; i < 26; i++) {
//                f[m][i] = m;
//            }
//
//            for (int i = m - 1; i >= 0; i--) {
//                for (int j = 0; j < 26; j++) {
//                    if (t.charAt(i) == j + 'a') {
//                        f[i][j] = i;
//                    }
//                    else {
//                        f[i][j] = f[i + 1][j];
//                    }
//                }
//            }
//            int add = 0;
//            for (int i = 0; i < n; i++) {
//                if (f[add][s.charAt(i) - 'a'] == m) {
//                    return false;
//                }
//                add = f[add][s.charAt(i) - 'a'] + 1;
//            }
//            return true;
//        }


    public boolean isSubsequence(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        int sIndex = 0, tIndex = 0;
        while (sIndex < sLen && tIndex < tLen) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
                tIndex++;
            } else {
                tIndex++;
            }
        }
        return sIndex == sLen;
    }

            //错误答案
//    public boolean isSubsequence(String s, String t) {
//        if (s.length() == t.length()) {
//            return s.equals(t);
//        }
//        int tIndex = 0;
//        int tLen = t.length();
//        int i = 0;
//        while (i < s.length() && tIndex < tLen) {
//            boolean find = true;
//            while (tIndex < t.length() && find) {
//                if (s.charAt(i) != t.charAt(tIndex)) {
//                    tIndex++;
//                } else {
//                    find = false;
//                }
//            }
//            i++;
//        }
//        if (i == s.length()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
    }
//leetcode submit region end(Prohibit modification and deletion)


}
