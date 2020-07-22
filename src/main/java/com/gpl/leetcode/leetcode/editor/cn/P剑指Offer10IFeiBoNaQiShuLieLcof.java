//写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下： 
//
// F(0) = 0,   F(1) = 1
//F(N) = F(N - 1) + F(N - 2), 其中 N > 1. 
//
// 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 
//
// 示例 1： 
//
// 输入：n = 2
//输出：1
// 
//
// 示例 2： 
//
// 输入：n = 5
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
//
// 注意：本题与主站 509 题相同：https://leetcode-cn.com/problems/fibonacci-number/ 
// Related Topics 递归


package com.gpl.leetcode.leetcode.editor.cn;
//Java：斐波那契数列
public class P剑指Offer10IFeiBoNaQiShuLieLcof{
    public static void main(String[] args) {
        Solution solution = new P剑指Offer10IFeiBoNaQiShuLieLcof().new Solution();
        // TO TEST
        System.out.println(solution.fib(10));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int fib1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }
    public int fib(int n) {
        if (n == 0) {
            return 0;
        }
        int pre = 1;
        int cur = 1;
        int ans = 1;
        for (int i = 2; i < n; i++) {
            ans = pre + cur;
            pre = cur;
            cur = ans;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}