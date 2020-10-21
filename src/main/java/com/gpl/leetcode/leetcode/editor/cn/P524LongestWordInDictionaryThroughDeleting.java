//给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符
//串。如果答案不存在，则返回空字符串。 
//
// 示例 1: 
//
// 
//输入:
//s = "abpcplea", d = ["ale","apple","monkey","plea"]
//
//输出: 
//"apple"
// 
//
// 示例 2: 
//
// 
//输入:
//s = "abpcplea", d = ["a","b","c"]
//
//输出: 
//"a"
// 
//
// 说明: 
//
// 
// 所有输入的字符串只包含小写字母。 
// 字典的大小不会超过 1000。 
// 所有输入的字符串长度不会超过 1000。 
// 
// Related Topics 排序 双指针


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//Java：通过删除字母匹配到字典里最长单词
public class P524LongestWordInDictionaryThroughDeleting{
    public static void main(String[] args) {
        Solution solution = new P524LongestWordInDictionaryThroughDeleting().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public boolean isSubsequence(String x, String y) {
            int xLen = x.length(), yLen = y.length();
            int j = 0;
            for (int i = 0; i < xLen && j < yLen; i++) {
                if (x.charAt(i) == y.charAt(j)) {
                    j++;
                }
            }
            return j == yLen;
        }

        public String findLongestWord(String s, List<String> d) {
            if (s == null || d == null) {
                return "";
            }
            String ans = "";
            for (String str : d) {
                if (str.length() > s.length()) {
                    continue;
                }
                if ((str.length() > ans.length() && isSubsequence(s, str)) || ((str.length() == ans.length() && str.compareTo(ans) < 0) && isSubsequence(s, str))) {
                    ans = str;
                }
            }
            return ans;
        }


    public String findLongestWord1(String s, List<String> d) {
        if (s == null || d == null) {
            return "";
        }
        Collections.sort(d, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() != o2.length() ? o2.length() - o1.length() : o1.compareTo(o2);
            }
        });
        int sLen = s.length();
        for (String str : d) {
            int strLen = str.length();
            if (strLen > sLen) {
                continue;
            }
            if (isSubsequence(s, str)) {
                return str;
            }
//            int i = 0, j = 0;
//            while (i < sLen && j < strLen) {
//                if (s.charAt(i) == str.charAt(j)) {
//                    i++;
//                    j++;
//                } else {
//                    i++;
//                }
//            }
//            if (j == strLen) {
//                return str;
//            }
        }
        return "";
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}