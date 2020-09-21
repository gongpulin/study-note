//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

//Java：无重复字符的最长子串
public class P3LongestSubstringWithoutRepeatingCharacters{
    public static void main(String[] args) {
        Solution solution = new P3LongestSubstringWithoutRepeatingCharacters().new Solution();
        // TO TEST
        String s = "abcdeabc";
        System.out.println(solution.lengthOfLongestSubstring(s));
        String ss = "aaaaa";
        System.out.println(solution.lengthOfLongestSubstring(ss));
        String sss = "pwwkew";
        System.out.println(solution.lengthOfLongestSubstring(sss));
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * 滑动窗口，s(i,j)为不重复子串，那么s(i+1,j)一定也为不重复子串，所以有指针不需要每次从头遍历
     */
    class Solution {

        public int lengthOfLongestSubstring(String s) {
            if (s == null) {
                return 0;
            }
            Set<Character> set = new HashSet<>();
            int right = 0, len = s.length();
            int ans = 0;
            for (int left = 0; left < len; left++) {
                if (left != 0) {
                    set.remove(s.charAt(left-1));
                }
                while (right < len && !set.contains(s.charAt(right))) {
                    set.add(s.charAt(right));
                    right++;
                }
                ans = Math.max(ans, right-left);
            }
            return ans;
        }

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int rk = 0, res = 0;
        Set<Character> set = new HashSet<Character>();
        for (int i = 0; i < n; i ++) {
            if ( i != 0 ) {
                set.remove(s.charAt(i-1));
            }
            while ( rk < n && !set.contains(s.charAt(rk))) {
                set.add(s.charAt(rk));
                rk++;
            }
            res = Math.max(res,rk-i);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}