//给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。 
//
// 下图是字符串 s1 = "great" 的一种可能的表示形式。 
//
//     great
//   /    \
//  gr    eat
// / \    /  \
//g   r  e   at
//           / \
//          a   t
// 
//
// 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。 
//
// 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。 
//
//     rgeat
//   /    \
//  rg    eat
// / \    /  \
//r   g  e   at
//           / \
//          a   t
// 
//
// 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。 
//
// 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。 
//
//     rgtae
//   /    \
//  rg    tae
// / \    /  \
//r   g  ta  e
//       / \
//      t   a
// 
//
// 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。 
//
// 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。 
//
// 示例 1: 
//
// 输入: s1 = "great", s2 = "rgeat"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s1 = "abcde", s2 = "caebd"
//输出: false 
// Related Topics 字符串 动态规划


package com.gpl.leetcode.leetcode.editor.cn;
//Java：扰乱字符串
public class P87ScrambleString{
    public static void main(String[] args) {
        Solution solution = new P87ScrambleString().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.equals(s2)) {
            return true;
        }
        int len = s1.length();

        //判断两个字符串字符出现次数是否一致
        int[] letters = new int[26];
        for (int i = 0; i < len; i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) {
                return false;
            }
        }

        //遍历每个切割位置
        for (int i = 1; i < len; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(len - i)) && isScramble(s1.substring(i), s2.substring(0, len - i))) {
                return true;
            }
        }
        return false;
    }




//    public boolean isScramble(String s1, String s2) {
//        if (s1 == null || s2 == null) {
//            return false;
//        }
//        if (s1.length() != s2.length()) {
//            return false;
//        }
//        if (s1.equals(s2)) {
//            return true;
//        }
//        int len = s1.length();
//        int[] letters = new int[26];
//        for (int i = 0; i < len; i++) {
//            letters[s1.charAt(i) - 'a']++;
//            letters[s2.charAt(i) - 'a']--;
//        }
//        for (int i = 0; i < 26; i++) {
//            if (letters[i] != 0) {
//                return false;
//            }
//        }
//        boolean[][][] dp = new boolean[len + 1][len][len];
//        for (int i = 1; i <= len; i++) {
//            for (int j = 0; j + i <= len; j++) {
//                for (int k = 0; k + i <= len; k++) {
//                    if (i == 1) {
//                        dp[i][j][k] = s1.charAt(j) == s2.charAt(k);
//                    } else {
//                        for (int q = 1; q < i; q++) {
//                            dp[i][j][k] = dp[q][j][k] && dp[i - q][j + q][k + q] || dp[q][j][k + i - q] && dp[i - q][j + q][k];
//                            if (dp[i][j][k]) {
//                                break;
//                            }
//                        }
//
//                    }
//
//                }
//            }
//        }
//        return dp[len][0][0];
//    }





}
//leetcode submit region end(Prohibit modification and deletion)

}