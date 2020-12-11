//所有 DNA 都由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究
//非常有帮助。 
//
// 编写一个函数来查找目标子串，目标子串的长度为 10，且在 DNA 字符串 s 中出现次数超过一次。 
//
// 
//
// 示例： 
//
// 输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
//输出：["AAAAACCCCC", "CCCCCAAAAA"] 
// Related Topics 位运算 哈希表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.*;

//Java：重复的DNA序列
public class P187RepeatedDnaSequences{
    public static void main(String[] args) {
        Solution solution = new P187RepeatedDnaSequences().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> findRepeatedDnaSequences1(String s) {

        if (s == null || s.length() < 10) {
            return new ArrayList();
        }
        Set<String> ans = new HashSet<>();
        Set<String> set = new HashSet<>();
        int len = s.length(), L = 10;
        for (int i = 0; i < len - 10 + 1; i++) {
            String subStr = s.substring(i, i + L);
            if (set.contains(subStr)) {
                ans.add(subStr);
            }
            set.add(subStr);
        }
        return new ArrayList(ans);
    }

        /**
         * 旋转hash方法
         * @param s
         * @return
         */
    public List<String> findRepeatedDnaSequences(String s) {
        if (s == null || s.length() < 10) {
            return new ArrayList();
        }
        int len = s.length(), L = 10;
        int a = 4, aL = (int)Math.pow(a, L);
        int[] nums = new int[len];
        for (int i = 0; i < len; i++) {
            nums[i] = getNum(s.charAt(i));
        }
        int h = 0;
        Set<Integer> set = new HashSet<>();
        Set<String> ans = new HashSet<>();
        for (int i = 0; i < len - L + 1; ++i) {
            if (i != 0) {
                h = h * a - nums[i-1] * aL + nums[i+L-1];
            } else {
                for (int j = 0; j < L; j++) {
                    h = h * a + nums[j];
                }
            }
            if (set.contains(h)) {
                ans.add(s.substring(i, i+L));
            }
            set.add(h);
        }
        return new ArrayList(ans);
    }

    private int getNum(char c) {
        switch (c) {
            case 'A' : return 0;
            case 'C' : return 1;
            case 'G' : return 2;
            case 'T' : return 3;
            default : return -1;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}