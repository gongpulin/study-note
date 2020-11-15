//给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例: 
//
// 输入: s = "abcdefg", k = 2
//输出: "bacdfeg"
// 
//
// 
//
// 提示： 
//
// 
// 该字符串只包含小写英文字母。 
// 给定字符串的长度和 k 在 [1, 10000] 范围内。 
// 
// Related Topics 字符串


package com.gpl.leetcode.leetcode.editor.cn;
//Java：反转字符串 II
public class P541ReverseStringIi{
    public static void main(String[] args) {
        Solution solution = new P541ReverseStringIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseStr(String s, int k) {
        if (s == null) {
            return "";
        }
        char[] arr = s.toCharArray();
        int len = arr.length;
        int left = 0;
        while (left < len) {
            int right = Math.min(left + k - 1, len - 1);
            swap(arr, left, right);
            left = left + 2 * k;
        }
        return new String(arr);
    }
    private void swap(char[] arr, int start, int end) {
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}