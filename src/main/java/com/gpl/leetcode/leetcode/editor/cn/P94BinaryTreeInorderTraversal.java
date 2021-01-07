//给定一个二叉树，返回它的中序 遍历。 
//
// 示例: 
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 哈希表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Java：二叉树的中序遍历
public class P94BinaryTreeInorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P94BinaryTreeInorderTraversal().new Solution();
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


    public List<Integer> inorderTraversal8(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                ans.add(cur.val);
                cur = cur.right;
            }
        }
        return ans;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList();
        dfs(root, ans);
        return ans;
    }
    private void dfs(TreeNode node, List<Integer> ans) {
        if (node == null) {
            return;
        }
        dfs(node.left, ans);
        ans.add(node.val);
        dfs(node.right, ans);
    }



    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null ) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }

        }
        return res;
    }

//    public List<Integer> inorderTraversal1(TreeNode root) {
//        List<Integer> res = new ArrayList<>();
//        if (root != null ) {
//            traversal(root, res);
//        }
//        return res;
//    }
//    private void traversal(TreeNode node, List<Integer> res) {
//        if (node != null) {
//            if (node.left != null ) {
//                traversal(node.left, res);
//            }
//            res.add(node.val);
//            if (node.right != null ) {
//                traversal(node.right, res);
//            }
//        }
//    }

    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if ( root == null ) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while( cur != null || !stack.isEmpty() ) {
            while ( cur != null ) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }
        return res;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
//leetcode submit region end(Prohibit modification and deletion)

}