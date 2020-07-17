//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 示例 1: 
//
// 输入: ["flower","flow","flight"]
//输出: "fl"
// 
//
// 示例 2: 
//
// 输入: ["dog","racecar","car"]
//输出: ""
//解释: 输入不存在公共前缀。
// 
//
// 说明: 
//
// 所有输入只包含小写字母 a-z 。 
// Related Topics 字符串



package com.gpl.leetcode.leetcode.editor.cn;
//Java：最长公共前缀
public class P14LongestCommonPrefix{
    public static void main(String[] args) {
        Solution solution = new P14LongestCommonPrefix().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length < 1) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String prefix = longestCommonPrefix(strs[0],strs[1]);
        for (int i = 2; i < strs.length; i++) {
            prefix = longestCommonPrefix(prefix,strs[i]);
        }
        return prefix;
    }

    private String longestCommonPrefix(String left, String right) {
        if (left == null || right == null) {
            return "";
        }
        int minLength = Math.min(left.length(), right.length());
        int index = 0;
        while (index < minLength) {
            if (left.charAt(index) != right.charAt(index)) {
                return left.substring(0,index);
            } else {
                index++;
            }
        }
        return left.substring(0,minLength);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}