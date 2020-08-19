//给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回
// -1。 
//
// 
//
// 示例 1: 
//
// 输入: coins = [1, 2, 5], amount = 11
//输出: 3 
//解释: 11 = 5 + 5 + 1 
//
// 示例 2: 
//
// 输入: coins = [2], amount = 3
//输出: -1 
//
// 
//
// 说明: 
//你可以认为每种硬币的数量是无限的。 
// Related Topics 动态规划


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * dp[i]  :  给定金额 i  最少需要 dp[i]个硬币凑成金额i
 *
 *          =      0  ,  i = 0;
 * dp[i]    =     -1  ,  i < 0;
 *          =     min(dp[i], dp[i-coin] + 1)  i > 0;
 */
//Java：零钱兑换
public class P322CoinChange{
    public static void main(String[] args) {
        Solution solution = new P322CoinChange().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int coinChange(int[] coins, int amount) {
        if (amount == 0 || coins == null || coins.length == 0) {
            return 0;
        }
//        Map<Integer, Integer> map = new HashMap<>();
//        return dp(coins, amount, map);
        return dp(coins, amount);

    }
    private int dp1(int[] coins, int amount) {
        int dpSzie = amount + 1;
        int[] dp = new int[dpSzie];
        Arrays.fill(dp,dpSzie);
        dp[0] = 0;
        for (int i = 0; i < dpSzie; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }
        return dp[amount] == (amount+1) ? -1 : dp[amount];
    }





    public int dp(int[] coins, int amount) {
        int dpSize = amount + 1;
        int[] dp = new int[dpSize];
        Arrays.fill(dp, dpSize);
        dp[0] = 0;
        for (int i = 0; i < dpSize; i++) {
            for (int coin : coins) {
                if (i - coin < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }
        return dp[amount] == dpSize ? -1 : dp[amount];
    }
//    private int dp1(int[] coins, int amount, Map map) {
//        if (map.containsKey(amount)) {
//            return (int) map.get(amount);
//        }
//        if (amount == 0) {
//            return 0;
//        }
//        if (amount < 0) {
//            return -1;
//        }
//        int ans = Integer.MAX_VALUE;
//        for (int coin : coins) {
//            int subproblem = dp(coins,amount-coin, map);
//            if (subproblem == -1) {
//                continue;
//            }
//            ans = Math.min(ans,1 + subproblem);
//        }
//        if (ans == Integer.MAX_VALUE) {
//            ans = -1;
//        }
//        map.put(amount, ans);
//        return ans;
//    }

}
//leetcode submit region end(Prohibit modification and deletion)

}