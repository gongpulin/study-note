//给定一个整数 n，返回 n! 结果尾数中零的数量。 
//
// 示例 1: 
//
// 输入: 3
//输出: 0
//解释: 3! = 6, 尾数中没有零。 
//
// 示例 2: 
//
// 输入: 5
//输出: 1
//解释: 5! = 120, 尾数中有 1 个零. 
//
// 说明: 你算法的时间复杂度应为 O(log n) 。 
// Related Topics 数学


package com.gpl.leetcode.leetcode.editor.cn;
//Java：阶乘后的零
public class P172FactorialTrailingZeroes{
    public static void main(String[] args) {
        Solution solution = new P172FactorialTrailingZeroes().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int trailingZeroes(int n) {
            int ans = 0;
            long divisor = 5;
            while (divisor <= n) {
                ans += n / divisor;
                divisor *= 5;
            }
            return ans;
        }

        //错误答案
    public int trailingZeroes1(int n) {
        if (n == 0) {
            return 0;
        }
        long num = n;
        for (int i = n - 1; i > 1; i--) {
            num *= i;
        }
        String numStr = String.valueOf(num);
        int ans = 0;
        for (int i = numStr.length() - 1; i > 0; i--) {
            if (numStr.charAt(i) == '0') {
                ans++;
            } else {
                break;
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}