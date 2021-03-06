//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。 
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Java：二叉树的层序遍历
public class P102BinaryTreeLevelOrderTraversal{
    public static void main(String[] args) {
        Solution solution = new P102BinaryTreeLevelOrderTraversal().new Solution();
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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> l = new ArrayList();
            while (size > 0) {
                TreeNode node = queue.poll();
                l.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans.add(l);
        }
        return ans;
    }










    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if ( root == null ) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while ( !queue.isEmpty() ) {
            int size = queue.size();
            List<Integer> l = new ArrayList<>();
            while ( size > 0 ) {
                TreeNode node = queue.poll();
                l.add(node.val);
                if ( node.left != null ) {
                    queue.offer(node.left);
                }
                if ( node.right != null ) {
                    queue.offer(node.right);
                }
                size--;
            }
            ans.add(l);
        }
        return ans;
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