//给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？ 
//
// 示例: 
//
// 输入: 3
//输出: 5
//解释:
//给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3 
// Related Topics 树 动态规划


package com.gpl.leetcode.leetcode.editor.cn;
//Java：不同的二叉搜索树
public class P96UniqueBinarySearchTrees{
    public static void main(String[] args) {
        Solution solution = new P96UniqueBinarySearchTrees().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numTrees(int n) {
        int[] G = new int[n+1];
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j-1] * G[i-j];
            }
        }
        return G[n];
    }
    public int numTrees1(int n) {
        long ans = 1;
        for (int i = 0; i < n; i++) {
            ans = ans * 2 * (2 * i + 1) / (i + 2);
        }
        return (int)ans;

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}