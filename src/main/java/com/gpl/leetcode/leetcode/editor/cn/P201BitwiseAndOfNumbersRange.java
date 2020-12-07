//给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。 
//
// 示例 1: 
//
// 输入: [5,7]
//输出: 4 
//
// 示例 2: 
//
// 输入: [0,1]
//输出: 0 
// Related Topics 位运算


package com.gpl.leetcode.leetcode.editor.cn;
//Java：数字范围按位与
public class P201BitwiseAndOfNumbersRange{
    public static void main(String[] args) {
        Solution solution = new P201BitwiseAndOfNumbersRange().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int rangeBitwiseAnd1(int m, int n) {
        int shift = 0;
        while (m < n) {
            m >>= 1;
            n >>= 1;
            shift++;
        }
        return m << shift;
    }

    public int rangeBitwiseAnd(int m, int n) {
        while (m < n) {
            n = n & (n - 1);
        }
        return n;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}