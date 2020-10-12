//猜数字游戏的规则如下： 
//
// 
// 每轮游戏，系统都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。 
// 如果你猜错了，系统会告诉你，你猜测的数字比系统选出的数字是大了还是小了。 
// 
//
// 你可以通过调用一个预先定义好的接口 guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）： 
//
// -1 : 你猜测的数字比系统选出的数字大
// 1 : 你猜测的数字比系统选出的数字小
// 0 : 恭喜！你猜对了！
// 
//
// 
//
// 示例 : 
//
// 输入: n = 10, pick = 6
//输出: 6 
// Related Topics 二分查找


package com.gpl.leetcode.leetcode.editor.cn;
//Java：猜数字大小
public class P374GuessNumberHigherOrLower{
    public static void main(String[] args) {
        Solution solution = new P374GuessNumberHigherOrLower().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/** 
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
//    public int guessNumber(int n) {
//        int left = 0, right = n;
//        while (left <= right) {
//            int mid = left + (right - left) / 2;
//            int guess = guess(mid);
//            if (guess == 0) {
//                return mid;
//            } else if (guess == 1) {
//                left = mid + 1;
//            } else {
//                right = mid - 1;
//            }
//        }
//        return -1;
//    }

    //三分查找   最坏情况下三分查找比较次数比二分查找最坏情况要多
    public int guessNumber(int n) {
        int left = 0, right = n;
        while (left <= right) {
            int mid1 = left + (right - left) / 3;
            int mid2 = right - (right - left) / 3;
            int guess1 = guess(mid1);
            int guess2 = guess(mid2);
            if (guess1 == 0) {
                return mid1;
            }
            if (guess2 == 0) {
                return mid2;
            } else if (guess1 == -1) {
                right = mid1 - 1;
            } else if (guess2 == 1) {
                left = mid2 + 1;
            } else {
                left = mid1 + 1;
                right = mid2 - 1;
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class GuessGame {
    public int guess(int num) {
        return 0;
    }
}
}