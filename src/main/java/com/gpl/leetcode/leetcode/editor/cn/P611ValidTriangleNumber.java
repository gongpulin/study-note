//给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。 
//
// 示例 1: 
//
// 
//输入: [2,2,3,4]
//输出: 3
//解释:
//有效的组合是: 
//2,3,4 (使用第一个 2)
//2,3,4 (使用第二个 2)
//2,2,3
// 
//
// 注意: 
//
// 
// 数组长度不超过1000。 
// 数组里整数的范围为 [0, 1000]。 
// 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;

//Java：有效三角形的个数
public class P611ValidTriangleNumber{
    public static void main(String[] args) {
        Solution solution = new P611ValidTriangleNumber().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len - 2; i++) {
            int k = i + 2;
            for (int j = i + 1; j < len - 1 && nums[i] != 0; j++) {
                while (k < len && nums[i] + nums[j] > nums[k]) {
                    k++;
                }
                ans += k - j - 1;
            }
        }
        return ans;
    }

    public int triangleNumber1(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] > nums[k] && nums[i] + nums[k] > nums[j] && nums[j] + nums[k] > nums[i]) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}