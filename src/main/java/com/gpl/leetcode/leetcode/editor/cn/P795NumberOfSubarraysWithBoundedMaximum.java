//给定一个元素都是正整数的数组A ，正整数 L 以及 R (L <= R)。 
//
// 求连续、非空且其中最大元素满足大于等于L 小于等于R的子数组个数。 
//
// 例如 :
//输入: 
//A = [2, 1, 4, 3]
//L = 2
//R = 3
//输出: 3
//解释: 满足条件的子数组: [2], [2, 1], [3].
// 
//
// 注意: 
//
// 
// L, R 和 A[i] 都是整数，范围在 [0, 10^9]。 
// 数组 A 的长度范围在[1, 50000]。 
// 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;
//Java：区间子数组个数
public class P795NumberOfSubarraysWithBoundedMaximum{
    public static void main(String[] args) {
        Solution solution = new P795NumberOfSubarraysWithBoundedMaximum().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        if (A == null) {
            return 0;
        }
        return count(A, R) - count(A, L - 1);
    }
    private int count(int[] A, int bound) {
        int ans = 0, cur = 0;
        for (int x : A) {
            cur = x <= bound ? cur + 1 : 0;
            ans += cur;
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}