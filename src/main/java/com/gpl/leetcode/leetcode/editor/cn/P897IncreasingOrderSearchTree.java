//给你一个树，请你 按中序遍历 重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。 
//
// 
//
// 示例 ： 
//
// 输入：[5,3,6,2,4,null,8,1,null,null,null,7,9]
//
//       5
//      / \
//    3    6
//   / \    \
//  2   4    8
// /        / \ 
//1        7   9
//
//输出：[1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]
//
// 1
//  \
//   2
//    \
//     3
//      \
//       4
//        \
//         5
//          \
//           6
//            \
//             7
//              \
//               8
//                \
//                 9  
//
// 
//
// 提示： 
//
// 
// 给定树中的结点数介于 1 和 100 之间。 
// 每个结点都有一个从 0 到 1000 范围内的唯一整数值。 
// 
// Related Topics 树 深度优先搜索 递归


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：递增顺序查找树
public class P897IncreasingOrderSearchTree{
    public static void main(String[] args) {
        Solution solution = new P897IncreasingOrderSearchTree().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    TreeNode cur;
    public TreeNode increasingBST(TreeNode root) {
        TreeNode head = new TreeNode();
        cur = head;
        dfs(root);
        return head.right;
    }
    private void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.left);
        cur.right = node;
        node.left = null;
        cur = node;
        dfs(node.right);
    }

//    public TreeNode increasingBST(TreeNode root) {
//        List<Integer> values = new ArrayList();
//        dfs(root, values);
//        TreeNode head = new TreeNode();
//        TreeNode cur = head;
//        for (int val : values) {
//            TreeNode node = new TreeNode(val);
//            cur.right = node;
//            cur.left = null;
//            cur = cur.right;
//        }
//        return head.right;
//    }
//    private void dfs(TreeNode node, List<Integer> list) {
//        if (node == null) {
//            return;
//        }
//        dfs(node.left, list);
//        list.add(node.val);
//        dfs(node.right, list);
//
//    }

    //错误答案   Error Found cycle in the TreeNode
//    public TreeNode increasingBST(TreeNode root) {
//        List<TreeNode> nodes = new ArrayList();
//        dfs(root, nodes);
//        TreeNode head = new TreeNode();
//        TreeNode cur = head;
//        for (TreeNode node : nodes) {
//            cur.left = null;
//            cur.right = node;
//            cur = cur.right;
//        }
//        return head.right;
//    }
//    private void dfs(TreeNode node, List<TreeNode> list) {
//        if (node == null) {
//            return;
//        }
//        dfs(node.left, list);
//        list.add(node);
//        dfs(node.right, list);
//
//    }
}
//leetcode submit region end(Prohibit modification and deletion)
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
}