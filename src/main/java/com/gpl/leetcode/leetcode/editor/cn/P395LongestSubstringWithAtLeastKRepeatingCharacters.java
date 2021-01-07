//找到给定字符串（由小写字符组成）中的最长子串 T ， 要求 T 中的每一字符出现次数都不少于 k 。输出 T 的长度。 
//
// 示例 1: 
//
// 
//输入:
//s = "aaabb", k = 3
//
//输出:
//3
//
//最长子串为 "aaa" ，其中 'a' 重复了 3 次。
// 
//
// 示例 2: 
//
// 
//输入:
//s = "ababbc", k = 2
//
//输出:
//5
//
//最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
// 
// Related Topics 递归 分治算法 Sliding Window


package com.gpl.leetcode.leetcode.editor.cn;
//Java：至少有K个重复字符的最长子串
public class P395LongestSubstringWithAtLeastKRepeatingCharacters{
    public static void main(String[] args) {
        Solution solution = new P395LongestSubstringWithAtLeastKRepeatingCharacters().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0 || s.length() < k) {
            return 0;
        }
        int len = s.length();
        if (k < 2) {
            return len;
        }
        return count(s.toCharArray(), k, 0, len - 1);
    }
    private int count(char[] arr, int k, int left, int right) {
        if (right - left + 1 < k) {
            return 0;
        }
        int[] times = new int[26];
        for (int i = left; i <= right; i++) {
            times[arr[i] - 'a']++;
        }
        while (right - left + 1 >= k && times[arr[left] - 'a'] < k) {
            left++;
        }
        while (right - left + 1 >= k && times[arr[right] - 'a'] < k) {
            right--;
        }
        if (right - left + 1 < k) {
            return 0;
        }
        for (int i = left; i <= right; i++) {
            if (times[arr[i] - 'a'] < k) {
                return Math.max(count(arr, k, left, i - 1), count(arr, k, i + 1, right));
            }
        }
        return right - left + 1;
    }


        //错误答案
    public int longestSubstring1(String s, int k) {
        if (s == null || s.length() == 0 || s.length() < k) {
            return 0;
        }
        int len = s.length();
        int[] counts = new int[26];
        for (int i = 0; i < len; i++) {
            counts[s.charAt(i) - 'a']++;
        }
        int ans = 0;
        int left = -1;
        for (int right = 0; right < len; right++) {
            if (counts[s.charAt(right) - 'a'] < k) {
                left = right;
            }
            ans = Math.max(ans, right - left);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}