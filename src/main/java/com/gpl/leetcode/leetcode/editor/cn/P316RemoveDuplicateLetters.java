//给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。 
//
// 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct
//-characters 相同 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "bcabc"
//输出："abc"
// 
//
// 示例 2： 
//
// 
//输入：s = "cbacdcbc"
//输出："acdb" 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 由小写英文字母组成 
// 
// Related Topics 栈 贪心算法 字符串


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.*;

//Java：去除重复字母
public class
P316RemoveDuplicateLetters{
    public static void main(String[] args) {
        Solution solution = new P316RemoveDuplicateLetters().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String removeDuplicateLetters(String s) {
        if (s == null) {
            return null;
        }
        int len = s.length();
        Map<Character, Integer> lastOccurence = new HashMap<>();
        for (int i = 0; i < len; i++) {
            lastOccurence.put(s.charAt(i), i);
        }
        Stack<Character> stack = new Stack<>();
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (!seen.contains(c)) {
                while (!stack.isEmpty() && c < stack.peek() && lastOccurence.get(stack.peek()) > i) {
                    seen.remove(stack.pop());
                }
                seen.add(c);
                stack.push(c);
            }

        }
        StringBuilder ans =new StringBuilder(stack.size());

//        for (Character c : stack) {
//            ans.append(c.charValue());
//        }
//        return ans.toString();

        while (!stack.isEmpty()) {
            ans.append(stack.pop());
        }
        return ans.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}