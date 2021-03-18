//给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动将会使 n - 1 个元素增加 1。 
//
// 
//
// 示例: 
//
// 输入:
//[1,2,3]
//
//输出:
//3
//
//解释:
//只需要3次移动（注意每次移动会增加两个元素的值）：
//
//[1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
// 
// Related Topics 数学


package com.gpl.leetcode.leetcode.editor.cn;
//Java：最小移动次数使数组元素相等
public class P453MinimumMovesToEqualArrayElements{
    public static void main(String[] args) {
        Solution solution = new P453MinimumMovesToEqualArrayElements().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        /**
         * 假设移动了x次，每次增加（len - 1）, 则一共增加 x *（len - 1） + 数组总和，最后的结果为(数组最小值min + x ) * len
         */
        // x * (len - 1) + sum = len * (min + x)
    public int minMoves(int[] nums) {
        if (nums == null) {
            return 0;
        }
        int len = nums.length;
        int sum = 0, min = nums[0];
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            min = Math.min(min, nums[i]);
        }
        int x = ((len * min) - sum) / (-1);
        return x;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}