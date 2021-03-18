//给两个整数数组 A 和 B ，返回两个数组中公共的、长度最长的子数组的长度。 
//
// 
//
// 示例： 
//
// 输入：
//A: [1,2,3,2,1]
//B: [3,2,1,4,7]
//输出：3
//解释：
//长度最长的公共子数组是 [3, 2, 1] 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= len(A), len(B) <= 1000 
// 0 <= A[i], B[i] < 100 
// 
// Related Topics 数组 哈希表 二分查找 动态规划


package com.gpl.leetcode.leetcode.editor.cn;
//Java：最长重复子数组
public class P718MaximumLengthOfRepeatedSubarray{
    public static void main(String[] args) {
        Solution solution = new P718MaximumLengthOfRepeatedSubarray().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        /**
         * dp[i][j]表示A[i:]与B[j:]的最长重复子数组的长度
         * dp[i][j] = dp[i + 1][j + 1] + 1   当A[i]=B[j]
         *          = 0       当A[i] != B[j]
         */
    public int findLength(int[] A, int[] B) {
        int m = A.length, n = B.length;
        int[][] dp = new int[m + 1][n + 1];
        int ans = 0;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }
    public int findLength3(int[] A, int[] B) {
        int ALen = A.length, BLen = B.length;
        int ans = 0;
        for (int i = 0; i < ALen; i++) {
            int count = 0, aIndex = i, bIndex = 0;
            boolean flag = false;
            while (aIndex < ALen && bIndex < BLen) {
                if (A[aIndex] == B[bIndex]) {
                    flag = true;
                    aIndex++;
                    bIndex++;
                    count++;
                } else {
                    if (flag) {
                        break;
                    }
                    bIndex++;
                }
            }
            ans = Math.max(ans, count);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}