//给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10n 。 
//
// 示例: 
//
// 输入: 2
//输出: 91 
//解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
// 
// Related Topics 数学 动态规划 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;
//Java：计算各个位数不同的数字个数
public class P357CountNumbersWithUniqueDigits{
    public static void main(String[] args) {
        Solution solution = new P357CountNumbersWithUniqueDigits().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        //dp[3]=dp[2]+10*9*8-9*8;
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 9;
        int min = Math.min(10, n);
        int ans = 10;
        for (int i = 1; i < min; i++) {
            dp[i + 1] = dp[i] * (10 - i);
            ans += dp[i + 1];
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}