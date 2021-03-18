//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。 
//
// 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312"
// 和 "192.168@1.1" 是 无效的 IP 地址。 
//
// 
//
// 示例 1： 
//
// 输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 输入：s = "1111"
//输出：["1.1.1.1"]
// 
//
// 示例 4： 
//
// 输入：s = "010010"
//输出：["0.10.0.10","0.100.1.0"]
// 
//
// 示例 5： 
//
// 输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3000 
// s 仅由数字组成 
// 
// Related Topics 字符串 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//Java：复原IP地址
public class P93RestoreIpAddresses{
    public static void main(String[] args) {
        Solution solution = new P93RestoreIpAddresses().new Solution();
        // TO TEST
        solution.restoreIpAddresses("1234");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList();
        if (s == null || s.length() == 0 || s.length() > 12) {
            return ans;
        }
        Deque path = new ArrayDeque<>();
        backtrack(ans, path, s, 0, 0);
        return ans;

    }
    private void backtrack(List<String> ans, Deque<String> path, String s, int start, int split) {
        if (start == s.length()) {
            if (split == 4) {
                ans.add(String.join(".",path));
//                System.out.println(String.join(".",path));
            }
            return;
        }
        if (s.length() - start < (4-split) || s.length() - start > 3*(4-split)) {
            return;
        }
        //选择：每次截取一个、两个、三个字符
        for (int i = 0; i < 3; i++) {
            if (start + i >= s.length()) {
                break;
            }
            int ipSegment = judgeIfSegment(s, start, start+i);
            if (ipSegment != -1) {
                path.add(ipSegment+"");
                backtrack(ans, path, s, start+i+1, split+1);
                path.removeLast();
            }
        }

    }
    private int judgeIfSegment(String s, int left, int right) {
        int len = right - left + 1;
        if (len > 1 && s.charAt(left) == '0') {
            return -1;
        }
        int res = 0;
        for (int i = left; i <= right; i++) {
            res = res * 10 + s.charAt(i) - '0';
        }
        if (res > 255) {
            return -1;
        }
//        System.out.println(res);
        return res;
    }


        /**
         * 暴力求解
         */
//        public List<String> restoreIpAddresses(String s) {
//            List<String> ans = new ArrayList();
//            if (s == null || s.length() < 4 || s.length() > 12) {
//                return ans;
//            }
//            int len = s.length();
//            for (int a = 1; a <= len-3; a++) {
//                String s1 = s.substring(0, a);
//                if (!isValid(s1)) {
//                    continue;
//                }
//                for (int b = a+1; b <= len-2; b++) {
//                    String s2 = s.substring(a, b);
//                    if (!isValid(s2)) {
//                        continue;
//                    }
//                    for (int c = b+1; c <= len-1; c++) {
//                        String s3 = s.substring(b, c);
//                        String s4 = s.substring(c, len);
//                        if (isValid(s3) && isValid(s4)) {
//                            ans.add(s1+"."+s2+"."+s3+"."+s4);
//                        }
//                    }
//                }
//            }
//            return ans;
//        }
//        private boolean isValid(String s) {
//            if ("0".equals(s.substring(0,1)) && s.length() > 1) {
//                return false;
//            }
//            Integer num = Integer.parseInt(s);
//            if (num >= 0 && num <= 255) {
//                return true;
//            }
//            return false;
//        }
}
//leetcode submit region end(Prohibit modification and deletion)

}