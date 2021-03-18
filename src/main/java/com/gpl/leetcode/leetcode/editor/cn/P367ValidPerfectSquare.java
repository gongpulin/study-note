//给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。 
//
// 说明：不要使用任何内置的库函数，如 sqrt。 
//
// 示例 1： 
//
// 输入：16
//输出：True 
//
// 示例 2： 
//
// 输入：14
//输出：False
// 
// Related Topics 数学 二分查找


package com.gpl.leetcode.leetcode.editor.cn;
//Java：有效的完全平方数
public class P367ValidPerfectSquare{
    public static void main(String[] args) {
        Solution solution = new P367ValidPerfectSquare().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPerfectSquare1(int num) {
        if (num < 2) {
            return true;
        }
        long left = 2, right = num / 2; //注意这里是long类型  例如808201
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long midValue = mid * mid;
            if (midValue == num) {
                return true;
            } else if (midValue > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }


    //牛顿迭代法
    public boolean isPerfectSquare(int num) {
        if (num < 2) {
            return true;
        }
        long x = num / 2;
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return (x * x == num);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}