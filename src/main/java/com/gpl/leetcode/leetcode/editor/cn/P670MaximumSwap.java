//给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。 
//
// 示例 1 : 
//
// 
//输入: 2736
//输出: 7236
//解释: 交换数字2和数字7。
// 
//
// 示例 2 : 
//
// 
//输入: 9973
//输出: 9973
//解释: 不需要交换。
// 
//
// 注意: 
//
// 
// 给定数字的范围是 [0, 108] 
// 
// Related Topics 数组 数学


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：最大交换
public class P670MaximumSwap{
    public static void main(String[] args) {
        Solution solution = new P670MaximumSwap().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximumSwap(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        int len = arr.length;
        int[] maxArr = new int[len];
        int max = len - 1;
        for (int i = len - 1; i >= 0; i--) {
            if (arr[i] > arr[max]) {
                max = i;
                maxArr[i] = -1;
            } else {
                maxArr[i] = max;
            }
        }
        for (int i = 0; i < len; i++) {
            if (maxArr[i] != -1 && arr[i] != arr[maxArr[i]]) {
                char temp = arr[i];
                arr[i] = arr[maxArr[i]];
                arr[maxArr[i]] = temp;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(arr));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}