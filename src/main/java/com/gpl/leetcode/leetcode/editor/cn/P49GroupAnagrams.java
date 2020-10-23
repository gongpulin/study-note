//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.*;

//Java：字母异位词分组
public class P49GroupAnagrams{
    public static void main(String[] args) {
        Solution solution = new P49GroupAnagrams().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List> ans = new HashMap<>();
            for (String str : strs) {
                char[] arr = str.toCharArray();
                Arrays.sort(arr);
                String key = String.valueOf(arr);
                if (!ans.containsKey(key)) {
                    ans.put(key, new ArrayList<String>());
                }
                ans.get(key).add(str);
            }
            return new ArrayList(ans.values());
        }
//        public List<List<String>> groupAnagrams(String[] strs) {
//            Map<String, List> ans = new HashMap<>();
//            int[] count = new int[26];
//            for (String str : strs) {
//                Arrays.fill(count, 0);
//                char[] arr = str.toCharArray();
//                for (char c : arr) {
//                    count[c - 'a']++;
//                }
//                StringBuilder sb = new StringBuilder();
//                for(int i = 0; i < 26; i++) {
//                    sb.append("#");
//                    sb.append(count[i]);
//                }
//                String key = sb.toString();
//                if (!ans.containsKey(key)) {
//                    ans.put(key, new ArrayList<String>());
//                }
//                ans.get(key).add(str);
//            }
//            return new ArrayList(ans.values());
//        }

}
//leetcode submit region end(Prohibit modification and deletion)

}