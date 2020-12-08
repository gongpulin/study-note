//给出 N 名运动员的成绩，找出他们的相对名次并授予前三名对应的奖牌。前三名运动员将会被分别授予 “金牌”，“银牌” 和“ 铜牌”（"Gold Medal",
// "Silver Medal", "Bronze Medal"）。 
//
// (注：分数越高的选手，排名越靠前。) 
//
// 示例 1: 
//
// 
//输入: [5, 4, 3, 2, 1]
//输出: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
//解释: 前三名运动员的成绩为前三高的，因此将会分别被授予 “金牌”，“银牌”和“铜牌” ("Gold Medal", "Silver Medal" and 
//"Bronze Medal").
//余下的两名运动员，我们只需要通过他们的成绩计算将其相对名次即可。 
//
// 提示: 
//
// 
// N 是一个正整数并且不会超过 10000。 
// 所有运动员的成绩都不相同。 
// 
//


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Java：相对名次
public class P506RelativeRanks{
    public static void main(String[] args) {
        Solution solution = new P506RelativeRanks().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String[] findRelativeRanks(int[] nums) {
        int[] cloneNums = nums.clone();
        Arrays.sort(cloneNums);
        Map<Integer, Integer> map = new HashMap<>();
        int len = nums.length;
        for (int i = len - 1; i >= 0; i--) {
            map.put(cloneNums[i], len - i);
        }
        String[] ans = new String[len];
        int index = 0;
        for (int num : nums) {
            int rank = map.get(num);
            if (rank == 1) {
                ans[index++] = "Gold Medal";
            } else if (rank == 2) {
                ans[index++] = "Silver Medal";
            } else if (rank == 3) {
                ans[index++] = "Bronze Medal";
            } else {
                ans[index++] = rank + "";
            }
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}