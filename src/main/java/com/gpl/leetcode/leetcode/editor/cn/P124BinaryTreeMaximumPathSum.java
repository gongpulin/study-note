//给定一个非空二叉树，返回其最大路径和。 
//
// 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//
//       1
//      / \
//     2   3
//
//输出: 6
// 
//
// 示例 2: 
//
// 输入: [-10,9,20,null,null,15,7]
//
//   -10
//   / \
//  9  20
//    /  \
//   15   7
//
//输出: 42 
// Related Topics 树 深度优先搜索


package com.gpl.leetcode.leetcode.editor.cn;
//Java：二叉树中的最大路径和
public class P124BinaryTreeMaximumPathSum{
    public static void main(String[] args) {
        Solution solution = new P124BinaryTreeMaximumPathSum().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int ans = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return ans;
    }

    //计算每个节点的贡献值
    private int maxGain(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //计算左右孩子的贡献值，只有当孩子贡献值大于零才加入孩子
        int l = Math.max(0,maxGain(root.left));
        int r = Math.max(0,maxGain(root.right));

        ans = Math.max(ans, l+r+root.val);
        return Math.max(l, r)+root.val;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
}