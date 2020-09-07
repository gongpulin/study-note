//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Stack;

//Java：验证二叉搜索树
public class P98ValidateBinarySearchTree{
    public static void main(String[] args) {
        Solution solution = new P98ValidateBinarySearchTree().new Solution();
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


    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        return help(root, null, null);
    }

    private boolean help(TreeNode node, Integer lower, Integer upper) {
        if (node == null) {
            return true;
        }
        if (lower != null && node.val <= lower) {
            return false;
        }
        if (upper != null && node.val >= upper) {
            return false;
        }
        if (!help(node.left, lower, node.val)) {
            return false;
        }
        if (!help(node.right, node.val, upper)) {
            return false;
        }
        return true;

    }


    /**
     * 中序遍历，如果中序遍历序列为递增序列则正确
     * @param root
     * @return
     */
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        int start = 0;
        int pre = 0;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                start++;
                if (start == 1) {
                    pre = cur.val;
                } else {
                    if (cur.val <= pre) {
                        return false;
                    }
                    pre = cur.val;
                }

                cur = cur.right;
            }
        }
        return true;
    }
//    public boolean isValidBST(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        double pre = -Double.MAX_VALUE;
//        Stack<TreeNode> stack = new Stack<>();
//        TreeNode cur = root;
//        while(cur != null || !stack.isEmpty()) {
//            if (cur != null) {
//                stack.push(cur);
//                cur = cur.left;
//            } else {
//                cur = stack.pop();
//                if (cur.val <= pre) {
//                    return false;
//                }
//                pre = cur.val;
//                cur = cur.right;
//            }
//        }
//        return true;
//    }


    //错误解答
//    public boolean isValidBST(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        if (((root.left == null ) || (root.left != null && root.val > root.left.val)) && ((root.right == null ) || (root.right != null && root.val < root.right.val))) {
//            return true;
//        } else {
//            return false;
//        }
//        isValidBST(root.right);
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