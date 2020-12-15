//给你一个字符串 s ，请你返回满足以下条件且出现次数最大的 任意 子串的出现次数： 
//
// 
// 子串中不同字母的数目必须小于等于 maxLetters 。 
// 子串的长度必须大于等于 minSize 且小于等于 maxSize 。 
// 
//
// 
//
// 示例 1： 
//
// 输入：s = "aababcaab", maxLetters = 2, minSize = 3, maxSize = 4
//输出：2
//解释：子串 "aab" 在原字符串中出现了 2 次。
//它满足所有的要求：2 个不同的字母，长度为 3 （在 minSize 和 maxSize 范围内）。
// 
//
// 示例 2： 
//
// 输入：s = "aaaa", maxLetters = 1, minSize = 3, maxSize = 3
//输出：2
//解释：子串 "aaa" 在原字符串中出现了 2 次，且它们有重叠部分。
// 
//
// 示例 3： 
//
// 输入：s = "aabcabcab", maxLetters = 2, minSize = 2, maxSize = 3
//输出：3
// 
//
// 示例 4： 
//
// 输入：s = "abcde", maxLetters = 2, minSize = 3, maxSize = 3
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10^5 
// 1 <= maxLetters <= 26 
// 1 <= minSize <= maxSize <= min(26, s.length) 
// s 只包含小写英文字母。 
// 
// Related Topics 位运算 字符串


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//Java：子串的最大出现次数
public class P1297MaximumNumberOfOccurrencesOfASubstring{
    public static void main(String[] args) {
        Solution solution = new P1297MaximumNumberOfOccurrencesOfASubstring().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        int len = s.length();
        int ans = 0;
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < len - minSize + 1; i++) {
            String cur = s.substring(i, i + minSize);
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < minSize; j++) {
                set.add(cur.charAt(j));
            }
            if (set.size() <= maxLetters) {
                int num = map.getOrDefault(cur, 0);
                map.put(cur, num + 1);
                ans = Math.max(ans, num + 1);
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}