//在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。 
//
// 给定行数 N 和序数 K，返回第 N 行中第 K个字符。（K从1开始） 
//
// 
//例子: 
//
// 输入: N = 1, K = 1
//输出: 0
//
//输入: N = 2, K = 1
//输出: 0
//
//输入: N = 2, K = 2
//输出: 1
//
//输入: N = 4, K = 5
//输出: 1
//
//解释:
//第一行: 0
//第二行: 01
//第三行: 0110
//第四行: 01101001
//       0110100110010110
// 
//
// 
//注意： 
//
// 
// N 的范围 [1, 30]. 
// K 的范围 [1, 2^(N-1)]. 
// 
// Related Topics 递归


package com.gpl.leetcode.leetcode.editor.cn;
//Java：第K个语法符号
public class P779KThSymbolInGrammar{
    public static void main(String[] args) {
        Solution solution = new P779KThSymbolInGrammar().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int kthGrammar(int N, int K) {
        return Integer.bitCount(K - 1) % 2;
    }
    public int kthGrammar1(int N, int K) {
        if (N == 1) {
            return 0;
        }
        return (~K & 1) ^ kthGrammar(N-1, (K+1)/2);
    }



        //Memory Limit Exceeded
    public int kthGrammar2(int N, int K) {
        String s = dfs(N);
        return s.charAt(K - 1) == '0' ? 0 : 1;
    }
    private String dfs(int N) {
        if (N == 1) {
            return "0";
        }
        String s = dfs(N - 1);
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                ans.append("01");
            } else {
                ans.append("10");
            }
        }
        return ans.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}