//给定一个无序的数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。 
//
// 示例 1: 
//
// 输入: nums = [1, 5, 1, 1, 6, 4]
//输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
//
// 示例 2: 
//
// 输入: nums = [1, 3, 2, 2, 3, 1]
//输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2] 
//
// 说明: 
//你可以假设所有输入都会得到有效的结果。 
//
// 进阶: 
//你能用 O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？ 
// Related Topics 排序
//[1,1,1,4,5,6]
//[1,6,1,5,1,4]
//[1,2,3,4,5,6,7,8,9]
//[5,9,4,8,3,7,2,6,1]

package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;

//Java：摆动排序 II
public class P324WiggleSortIi{
    public static void main(String[] args) {
        Solution solution = new P324WiggleSortIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        int[] temp = nums.clone();
        for (int i = 0; i < len / 2; i++) {
            nums[2 * i] = temp[(len - 1) / 2 - i];
            nums[2 * i + 1] = temp[len - 1 - i];
        }
        if (len % 2 == 1) {
            nums[len - 1] = temp[0];
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}