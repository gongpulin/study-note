//编写一个程序，找出第 n 个丑数。 
//
// 丑数就是质因数只包含 2, 3, 5 的正整数。 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
// Related Topics 堆 数学 动态规划


package com.gpl.leetcode.leetcode.editor.cn;
//Java：丑数 II
public class P264UglyNumberIi{
    public static void main(String[] args) {
        Solution solution = new P264UglyNumberIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        /**
         * dp[i]
         */
    public int nthUglyNumber(int n) {
        int[] nums = new int[1690];
        nums[0] = 1;
        int ugly, i2 = 0, i3 = 0, i5 = 0;
        for (int i = 1; i < 1690; i++) {
            ugly = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
            nums[i] = ugly;
            if (ugly == nums[i2] * 2) {
                i2++;
            }
            if (ugly == nums[i3] * 3) {
                i3++;
            }
            if (ugly == nums[i5] * 5) {
                i5++;
            }
        }
        return nums[n - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}