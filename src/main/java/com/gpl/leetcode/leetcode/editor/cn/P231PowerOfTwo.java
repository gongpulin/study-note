//给定一个整数，编写一个函数来判断它是否是 2 的幂次方。 
//
// 示例 1: 
//
// 输入: 1
//输出: true
//解释: 20 = 1 
//
// 示例 2: 
//
// 输入: 16
//输出: true
//解释: 24 = 16 
//
// 示例 3: 
//
// 输入: 218
//输出: false 
// Related Topics 位运算 数学


package com.gpl.leetcode.leetcode.editor.cn;
//Java：2的幂
public class P231PowerOfTwo{
    public static void main(String[] args) {
        Solution solution = new P231PowerOfTwo().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public boolean isPowerOfTwo2(int n) {
            if (n == 0) {
                return false;
            }
            while (n % 2 == 0) {
                n = n / 2;
            }
            return n == 1;
        }

        public boolean isPowerOfTwo1(int n) {
            if (n == 0) {
                return false;
            }
            long x = (long) n;
            return (x & (-x)) == x;
        }
        public boolean isPowerOfTwo(int n) {
            if (n == 0) {
                return false;
            }
            long x = (long) n;
            return (x & (x - 1)) == 0;
        }

        //错误答案
    public boolean isPowerOfTwo5(int n) {
        if (n == 1) {
            return true;
        }
        int ans = 2;
        for (int i = 0; i < n && ans <= n; i++) {
            if (ans == n) {
                return true;
            }
            ans *= 2;
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}