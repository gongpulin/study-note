//根据一棵树的中序遍历与后序遍历构造二叉树。 
//
// 注意: 
//你可以假设树中没有重复的元素。 
//
// 例如，给出 
//
// 中序遍历 inorder = [9,3,15,20,7]
//后序遍历 postorder = [9,15,7,20,3] 
//
// 返回如下的二叉树： 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
// Related Topics 树 深度优先搜索 数组


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：从中序与后序遍历序列构造二叉树
public class P106ConstructBinaryTreeFromInorderAndPostorderTraversal{
    public static void main(String[] args) {
        Solution solution = new P106ConstructBinaryTreeFromInorderAndPostorderTraversal().new Solution();
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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || inorder == null || postorder.length == 0 || inorder.length == 0 || postorder.length != inorder.length) {
            return null;
        }
        int len = inorder.length;
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(inorder, postorder, map, 0, len-1, 0, len-1);
    }
    private TreeNode buildTree(int[] inorder, int[] postorder, Map<Integer, Integer> map, int postorder_left, int postorder_right, int inorder_left, int inorder_right) {
        if (inorder_left > inorder_right) {
            return null;
        }
        //根节点在中序遍历的下表
        int root_index = map.get(postorder[postorder_right]);
        TreeNode root = new TreeNode(inorder[root_index]);
        int left_node_num = root_index - inorder_left;
        root.left = buildTree(inorder, postorder, map, postorder_left, postorder_left+left_node_num-1, inorder_left, root_index-1);
        root.right = buildTree(inorder, postorder, map, postorder_left+left_node_num, postorder_right-1, root_index+1, inorder_right);
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