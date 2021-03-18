//给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。 
//
// 
//
// 提示： 
//
// 
// num1 和num2 的长度都小于 5100 
// num1 和num2 都只包含数字 0-9 
// num1 和num2 都不包含任何前导零 
// 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式 
// 
// Related Topics 字符串


package com.gpl.leetcode.leetcode.editor.cn;
//Java：字符串相加
public class P415AddStrings{
    public static void main(String[] args) {
        Solution solution = new P415AddStrings().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public String addStrings(String num1, String num2) {
            int carry = 0;
            StringBuilder ans = new StringBuilder();
            int index1 = num1.length() - 1, index2 = num2.length() - 1 ;
            while (index1 >= 0 || index2 >= 0) {
                int n1 = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
                int n2 = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
                int sum = (n1 + n2 + carry) % 10;
                carry = (n1 + n2 + carry) / 10;
                ans.append(sum);
                index1--;
                index2--;
            }
            if (carry != 0) {
                ans.append(carry);
            }
            return ans.reverse().toString();
        }
    public String addStrings1(String num1, String num2) {
        int carry = 0;
        StringBuilder ans = new StringBuilder();
        int index1 = num1.length() - 1, index2 = num2.length() - 1 ;
        while (index1 >= 0 || index2 >= 0) {
            int n1 = index1 >= 0 ? num1.charAt(index1) - '0' : 0;
            int n2 = index2 >= 0 ? num2.charAt(index2) - '0' : 0;
            int sum = (n1 + n2 + carry) % 10;
            carry = (n1 + n2 + carry) / 10;
            ans.insert(0,sum); // insert效率很低，不如先append,再reverse()
            index1--;
            index2--;
        }
        if (carry != 0) {
            ans.insert(0,carry);
        }
         return ans.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}