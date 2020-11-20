//给定一个非负整数数组，你最初位于数组的第一个位置。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个位置。 
//
// 示例 1: 
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
// 
//
// 示例 2: 
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
// 
// Related Topics 贪心算法 数组


package com.gpl.leetcode.leetcode.editor.cn;
//Java：跳跃游戏
public class P55JumpGame{
    public static void main(String[] args) {
        Solution solution = new P55JumpGame().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean canJump(int[] nums) {
       if (nums == null) {
           return false;
       }
       int len = nums.length, rightMost = 0;
       for (int i = 0; i < len; i++) {
           if (i <= rightMost) {
               rightMost = Math.max(rightMost, i + nums[i]);
               if (rightMost >= len - 1) {
                   return true;
               }
           }

       }
       return false;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}