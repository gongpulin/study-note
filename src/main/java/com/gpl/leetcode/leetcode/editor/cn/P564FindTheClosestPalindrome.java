//给定一个整数 n ，你需要找到与它最近的回文数（不包括自身）。 
//
// “最近的”定义为两个整数差的绝对值最小。 
//
// 示例 1: 
//
// 
//输入: "123"
//输出: "121"
// 
//
// 注意: 
//
// 
// n 是由字符串表示的正整数，其长度不超过18。 
// 如果有多个结果，返回最小的那个。 
// 
// Related Topics 字符串


package com.gpl.leetcode.leetcode.editor.cn;
//Java：寻找最近的回文数
public class P564FindTheClosestPalindrome{
    public static void main(String[] args) {
        Solution solution = new P564FindTheClosestPalindrome().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String nearestPalindromic(String n) {
        if (n == null) {
            return "";
        }

    }
    private boolean isPalindromic(String n) {
        int left = 0, right = n.length() - 1;
        while (left < right) {
            if (n.charAt(left) != n.charAt(right)) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}