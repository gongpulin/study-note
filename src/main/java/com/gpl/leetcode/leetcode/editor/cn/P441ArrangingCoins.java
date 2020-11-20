//你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。 
//
// 给定一个数字 n，找出可形成完整阶梯行的总行数。 
//
// n 是一个非负整数，并且在32位有符号整型的范围内。 
//
// 示例 1: 
//
// 
//n = 5
//
//硬币可排列成以下几行:
//¤
//¤ ¤
//¤ ¤
//
//因为第三行不完整，所以返回2.
// 
//
// 示例 2: 
//
// 
//n = 8
//
//硬币可排列成以下几行:
//¤
//¤ ¤
//¤ ¤ ¤
//¤ ¤
//
//因为第四行不完整，所以返回3.
// 
// Related Topics 数学 二分查找


package com.gpl.leetcode.leetcode.editor.cn;
//Java：排列硬币
public class P441ArrangingCoins{
    public static void main(String[] args) {
        Solution solution = new P441ArrangingCoins().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int arrangeCoins(int n) {
        int left = 1, right = n;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long midValue = (1 + mid) * mid / 2;
            if (midValue == n) {
                return (int)mid;
            } else if (midValue > n) {
                right = (int)mid - 1;
            } else {
                left = (int)mid + 1;
            }
        }
        return left - 1;  //return high;//return low - 1;是同样的结果。因为最后high<low,而根据题意，k取较小值
    }

    //暴力求解 超时
    public int arrangeCoins2(int n) {
        if (n == 1) {
            return 1;
        }
        int sum = 1, i = 1;
        while (sum <= n) {
            i++;
            sum += i;

        }
        return i-1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}