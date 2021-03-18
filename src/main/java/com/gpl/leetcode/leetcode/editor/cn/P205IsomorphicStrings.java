//给定两个字符串 s 和 t，判断它们是否是同构的。 
//
// 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。 
//
// 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。 
//
// 示例 1: 
//
// 输入: s = "egg", t = "add"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "foo", t = "bar"
//输出: false 
//
// 示例 3: 
//
// 输入: s = "paper", t = "title"
//输出: true 
//
// 说明: 
//你可以假设 s 和 t 具有相同的长度。 
// Related Topics 哈希表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：同构字符串
public class P205IsomorphicStrings{
    public static void main(String[] args) {
        Solution solution = new P205IsomorphicStrings().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public boolean isIsomorphic(String s, String t) {
            int len = s.length();
            int[] mapS = new int[128];
            int[] mapT = new int[128];
            for (int i = 0; i < len; i++) {
                char c1 = s.charAt(i);
                char c2 = t.charAt(i);
                if (mapS[c1] != mapT[c2]) {
                    return false;
                } else {
                    if (mapS[c1] == 0) {
                        mapS[c1] = i + 1;
                        mapT[c2] = i + 1;
                    }
                }
            }
            return true;
        }


        public boolean isIsomorphic2(String s, String t) {
            return convert(s).equals(convert(t));
        }
        private String convert(String str) {
            int count = 1;
            int[] map = new int[128];
            int len = str.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < len; i++) {
                char c = str.charAt(i);
                if (map[c] == 0) {
                    map[c] = count;
                    count++;
                }
                sb.append(map[c]);
            }
            return sb.toString();
        }




        /**
         * 举个例子，`s = ab, t = cc`，如果单看 `s -> t` ，那么 `a -> c, b -> c` 是没有问题的。
         * 必须再验证 `t -> s`，此时，`c -> a, c -> b`，一个字母对应了多个字母，所以不是同构的。
         * @param s
         * @param t
         * @return
         */
//        public boolean isIsomorphic1(String s, String t) {
//            return isIsomorphicHelper(s, t) && isIsomorphicHelper(t, s);
//        }
        public boolean isIsomorphicHelper(String s, String t) {
            int len = s.length();
            Map<Character, Character> map = new HashMap<>();
            for (int i = 0; i < len; i++) {
                char c1 = s.charAt(i);
                char c2 = t.charAt(i);
                if (map.containsKey(c1)) {
                    if (map.get(c1) != c2) {
                        return false;
                    }
                } else {
                    map.put(c1, c2);
                }
            }
            return true;
        }

        /**
         * 错误答案，测试用例 ab, ca不通过
         */
//    public boolean isIsomorphic(String s, String t) {
//       char[] sArr = s.toCharArray();
//       char[] tArr = t.toCharArray();
//       for (int i = 0; i < sArr.length; i++) {
//           replaceT(tArr[i], sArr[i], tArr);
//       }
//       return s.equals(String.valueOf(tArr));
//    }
//    private void replaceT(char from, char to, char[] tArr) {
//        for (int i = 0; i < tArr.length; i++) {
//            if (tArr[i] == from) {
//                tArr[i] = to;
//            }
//        }
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}