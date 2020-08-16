//给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。 
//
// 例如，从根到叶子节点路径 1->2->3 代表数字 123。 
//
// 计算从根到叶子节点生成的所有数字之和。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//    1
//   / \
//  2   3
//输出: 25
//解释:
//从根到叶子节点路径 1->2 代表数字 12.
//从根到叶子节点路径 1->3 代表数字 13.
//因此，数字总和 = 12 + 13 = 25. 
//
// 示例 2: 
//
// 输入: [4,9,0,5,1]
//    4
//   / \
//  9   0
// / \
//5   1
//输出: 1026
//解释:
//从根到叶子节点路径 4->9->5 代表数字 495.
//从根到叶子节点路径 4->9->1 代表数字 491.
//从根到叶子节点路径 4->0 代表数字 40.
//因此，数字总和 = 495 + 491 + 40 = 1026. 
// Related Topics 树 深度优先搜索


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.Queue;

//Java：求根到叶子节点数字之和
public class P129SumRootToLeafNumbers{
    public static void main(String[] args) {
        Solution solution = new P129SumRootToLeafNumbers().new Solution();
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
    public int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        help(root, 0);
        return ans;
    }
    public void help(TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        sum = sum * 10 + node.val;
        if (node.left == null && node.right == null) {
            ans = ans + sum;
        }
        help(node.left, sum);
        help(node.right, sum);
        return;
    }
    public int sumNumbers1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int ans = 0;
        Queue<TreeNode> queNode = new LinkedList<>();
        Queue<Integer> queVal = new LinkedList<>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode node = queNode.poll();
            int v = queVal.poll();
            if (node.left == null && node.right == null) {
                ans = ans + v;
            }
            if (node.left != null) {
                queNode.offer(node.left);
                queVal.offer(node.left.val + 10 * v);
            }
            if (node.right != null) {
                queNode.offer(node.right);
                queVal.offer(node.right.val + 10 * v);
            }

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