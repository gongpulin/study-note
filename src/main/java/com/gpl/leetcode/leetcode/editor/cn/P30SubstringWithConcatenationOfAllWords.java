//给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。 
//
// 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。 
//
// 
//
// 示例 1： 
//
// 输入：
//  s = "barfoothefoobarman",
//  words = ["foo","bar"]
//输出：[0,9]
//解释：
//从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
//输出的顺序不重要, [9,0] 也是有效答案。
// 
//
// 示例 2： 
//
// 输入：
//  s = "wordgoodgoodgoodbestword",
//  words = ["word","good","best","word"]
//输出：[]
// 
// Related Topics 哈希表 双指针 字符串


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Java：串联所有单词的子串
public class P30SubstringWithConcatenationOfAllWords{
    public static void main(String[] args) {
        Solution solution = new P30SubstringWithConcatenationOfAllWords().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public List<Integer> findSubstring(String s, String[] words) {
            List<Integer> ans = new ArrayList<>();
            if (s == null || s.length() == 0) {
                return ans;
            }
            int wordNum = words.length, wordLen = words[0].length(), sLen = s.length();
            if (sLen < wordLen * wordNum) {
                return ans;
            }
            Map<String, Integer> wordMap = new HashMap<>();
            for (String word : words) {
                wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
            }
            for (int i = 0; i < sLen - (wordNum * wordLen) + 1; i++) {
                int num = 0;
                Map<String, Integer> hasWord = new HashMap<>();
                while (num < wordNum) {
                    String substr = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
                    if (wordMap.containsKey(substr)) {
                        hasWord.put(substr, hasWord.getOrDefault(substr, 0) + 1);
                        if (hasWord.get(substr) > wordMap.get(substr)) {
                            break;
                        }
                    } else {
                        break;
                    }
                    num++;
                }
                if (num == wordNum) {
                    ans.add(i);
                }
            }
            return ans;
        }
//    public List<Integer> findSubstring(String s, String[] words) {
//        List<Integer> ans = new ArrayList<>();
//        if (s == null || s.length() == 0) {
//            return ans;
//        }
//        int len = words.length;
//        int wLen = words[0].length();
//        int sLen = s.length();
//        if (sLen < (len + 1) * wLen) {
//            return ans;
//        }
//        Map<String, Integer> map = new HashMap<>();
//        for (String word : words) {
//            map.put(word, map.getOrDefault(word, 0) + 1);
//        }
//        for (int i = 0; i < sLen - len * wLen + 1; i++) {
//            String substr = s.substring(i, i + wLen);
//            int index = i + wLen;
//            int j = 0;
//            Map<String, Integer> sMap = new HashMap<>();
//            for (; j < len && index + wLen < sLen; j ++) {
//                sMap.put(substr, sMap.getOrDefault(substr, 0) + 1);
//                if (map.containsKey(substr) && sMap.get(substr) <= map.get(substr)) {
//                    substr = s.substring(index, index + wLen);
//                    index = index + wLen;
//                } else {
//                    break;
//                }
//            }
//            if (j == len) {
//                ans.add(i);
//            }
//        }
//        return ans;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}