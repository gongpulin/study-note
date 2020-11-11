//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 5
//输出:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//] 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：杨辉三角
public class P118PascalsTriangle{
    public static void main(String[] args) {
        Solution solution = new P118PascalsTriangle().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> ans = new ArrayList<>();
        if (numRows == 0) {
            return ans;
        }
        ans.add(new ArrayList<>());
        ans.get(0).add(1);
        for (int i = 1; i < numRows; i++) {
            List<Integer> rows = new ArrayList<>();
            List<Integer> preRow = ans.get(i - 1);
            rows.add(1);
            for (int j = 1; j < i; j++) {
                rows.add(preRow.get(j - 1) + preRow.get(j));
            }
            rows.add(1);
            ans.add(rows);
        }
        return ans;
    }
//    private void generateByRow(int row, List<List<Integer>> ans) {
//        List<Integer> rows = new ArrayList<>();
//        for (int i = 1; i <= row; i++) {
//
//        }
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}