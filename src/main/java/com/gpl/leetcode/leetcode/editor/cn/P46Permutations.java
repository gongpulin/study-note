//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：全排列
public class P46Permutations{
    public static void main(String[] args) {
        Solution solution = new P46Permutations().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return ans;
        }
        backtrack(ans, new ArrayList(), nums);
        return ans;
    }
    private void backtrack(List<List<Integer>> ans, List<Integer> path, int[] nums) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList(path));
            return;
        }
        for (int num : nums) {
            if (path.contains(num)) {
                continue;
            } else {
                path.add(num);
                backtrack(ans, path, nums);
                path.remove(path.size()-1);
            }
        }
        return;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}