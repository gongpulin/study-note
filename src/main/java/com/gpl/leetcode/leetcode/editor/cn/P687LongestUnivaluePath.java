//给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。 
//
// 注意：两个节点之间的路径长度由它们之间的边数表示。 
//
// 示例 1: 
//
// 输入: 
//
// 
//              5
//             / \
//            4   5
//           / \   \
//          1   1   5
// 
//
// 输出: 
//
// 
//2
// 
//
// 示例 2: 
//
// 输入: 
//
// 
//              1
//             / \
//            4   5
//           / \   \
//          4   4   5
// 
//
// 输出: 
//
// 
//2
// 
//
// 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。 
// Related Topics 树 递归


package com.gpl.leetcode.leetcode.editor.cn;
//Java：最长同值路径
public class P687LongestUnivaluePath{
    public static void main(String[] args) {
        Solution solution = new P687LongestUnivaluePath().new Solution();
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
    int ans = 0;
    public int longestUnivaluePath(TreeNode root) {
        depth(root);
        return ans;
    }

    private int depth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = depth(node.left);
        int rightDepth = depth(node.right);
        int arrowLeft = 0, arrowRight = 0;
        if (node.left != null && node.left.val == node.val) {
            arrowLeft += leftDepth+1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight += rightDepth+1;
        }
        ans = Math.max(ans, arrowLeft+arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }

//    private int depth(TreeNode node) {
//        if (node == null) {
//            return 0;
//        }
//        int leftDepth = depth(node.left);
//        int rightDepth = depth(node.right);
//        int arrowLeft = 0, arrowRight = 0;
//        if (node.left != null && node.left.val == node.val) {
//            leftDepth++;
//        }
//        if (node.right != null && node.right.val == node.val) {
//            rightDepth++;
//        }
//        ans = Math.max(ans, leftDepth+rightDepth);
//        return Math.max(leftDepth, rightDepth);
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
}