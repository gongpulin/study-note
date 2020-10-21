//给定一组非负整数 nums，重新排列它们每位数字的顺序使之组成一个最大的整数。 
//
// 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [10,2]
//输出："210" 
//
// 示例 2： 
//
// 输入：nums = [3,30,34,5,9]
//输出："9534330"
// 
//
// 示例 3： 
//
// 输入：nums = [1]
//输出："1"
// 
//
// 示例 4： 
//
// 输入：nums = [10]
//输出："10"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 109 
// 
// Related Topics 排序


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;

//Java：最大数
public class P179LargestNumber{
    public static void main(String[] args) {
        Solution solution = new P179LargestNumber().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "";
        }
        int len = nums.length;
        String[] strArr = new String[len];
        for (int i = 0; i < len; i++) {
            strArr[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strArr, new Comparator<String>() { //o1 - o2从小到大，   o2 - o1从大到小
            @Override
            public int compare(String s, String t1) {
                return (t1+s).compareTo(s+t1);
            }
        });
        if (strArr[0].equals("0")) {
            return "0";
        }
        StringBuilder ans = new StringBuilder();
        for (String s : strArr) {
            ans.append(s);
        }
        return ans.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}