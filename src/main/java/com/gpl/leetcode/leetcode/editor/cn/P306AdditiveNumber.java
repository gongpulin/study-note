//累加数是一个字符串，组成它的数字可以形成累加序列。 
//
// 一个有效的累加序列必须至少包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。 
//
// 给定一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是累加数。 
//
// 说明: 累加序列里的数不会以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。 
//
// 示例 1: 
//
// 输入: "112358"
//输出: true 
//解释: 累加序列为: 1, 1, 2, 3, 5, 8 。1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
// 
//
// 示例 2:

//
// 输入: "199100199"
//输出: true 
//解释: 累加序列为: 1, 99, 100, 199。1 + 99 = 100, 99 + 100 = 199 
//
// 进阶: 
//你如何处理一个溢出的过大的整数输入? 
// Related Topics 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;
//Java：累加数
public class P306AdditiveNumber{
    public static void main(String[] args) {
        Solution solution = new P306AdditiveNumber().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAdditiveNumber(String num) {
        if (num == null || num.length() < 3) {
            return false;
        }
        return backtrack(num, 0, 0, 0, 0);
    }

        /**
         *
         * @param num 原始字符串
         * @param start 当前处理的下标
         * @param pre 前一个数字
         * @param sum 前两个数字之和
         * @param k   当前处理第几个数字
         * @return
         */
    private boolean backtrack(String num, int start, long pre, long sum, int k) {
        if (start == num.length()) {
            return k > 2;
        }
        for (int i = start; i < num.length(); i++) {
            long cur = getCurValue(num, start, i);
            if (cur < 0) {
                continue;
            }
            if (k >= 2 && cur != sum) {
                continue;
            }
            if (backtrack(num, i+1, cur, pre+cur, k+1)) {
                return true;
            }
        }
        return false;
    }
    private long getCurValue(String s, int left, int right) {
        if (left > right || (left < right && s.charAt(left) == '0')) {
            return -1;
        }
        long res = 0;
        while (left <= right) {
            res = res * 10 + s.charAt(left) - '0';
            left++;
        }
        return  res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}