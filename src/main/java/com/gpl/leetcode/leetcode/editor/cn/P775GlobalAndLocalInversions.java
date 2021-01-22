//数组 A 是 [0, 1, ..., N - 1] 的一种排列，N 是数组 A 的长度。全局倒置指的是 i,j 满足 0 <= i < j < N 并且 A
//[i] > A[j] ，局部倒置指的是 i 满足 0 <= i < N 并且 A[i] > A[i+1] 。 
//
// 当数组 A 中全局倒置的数量等于局部倒置的数量时，返回 true 。 
//
// 
//
// 示例 1: 
//
// 
//输入: A = [1,0,2]
//输出: true
//解释: 有 1 个全局倒置，和 1 个局部倒置。
// 
//
// 示例 2: 
//
// 
//输入: A = [1,2,0]
//输出: false
//解释: 有 2 个全局倒置，和 1 个局部倒置。
// 
//
// 注意: 
//
// 
// A 是 [0, 1, ..., A.length - 1] 的一种排列 
// A 的长度在 [1, 5000]之间 
// 这个问题的时间限制已经减少了。 
// 
// Related Topics 数组 数学


package com.gpl.leetcode.leetcode.editor.cn;
//Java：全局倒置与局部倒置
public class P775GlobalAndLocalInversions{
    public static void main(String[] args) {
        Solution solution = new P775GlobalAndLocalInversions().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public boolean isIdealPermutation(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        int len = A.length;
        int rightMin = len;
        for (int i = len - 1; i >= 2; i--) {
            rightMin = Math.min(rightMin, A[i]);
            if (A[i - 2] > rightMin) {
                return false;
            }
        }
        return true;
    }


    public boolean isIdealPermutation1(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        int len = A.length;
        for (int i = 0; i < len; i++) {
            if (Math.abs(A[i] - i) > 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isIdealPermutation2(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        int len = A.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 2; j < len; j++) {
                if (A[i] > A[j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean isIdealPermutation3(int[] A) {
        if (A == null || A.length == 0) {
            return false;
        }
        int global = 0, bart = 0;
        int len = A.length;
        for (int i = 0; i < len - 1; i++) {
            if (A[i] > A[i + 1]) {
                bart++;
            }
            for (int j = i + 1; j < len; j++) {
                if (A[i] > A[j]) {
                    global++;
                }
            }
        }
        return global == bart;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}