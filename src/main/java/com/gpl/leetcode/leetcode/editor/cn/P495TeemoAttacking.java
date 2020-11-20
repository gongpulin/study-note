//在《英雄联盟》的世界中，有一个叫 “提莫” 的英雄，他的攻击可以让敌方英雄艾希（编者注：寒冰射手）进入中毒状态。现在，给出提莫对艾希的攻击时间序列和提莫攻击
//的中毒持续时间，你需要输出艾希的中毒状态总时长。 
//
// 你可以认为提莫在给定的时间点进行攻击，并立即使艾希处于中毒状态。 
//
// 
//
// 示例1: 
//
// 输入: [1,4], 2
//输出: 4
//原因: 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。
//第 4 秒初，提莫再次攻击艾希，使得艾希获得另外 2 秒中毒时间。
//所以最终输出 4 秒。
// 
//
// 示例2: 
//
// 输入: [1,2], 2
//输出: 3
//原因: 第 1 秒初，提莫开始对艾希进行攻击并使其立即中毒。中毒状态会维持 2 秒钟，直到第 2 秒末结束。
//但是第 2 秒初，提莫再次攻击了已经处于中毒状态的艾希。
//由于中毒状态不可叠加，提莫在第 2 秒初的这次攻击会在第 3 秒末结束。
//所以最终输出 3 。
// 
//
// 
//
// 提示： 
//
// 
// 你可以假定时间序列数组的总长度不超过 10000。 
// 你可以假定提莫攻击时间序列中的数字和提莫攻击的中毒持续时间都是非负整数，并且不超过 10,000,000。 
// 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;
//Java：提莫攻击
public class P495TeemoAttacking{
    public static void main(String[] args) {
        Solution solution = new P495TeemoAttacking().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0 || duration == 0) {
            return 0;
        }
        int ans = 0;
        int len = timeSeries.length;
        for (int i = 1; i < len; i++) {
            int right = timeSeries[i - 1] + duration;
            if (timeSeries[i] < right) {
                ans += timeSeries[i] - timeSeries[i - 1];
            } else {
                ans += duration;
            }
        }
        ans += duration;
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}