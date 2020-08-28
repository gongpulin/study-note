//根据一棵树的前序遍历与中序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 前序遍历 preorder = [3,9,20,15,7]
//中序遍历 inorder = [9,3,15,20,7] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
// Related Topics 树 深度优先搜索 数组


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：从前序与中序遍历序列构造二叉树
public class P105ConstructBinaryTreeFromPreorderAndInorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P105ConstructBinaryTreeFromPreorderAndInorderTraversal().new Solution();
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        int len = inorder.length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, map, 0, len -1, 0, len - 1);
    }
    public TreeNode buildTree(int[] preorder, int[] inorder, Map<Integer, Integer> map, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        //根节点在中序遍历的下标
        int root_index = map.get(preorder[preorder_left]);
        TreeNode root = new TreeNode(inorder[root_index]);
        //左边节点数量
        int left_node_num = root_index - inorder_left;
        root.left = buildTree(preorder, inorder, map, preorder_left+1, preorder_left+left_node_num, inorder_left, root_index -1);
        root.right = buildTree(preorder, inorder, map, preorder_left+left_node_num+1, preorder_right, root_index+1, inorder_right);

        return root;
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