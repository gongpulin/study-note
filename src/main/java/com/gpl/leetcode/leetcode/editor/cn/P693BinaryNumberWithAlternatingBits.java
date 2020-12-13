//给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 5
//输出：true
//解释：5 的二进制表示是：101
// 
//
// 示例 2： 
//
// 
//输入：n = 7
//输出：false
//解释：7 的二进制表示是：111. 
//
// 示例 3： 
//
// 
//输入：n = 11
//输出：false
//解释：11 的二进制表示是：1011. 
//
// 示例 4： 
//
// 
//输入：n = 10
//输出：true
//解释：10 的二进制表示是：1010. 
//
// 示例 5： 
//
// 
//输入：n = 3
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 231 - 1 
// 
// Related Topics 位运算


package com.gpl.leetcode.leetcode.editor.cn;
//Java：交替位二进制数
public class P693BinaryNumberWithAlternatingBits{
    public static void main(String[] args) {
        Solution solution = new P693BinaryNumberWithAlternatingBits().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean hasAlternatingBits(int n) {

        while (n != 0) {
            int first = n & 1;
            n = n >> 1;
            int second = n & 1;
            if (first == second) {
                return false;
            }
        }
        return true;
    }
    public boolean hasAlternatingBits1(int n) {
        if (n == 1) {
            return true;
        }
        String str = Integer.toBinaryString(n);
        int len = str.length();
        for (int i = 1; i < len; i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}