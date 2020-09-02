//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：括号生成
public class P22GenerateParentheses{
    public static void main(String[] args) {
        Solution solution = new P22GenerateParentheses().new Solution();
        // TO TEST
        solution.generateParenthesis(4);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }
    // open:左括号的数量 ，   close:右括号的数量
    private void backtrack(List<String> ans, StringBuilder path, int open, int close, int n) {
        if (path.length() == n * 2) {
            ans.add(path.toString());
//            System.out.println(path.toString());
            return;
        }
        if (open < n) {
            path.append('(');
            backtrack(ans, path, open+1, close, n);
            path.deleteCharAt(path.length()-1);
        }
        if (close < open) {
            path.append(')');
            backtrack(ans, path, open, close+1, n);
            path.deleteCharAt(path.length()-1);
        }

    }



}
//leetcode submit region end(Prohibit modification and deletion)

}