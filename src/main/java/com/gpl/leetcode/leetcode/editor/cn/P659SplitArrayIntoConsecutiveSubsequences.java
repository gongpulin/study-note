//给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。 
//
// 如果可以完成上述分割，则返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入: [1,2,3,3,4,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3
//3, 4, 5
// 
//
// 示例 2： 
//
// 
//输入: [1,2,3,3,4,4,5,5]
//输出: True
//解释:
//你可以分割出这样两个连续子序列 : 
//1, 2, 3, 4, 5
//3, 4, 5
// 
//
// 示例 3： 
//
// 
//输入: [1,2,3,4,4,5]
//输出: False
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10000 
// 
// Related Topics 堆 贪心算法


package com.gpl.leetcode.leetcode.editor.cn;

import org.apache.zookeeper.server.quorum.SendAckRequestProcessor;

import java.util.HashMap;
import java.util.Map;

//Java：分割数组为连续子序列
public class P659SplitArrayIntoConsecutiveSubsequences{
    public static void main(String[] args) {
        Solution solution = new P659SplitArrayIntoConsecutiveSubsequences().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPossible(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int len = nums.length;
        Map<Integer, Integer> countMap = new HashMap();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        Map<Integer, Integer> endMap = new HashMap();
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0);
            if (count > 0) {
                int preEvenCount = endMap.getOrDefault(num - 1, 0);
                if (preEvenCount > 0) {
                    countMap.put(num, count - 1);
                    endMap.put(num - 1, preEvenCount - 1);
                    endMap.put(num, endMap.getOrDefault(num, 0) + 1);
                } else {
                    int count1 = countMap.getOrDefault(num + 1, 0);
                    int count2 = countMap.getOrDefault(num + 2, 0);
                    if (count1 > 0 && count2 > 0) {
                        countMap.put(num, count - 1);
                        countMap.put(num + 1, count1 - 1);
                        countMap.put(num + 2, count2 - 1);
                        endMap.put(num + 2, endMap.getOrDefault(num + 2, 0) + 1);
                    } else {
                        return false;
                    }

                }
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}