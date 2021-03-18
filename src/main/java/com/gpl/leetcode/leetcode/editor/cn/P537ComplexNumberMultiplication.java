//给定两个表示复数的字符串。 
//
// 返回表示它们乘积的字符串。注意，根据定义 i2 = -1 。 
//
// 示例 1: 
//
// 
//输入: "1+1i", "1+1i"
//输出: "0+2i"
//解释: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
// 
//
// 示例 2: 
//
// 
//输入: "1+-1i", "1+-1i"
//输出: "0+-2i"
//解释: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。 
// 
//
// 注意: 
//
// 
// 输入字符串不包含额外的空格。 
// 输入字符串将以 a+bi 的形式给出，其中整数 a 和 b 的范围均在 [-100, 100] 之间。输出也应当符合这种形式。 
// 
// Related Topics 数学 字符串


package com.gpl.leetcode.leetcode.editor.cn;
//Java：复数乘法
public class P537ComplexNumberMultiplication{
    public static void main(String[] args) {
        Solution solution = new P537ComplexNumberMultiplication().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String complexNumberMultiply(String a, String b) {
        String[] x = a.split("\\+|i");
        String[] y = b.split("\\+|i");
        int a1 = Integer.parseInt(x[0]);
        int a2 = Integer.parseInt(x[1]);
        int b1 = Integer.parseInt(y[0]);
        int b2 = Integer.parseInt(y[1]);
        return (a1 * b1 - a2 * b2) + "+" + (a1 * b2 + b1 * a2) + "i";
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}