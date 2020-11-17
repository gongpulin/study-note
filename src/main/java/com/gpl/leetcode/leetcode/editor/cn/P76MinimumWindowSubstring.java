//给你一个字符串 S、一个字符串 T 。请你设计一种算法，可以在 O(n) 的时间复杂度内，从字符串 S 里面找出：包含 T 所有字符的最小子串。 
//
// 
//
// 示例： 
//
// 输入：S = "ADOBECODEBANC", T = "ABC"
//输出："BANC" 
//
// 
//
// 提示： 
//
// 
// 如果 S 中不存这样的子串，则返回空字符串 ""。 
// 如果 S 中存在这样的子串，我们保证它是唯一的答案。 
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：最小覆盖子串
public class P76MinimumWindowSubstring{
    public static void main(String[] args) {
        Solution solution = new P76MinimumWindowSubstring().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null) {
            return null;
        }
        int sLen = s.length(), tLen = t.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> windowMap = new HashMap<>();
        int left = 0, right = 0, ans = Integer.MAX_VALUE, ans_left = 0, ans_right = 0;
        while (right < sLen) {
            if (right < sLen && map.containsKey(s.charAt(right))) {
                char c = s.charAt(right);
                windowMap.put(c, windowMap.getOrDefault(c, 0) + 1);
            }
            while (left <= right && right - left + 1 >= tLen && isContainMap(windowMap, map)) {
                int len = right - left + 1;
                if (len < ans) {
                    ans_left = left;
                    ans_right = right + 1;
                    ans = len;
                }
                char leftChar = s.charAt(left);
                if (map.containsKey(leftChar)) {
                    int leftV = windowMap.getOrDefault(leftChar, 0);
                    windowMap.put(leftChar, leftV - 1);
                }
                left++;
            }
            right++;
        }
        return s.substring(ans_left, ans_right);
    }
    private boolean isContainMap(Map<Character, Integer> windowMap, Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char c = entry.getKey();
            int v = entry.getValue();
            if (v > windowMap.getOrDefault(c, 0)) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}