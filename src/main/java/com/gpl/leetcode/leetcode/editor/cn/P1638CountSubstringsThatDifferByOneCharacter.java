//给你两个字符串 s 和 t ，请你找出 s 中的非空子串的数目，这些子串满足替换 一个不同字符 以后，是 t 串的子串。换言之，请你找到 s 和 t 串中 
//恰好 只有一个字符不同的子字符串对的数目。 
//
// 比方说， "computer" 和 "computation" 加粗部分只有一个字符不同： 'e'/'a' ，所以这一对子字符串会给答案加 1 。
//
// 请你返回满足上述条件的不同子字符串对数目。
//
// 一个 子字符串 是一个字符串中连续的字符。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aba", t = "baba"
//输出：6
//解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对：
//("aba", "baba")
//("aba", "baba")
//("aba", "baba")
//("aba", "baba")
//("aba", "baba")
//("aba", "baba")
//加粗部分分别表示 s 和 t 串选出来的子字符串。
// 
//示例 2：
//
// 
//输入：s = "ab", t = "bb"
//输出：3
//解释：以下为只相差 1 个字符的 s 和 t 串的子字符串对：
//("ab", "bb")
//("ab", "bb")
//("ab", "bb")
//加粗部分分别表示 s 和 t 串选出来的子字符串。
// 
//示例 3：
//
// 
//输入：s = "a", t = "a"
//输出：0
// 
//
// 示例 4： 
//
// 
//输入：s = "abe", t = "bbc"
//输出：10
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, t.length <= 100 
// s 和 t 都只包含小写英文字母。 
// 
// Related Topics 字典树 哈希表 字符串


package com.gpl.leetcode.leetcode.editor.cn;
//Java：统计只差一个字符的子串数目
public class P1638CountSubstringsThatDifferByOneCharacter{
    public static void main(String[] args) {
        Solution solution = new P1638CountSubstringsThatDifferByOneCharacter().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countSubstrings(String s, String t) {
        if (s == null || t == null) {
            return 0;
        }
        int sLen= s.length();
        int tLen= t.length();
        int[][][] dp = new int[sLen][tLen][2];

        for (int i = 0; i < sLen; i++) {
            if (s.charAt(i) != t.charAt(0)) {
                dp[i][0][0]++;
            } else {
                dp[i][0][1]++;
            }
        }

        for (int j = 1; j < tLen; j++) {
            if (s.charAt(0) != t.charAt(j)) {
                dp[0][j][0]++;
            } else {
                dp[0][j][1]++;
            }
        }

        for (int i = 1; i < sLen; i++) {
            for (int j = 1; j < tLen; j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    dp[i][j][0] = dp[i - 1][j - 1][1] + 1;
                } else {
                    dp[i][j][0] = dp[i - 1][j - 1][0];
                    dp[i][j][1] = dp[i - 1][j - 1][1] + 1;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < sLen; i++) {
            for (int j = 0; j < tLen; j++) {
                ans += dp[i][j][0];
            }
        }
        return ans;
    }














//    public int countSubstrings(String s, String t) {
//        int slen = s.length();
//        int tlen = t.length();
//        // dp[i][j][0]表示s中以i结束的子串与t中以j结束的子串恰好只有一个字符不同的子字符串数目。
//        // dp[i][j][1]表示s中以i结束的子串与t中以j结束的子串相同的数目。
//        int[][][] dp = new int[slen][tlen][2];
//        // 初始化dp[0][j][0],dp[i][0][0],dp[0][j][1],dp[i][0][1]
//        for(int i = 0; i < slen; i++){
//            if(s.charAt(i) != t.charAt(0)){
//                dp[i][0][0]++;
//            }
//            else{
//                dp[i][0][1]++;
//            }
//        }
//        for(int j = 1; j < tlen; j++){
//            if(s.charAt(0) != t.charAt(j)){
//                dp[0][j][0]++;
//            }
//            else{
//                dp[0][j][1]++;
//            }
//        }
//
//        for(int i = 1; i < slen; i++){
//            for(int j = 1; j < tlen; j++){
//                if(s.charAt(i) != t.charAt(j)){
//                    dp[i][j][0] = dp[i-1][j-1][1] + 1;
//                }
//                else{
//                    dp[i][j][0] = dp[i-1][j-1][0];
//                    dp[i][j][1] = dp[i-1][j-1][1] + 1;
//                }
//            }
//        }
//        int res = 0;
//        for(int i = 0; i < slen; i++){
//            for(int j = 0; j < tlen; j++){
//                res += dp[i][j][0];
//            }
//        }
//        return res;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}