//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针


package com.gpl.leetcode.leetcode.editor.cn;
//Java：移动零
public class P283MoveZeroes{
    public static void main(String[] args) {
        Solution solution = new P283MoveZeroes().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {


        public void moveZeroes(int[] nums) {
            if (nums == null) {
                return;
            }
            int left = 0, len = nums.length;
            for (int right = 0; right < len; right++) {
                if (nums[right] != 0) {
                    int temp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = temp;
                    left++;
                }
            }
        }







        //[0,1,0,3,12]
        //[2,1,0,3,12]
//        public void moveZeroes(int[] nums) {
//            if (nums == null) {
//                return;
//            }
//            int left = 0, right = 0, len = nums.length;
//            while (right < len) {
//                if (nums[right] != 0) {
//                    int temp = nums[left];
//                    nums[left++] = nums[right];
//                    nums[right] = temp;
//                }
//                right++;
//            }
//        }










    public void moveZeroes1(int[] nums) {
        int len = nums.length;

        for (int i = 0; i < len - 1; i++) {
            if (nums[i] == 0) {
                int temp = nums[i];
                for (int j = i; j < len - 1; j++) {
                    nums[j] = nums[j+1];
                }
                nums[len-1] = temp;
                i--;  //注意测试用例[0,0,1]
                len--; //这里len必须减1
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}