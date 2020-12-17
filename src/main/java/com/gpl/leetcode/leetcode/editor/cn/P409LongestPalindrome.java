//给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。 
//
// 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。 
//
// 注意: 
//假设字符串的长度不会超过 1010。 
//
// 示例 1: 
//
// 
//输入:
//"abccccdd"
//
//输出:
//7
//
//解释:
//我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
// 
// Related Topics 哈希表


package com.gpl.leetcode.leetcode.editor.cn;
//Java：最长回文串
public class P409LongestPalindrome{
    public static void main(String[] args) {
        Solution solution = new P409LongestPalindrome().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestPalindrome(String s) {
        if (s == null) {
            return 0;
        }
        int ans = 0;
        int len = s.length();
        int[] count = new int[128];
        for (int i = 0; i < len; i++) {
            count[s.charAt(i)]++;
        }
        for (int value : count) {
            ans += value / 2 * 2;
            if (value % 2 == 1 && ans % 2 == 0) {
                ans++;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}