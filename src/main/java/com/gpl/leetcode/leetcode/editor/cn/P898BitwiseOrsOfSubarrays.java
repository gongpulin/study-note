//我们有一个非负整数数组 A。 
//
// 对于每个（连续的）子数组 B = [A[i], A[i+1], ..., A[j]] （ i <= j），我们对 B 中的每个元素进行按位或操作，获得结果
// A[i] | A[i+1] | ... | A[j]。 
//
// 返回可能结果的数量。 （多次出现的结果在最终答案中仅计算一次。） 
//
// 
//
// 示例 1： 
//
// 输入：[0]
//输出：1
//解释：
//只有一个可能的结果 0 。
// 
//
// 示例 2： 
//
// 输入：[1,1,2]
//输出：3
//解释：
//可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。
//产生的结果为 1，1，2，1，3，3 。
//有三个唯一值，所以答案是 3 。
// 
//
// 示例 3： 
//
// 输入：[1,2,4]
//输出：6
//解释：
//可能的结果是 1，2，3，4，6，以及 7 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= A.length <= 50000 
// 0 <= A[i] <= 10^9 
// 
// Related Topics 位运算 动态规划


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

//Java：子数组按位或操作
public class P898BitwiseOrsOfSubarrays{
    public static void main(String[] args) {
        Solution solution = new P898BitwiseOrsOfSubarrays().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int subarrayBitwiseORs1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        Set<Integer> ans = new HashSet<>();
        Set<Integer> cur = new HashSet<>();
        cur.add(0);
        for (int x : arr) {
            Set<Integer> cur2 = new HashSet<>();
            for (int y : cur) {
                cur2.add(x | y);
            }
            cur2.add(x);
            cur = cur2;
            ans.addAll(cur);
        }
        return ans.size();
    }


        //超时
    public int subarrayBitwiseORs1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1 && arr[0] == 0) {
            return 1;
        }
        int len = arr.length;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < len; i++) {
            int res = arr[i];
            set.add(res);
            for (int j = i + 1; j < len; j++) {
                res |= arr[j];
                set.add(res);
            }
        }
        return set.size();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}