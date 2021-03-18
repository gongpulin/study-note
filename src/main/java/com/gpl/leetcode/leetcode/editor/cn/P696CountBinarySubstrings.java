//给定一个字符串 s，计算具有相同数量0和1的非空(连续)子字符串的数量，并且这些子字符串中的所有0和所有1都是组合在一起的。 
//
// 重复出现的子串要计算它们出现的次数。 
//
// 示例 1 : 
//
// 
//输入: "00110011"
//输出: 6
//解释: 有6个子串具有相同数量的连续1和0：“0011”，“01”，“1100”，“10”，“0011” 和 “01”。
//
//请注意，一些重复出现的子串要计算它们出现的次数。
//
//另外，“00110011”不是有效的子串，因为所有的0（和1）没有组合在一起。
// 
//
// 示例 2 : 
//
// 
//输入: "10101"
//输出: 4
//解释: 有4个子串：“10”，“01”，“10”，“01”，它们具有相同数量的连续1和0。
// 
//
// 注意： 
//
// 
// s.length 在1到50,000之间。 
// s 只包含“0”或“1”字符。 
// 
// Related Topics 字符串


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：计数二进制子串
public class P696CountBinarySubstrings{
    public static void main(String[] args) {
        Solution solution = new P696CountBinarySubstrings().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int countBinarySubstrings(String s) {
            if (s == null) {
                return 0;
            }
            int ans = 0;
            int len = s.length(), p = 0, last = 0;
            while (p < len) {
                int ct = 0;
                char c = s.charAt(p);
                while (p < len && s.charAt(p) == c) {
                    ct++;
                    p++;
                }
                ans += Math.min(ct, last);
                last = ct;
            }
            return ans;
        }
    public int countBinarySubstrings1(String s) {
        if (s == null) {
            return 0;
        }
        int ans = 0;
        int len = s.length(), p = 0;
        List<Integer> list = new ArrayList<>();
        while (p < len) {
            int ct = 0;
            char c = s.charAt(p);
            while (p < len && s.charAt(p) == c) {
                ct++;
                p++;
            }
            list.add(ct);
        }
        for (int i = 1; i < list.size(); i++) {
            ans += Math.min(list.get(i), list.get(i - 1));
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}