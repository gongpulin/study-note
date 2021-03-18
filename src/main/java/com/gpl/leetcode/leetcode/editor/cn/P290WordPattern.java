//给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。 
//
// 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。 
//
// 示例1: 
//
// 输入: pattern = "abba", str = "dog cat cat dog"
//输出: true 
//
// 示例 2: 
//
// 输入:pattern = "abba", str = "dog cat cat fish"
//输出: false 
//
// 示例 3: 
//
// 输入: pattern = "aaaa", str = "dog cat cat dog"
//输出: false 
//
// 示例 4: 
//
// 输入: pattern = "abba", str = "dog dog dog dog"
//输出: false 
//
// 说明: 
//你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。 
// Related Topics 哈希表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

//Java：单词规律
public class P290WordPattern{
    public static void main(String[] args) {
        Solution solution = new P290WordPattern().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean wordPattern(String pattern, String s) {
        if (pattern == null || s == null) {
            return false;
        }
        int patternLen = pattern.length();
        String[] sArr = s.split(" ");
        if (sArr.length != patternLen) {
            return false;
        }
        Map<String, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < patternLen; i++) {
            if (!map1.getOrDefault(sArr[i], -1) .equals( map2.getOrDefault(pattern.charAt(i), -1)) ){
                return false;
            } else {
                map1.put(sArr[i], i);
                map2.put(pattern.charAt(i), i);
            }
        }
        return true;
    }

        public boolean wordPattern2(String pattern, String s) {
            String[] words = s.split(" ");
            if (words.length != pattern.length()) {
                return false;
            }
            Map index = new HashMap();
            for (int i=0; i<words.length; ++i) {
                if (!Objects.equals(index.put(pattern.charAt(i), i),
                        index.put(words[i], i))) {
                    return false;
                }
            }
            return true;
        }



        public boolean wordPattern1(String pattern, String s) {
            String[] patternArr = pattern.split("");
            String[] sArr = s.split(" ");
            if (pattern.length() != sArr.length) {
                return false;
            }
            return wordPatternCompare(patternArr, sArr) && wordPatternCompare(sArr, patternArr);
        }

        private boolean wordPatternCompare(String[] array1, String[] array2) {
            int len = array1.length;
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < len; i++) {
                String key = array1[i];
                if (map.containsKey(key)) {
                    if (!(map.get(key).equals(array2[i]))) {
                        return false;
                    }
                } else {
                    map.put(array1[i], array2[i]);
                }
            }
            return true;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}