//给定一个字符串数组 words，找到 length(word[i]) * length(word[j]) 的最大值，并且这两个单词不含有公共字母。你可以认为
//每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。 
//
// 示例 1: 
//
// 输入: ["abcw","baz","foo","bar","xtfn","abcdef"]
//输出: 16 
//解释: 这两个单词为 "abcw", "xtfn"。 
//
// 示例 2: 
//
// 输入: ["a","ab","abc","d","cd","bcd","abcd"]
//输出: 4 
//解释: 这两个单词为 "ab", "cd"。 
//
// 示例 3: 
//
// 输入: ["a","aa","aaa","aaaa"]
//输出: 0 
//解释: 不存在这样的两个单词。 
// Related Topics 位运算


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Java：最大单词长度乘积
public class P318MaximumProductOfWordLengths{
    public static void main(String[] args) {
        Solution solution = new P318MaximumProductOfWordLengths().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProduct(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
//        Arrays.sort(words, (a , b) -> b.length() - a.length()); //此处排序无用
        int ans = 0;
        int len = words.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                String w1 = words[i];
                String w2 = words[j];
                if (isCommon(w1, w2)) {
                    ans = Math.max(ans, w1.length() * w2.length());
                }
            }
        }
        return ans;
    }
    private boolean isCommon(String w1, String w2) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < w1.length(); i++) {
            set.add(w1.charAt(i));
        }
        for (int j = 0; j < w2.length(); j++) {
            if (set.contains(w2.charAt(j))) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}