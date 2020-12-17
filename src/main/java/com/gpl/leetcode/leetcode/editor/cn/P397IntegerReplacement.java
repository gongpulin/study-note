//给定一个正整数 n ，你可以做如下操作： 
//
// 
// 如果 n 是偶数，则用 n / 2替换 n 。 
// 如果 n 是奇数，则可以用 n + 1或n - 1替换 n 。 
// 
//
// n 变为 1 所需的最小替换次数是多少？ 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 8
//输出：3
//解释：8 -> 4 -> 2 -> 1
// 
//
// 示例 2： 
//
// 
//输入：n = 7
//输出：4
//解释：7 -> 8 -> 4 -> 2 -> 1
//或 7 -> 6 -> 3 -> 2 -> 1
// 
//
// 示例 3： 
//
// 
//输入：n = 4
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 231 - 1 
// 
// Related Topics 位运算 数学


package com.gpl.leetcode.leetcode.editor.cn;
//Java：整数替换
public class P397IntegerReplacement{
    public static void main(String[] args) {
        Solution solution = new P397IntegerReplacement().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int integerReplacement(int n) {
        if (n == Integer.MAX_VALUE) {
            return 32;
        }
        int ans = 0;
        while (n != 1) {
            if ((n & 1) == 0) {
                n = n >> 1;
            } else {
                // 奇数 01减一,11加1,特殊情况n=3也是减1
//                n += ((n & 2) == 0 || n == 3)? -1:1;

                if ((n & 2) == 0 || n == 3) {
                    n = n - 1;
                } else {
                    n = n + 1;
                }
            }
            ans++;
        }
        return ans;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}