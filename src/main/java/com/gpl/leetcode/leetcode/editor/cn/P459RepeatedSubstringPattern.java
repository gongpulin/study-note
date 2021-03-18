//给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。 
//
// 示例 1: 
//
// 
//输入: "abab"
//
//输出: True
//
//解释: 可由子字符串 "ab" 重复两次构成。
// 
//
// 示例 2: 
//
// 
//输入: "aba"
//
//输出: False
// 
//
// 示例 3: 
//
// 
//输入: "abcabcabcabc"
//
//输出: True
//
//解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
// 
// Related Topics 字符串


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：重复的子字符串
public class P459RepeatedSubstringPattern{
    public static void main(String[] args) {
        Solution solution = new P459RepeatedSubstringPattern().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        if (s == null) {
            return false;
        }
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i <= len / 2; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int left = map.get(c);
                if (isRepeat(s, left, i)) {
                    return true;
                }
            } else {
                map.put(c, i);
            }
        }
        return false;
    }

    private boolean isRepeat(String s, int left, int right) {
        int len = s.length();
        int repeatNum = right - left;
        if (len % repeatNum != 0) {
            return false;
        }
        while (right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right++;
            } else {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}