//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 你的目标是使用最少的跳跃次数到达数组的最后一个位置。 
//
// 示例: 
//
// 输入: [2,3,1,1,4]
//输出: 2
//解释: 跳到最后一个位置的最小跳跃数是 2。
//     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
// 
//
// 说明: 
//
// 假设你总是可以到达数组的最后一个位置。 
// Related Topics 贪心算法 数组


package com.gpl.leetcode.leetcode.editor.cn;
//Java：跳跃游戏 II
public class P45JumpGameIi{
    public static void main(String[] args) {
        Solution solution = new P45JumpGameIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        public int jump(int[] nums) {
            if (nums == null) {
                return -1;
            }
            int setps = 0, numRightMost = 0, rightMost = 0, len = nums.length;
            for (int i = 0; i < len - 1; i++) {
                numRightMost = Math.max(numRightMost, i + nums[i]);
                if (i == rightMost) {
                    rightMost = numRightMost;
                    setps++;
                }
            }
            return setps;
        }
    public int jump1(int[] nums) {
        if (nums == null) {
            return -1;
        }
        int steps = 0, right = nums.length - 1;
        while (right > 0) {
            for (int i = 0; i < right; i++) {
                if (i + nums[i] >= right) {
                    right = i;
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}