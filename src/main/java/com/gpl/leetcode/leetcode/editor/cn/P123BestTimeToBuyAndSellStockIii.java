//给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。 
//
// 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。 
//
// 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。 
//
// 示例 1: 
//
// 输入: [3,3,5,0,0,3,1,4]
//输出: 6
//解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
//     随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。 
//
// 示例 2: 
//
// 输入: [1,2,3,4,5]
//输出: 4
//解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。   
//     注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。   
//     因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
// 
//
// 示例 3: 
//
// 输入: [7,6,4,3,1] 
//输出: 0 
//解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。 
// Related Topics 数组 动态规划


package com.gpl.leetcode.leetcode.editor.cn;
//Java：买卖股票的最佳时机 III
public class P123BestTimeToBuyAndSellStockIii{
    public static void main(String[] args) {
        Solution solution = new P123BestTimeToBuyAndSellStockIii().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxProfit1(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int ans = 0, len = prices.length, k = 2;
        int[][][] dp = new int[len][k+1][2];
        for (int i = 1; i <= len; i++) {
            for (int j = k; j >= 1; j++) {
                if (i == 0) {
                    dp[0][j][0] = 0;
                    dp[0][j][1] = Integer.MIN_VALUE;
                    dp[i][0][0] = 0;
                    dp[i][0][1] = Integer.MIN_VALUE;
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i-1][j][0], dp[i-1][j][1] + prices[i]);
                dp[i][j][1] = Math.max(dp[i-1][j][0], dp[i-1][j-1][0] - prices[i]);
            }
        }
        return dp[len-1][2][0];
    }

        public int maxProfit(int[] prices) {
            if(prices==null || prices.length==0) {
                return 0;
            }
            int n = prices.length;
            //定义二维数组，5种状态
            int[][] dp = new int[n][5];
            //初始化第一天的状态
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            dp[0][2] = 0;
            dp[0][3] = -prices[0];
            dp[0][4] = 0;
            for(int i=1;i<n;++i) {
                //dp[i][0]相当于初始状态，它只能从初始状态转换来
                dp[i][0] = dp[i-1][0];
                //处理第一次买入、第一次卖出
                dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
                dp[i][2] = Math.max(dp[i-1][2],dp[i-1][1]+prices[i]);
                //处理第二次买入、第二次卖出
                dp[i][3] = Math.max(dp[i-1][3],dp[i-1][2]-prices[i]);
                dp[i][4] = Math.max(dp[i-1][4],dp[i-1][3]+prices[i]);
            }
            //返回最大值
            return Math.max(Math.max( Math.max(dp[n-1][0],dp[n-1][1]),Math.max(dp[n-1][2],dp[n-1][3]) ),dp[n-1][4]);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}