//给你两个二进制字符串，返回它们的和（用二进制表示）。 
//
// 输入为 非空 字符串且只包含数字 1 和 0。 
//
// 
//
// 示例 1: 
//
// 输入: a = "11", b = "1"
//输出: "100" 
//
// 示例 2: 
//
// 输入: a = "1010", b = "1011"
//输出: "10101" 
//
// 
//
// 提示： 
//
// 
// 每个字符串仅由字符 '0' 或 '1' 组成。 
// 1 <= a.length, b.length <= 10^4 
// 字符串如果不是 "0" ，就都不含前导零。 
// 
// Related Topics 数学 字符串


package com.gpl.leetcode.leetcode.editor.cn;

import scala.Int;

//Java：二进制求和
public class P67AddBinary{
    public static void main(String[] args) {
        Solution solution = new P67AddBinary().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public String addBinary(String a, String b) {
            StringBuilder ans = new StringBuilder();
            int aLen = a.length(), bLen = b.length();
            int maxLen = Math.max(aLen, bLen);
            int carry = 0;
            for (int i = 0; i < maxLen; i++) {
                carry += i < aLen ? a.charAt(aLen - i - 1) - '0' : 0;
                carry += i < bLen ? b.charAt(bLen - i - 1) - '0' : 0;
                ans.append((char)(carry % 2 + '0'));
                carry /= 2;
            }
            if (carry > 0) {
                ans.append("1");
            }
            ans.reverse();
            return ans.toString();
        }

        /**
         * Integer  api,错误答案
         * + 如果字符串超过 *33* 位，不能转化为 `Integer`
         * + 如果字符串超过 *65* 位，不能转化为 `Long`
         * + 如果字符串超过 *500000001* 位，不能转化为 `BigInteger`
         */
//    public String addBinary(String a, String b) {
//        return Integer.toBinaryString(Integer.parseInt(a, 2) + Integer.parseInt(b, 2));
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}