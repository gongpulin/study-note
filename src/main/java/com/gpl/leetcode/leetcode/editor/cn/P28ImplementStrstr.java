//实现 strStr() 函数。 
//
// 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
//果不存在，则返回 -1。 
//
// 示例 1: 
//
// 输入: haystack = "hello", needle = "ll"
//输出: 2
// 
//
// 示例 2: 
//
// 输入: haystack = "aaaaa", needle = "bba"
//输出: -1
// 
//
// 说明: 
//
// 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。 
//
// 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。 
// Related Topics 双指针 字符串


package com.gpl.leetcode.leetcode.editor.cn;
//Java：实现 strStr()
public class P28ImplementStrstr{
    public static void main(String[] args) {
        Solution solution = new P28ImplementStrstr().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        if (needle.length() == 0) {
            return 0;
        }
        int l1 = haystack.length();
        int l2 = needle.length();
        for (int i = 0; i < l1 - l2 + 1; i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                int left = i+1;
                int right = 1;
                while (right < l2 && left < l1 && haystack.charAt(left) == needle.charAt(right)) {
                    left++;
                    right++;
                }
                if (right == l2) { //注意这里是l2不是l2-1
                    return i;
                }
            }
        }
        return -1;
    }

        public int strStr1(String haystack, String needle) {
            int L = needle.length(), n = haystack.length();
            if (L == 0) {
                return 0;
            }

            int pn = 0;
            while (pn < n - L + 1) {
                // find the position of the first needle character
                // in the haystack string
                while (pn < n - L + 1 && haystack.charAt(pn) != needle.charAt(0)) {
                    ++pn;
                }

                // compute the max match string
                int currLen = 0, pL = 0;
                while (pL < L && pn < n && haystack.charAt(pn) == needle.charAt(pL)) {
                    ++pn;
                    ++pL;
                    ++currLen;
                }

                // if the whole needle string is found,
                // return its start position
                if (currLen == L) return pn - L;

                // otherwise, backtrack
                pn = pn - currLen + 1;
            }
            return -1;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}