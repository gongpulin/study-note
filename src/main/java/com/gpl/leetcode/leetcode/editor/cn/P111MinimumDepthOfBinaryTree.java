//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最小深度 2. 
// Related Topics 树 深度优先搜索 广度优先搜索


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//Java：二叉树的最小深度
public class P111MinimumDepthOfBinaryTree{
    public static void main(String[] args) {
        Solution solution = new P111MinimumDepthOfBinaryTree().new Solution();
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

    //BFS 广度优先遍历
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            depth++;
        }
        return depth;
    }


    //深度优先遍历
//    public int minDepth(TreeNode root) {
//        if ( root == null ) {
//            return 0;
//        }
//        if ( root.left == null && root.right == null) {
//            return 1;
//        }
//        int MIN_DEPTH = Integer.MAX_VALUE;
//        if ( root.left != null ) {
//            MIN_DEPTH = Math.min(MIN_DEPTH, minDepth(root.left));
//        }
//        if ( root.right != null ) {
//            MIN_DEPTH = Math.min(MIN_DEPTH, minDepth(root.right));
//        }
//        return MIN_DEPTH + 1;
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