//两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。 
//
// 计算一个数组中，任意两个数之间汉明距离的总和。 
//
// 示例: 
//
// 
//输入: 4, 14, 2
//
//输出: 6
//
//解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
//所以答案为：
//HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 
//2 + 2 = 6.
// 
//
// 注意: 
//
// 
// 数组中元素的范围为从 0到 10^9。 
// 数组的长度不超过 10^4。 
// 
// Related Topics 位运算


package com.gpl.leetcode.leetcode.editor.cn;
//Java：汉明距离总和
public class P477TotalHammingDistance{
    public static void main(String[] args) {
        Solution solution = new P477TotalHammingDistance().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int totalHammingDistance(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return 0;
        }
        int ans = 0;
        int len = nums.length;
        for (int i = 0; i < 32; i++) {
            int num0 = 0;
            for (int j = 0; j < len; j++) {
                if ((nums[j] & 1) == 0) {
                    num0++;
                }
                nums[j] >>= 1;
            }
            ans += num0 * (len - num0);
        }
        return ans;
    }
    public int totalHammingDistance1(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        int ans = 0;
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                ans += getDistince(nums[i], nums[j]);
            }
        }
        return ans;
    }
    private int getDistince(int num1, int num2) {
        int ans = 0;
        int diff = num1 ^ num2;
        while (diff != 0) {
            diff &= (diff - 1);
            ans++;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}