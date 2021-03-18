//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。 
//
// 
//
// 示例： 
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10^3 
// -10^3 <= nums[i] <= 10^3 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 双指针


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;

//Java：最接近的三数之和
public class P16ThreeSumClosest{
    public static void main(String[] args) {
        Solution solution = new P16ThreeSumClosest().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) { //剪枝，避免重复元素计算
                continue;
            }
            int left = i + 1;
            int right = len - 1;
            while(left < right) {
                /**
                 * 下面注释为错误剪枝，当nums={1,1,1,0} 100时，结果为3，所以不可提前剪枝
                 */
//                if(left < right && nums[left] == nums[left-1]) {
//                    left++;
//                    continue;
//                }
//                if(right < len - 1 && nums[right] == nums[right+1]) {
//                    right--;
//                    continue;
//                }

                //剪枝
                int min = nums[i] + nums[left] + nums[left+1];
                if (target < min) {
                    if(Math.abs(target-min) < Math.abs(target-ans)) {
                        ans = min;

                    }
                    break;
                }
                int max = nums[i] + nums[right-1] + nums[right];
                if (target > max) {
                    if (Math.abs(target-max) < Math.abs(target - ans)) {
                        ans = max;
                    }
                    break;
                }

                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }
                if(sum > target) {
                    right--;
                    while(left < right && nums[right] == nums[right+1]) {//剪枝，避免重复元素计算
                        right--;
                    }
                } else {
                    left++;
                    while(left < right && nums[left] == nums[left-1]) {//剪枝，避免重复元素计算
                        left++;
                    }
                }
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}