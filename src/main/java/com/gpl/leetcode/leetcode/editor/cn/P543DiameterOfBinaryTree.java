//给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。 
//
// 
//
// 示例 : 
//给定二叉树 
//
//           1
//         / \
//        2   3
//       / \     
//      4   5    
// 
//
// 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
        //
//
//
// 注意：两结点之间的路径长度是以它们之间边的数目表示。
// Related Topics 树

package com.gpl.leetcode.leetcode.editor.cn;
//Java：二叉树的直径
public class P543DiameterOfBinaryTree{
    public static void main(String[] args) {
        Solution solution = new P543DiameterOfBinaryTree().new Solution();
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
    /**
     * 1、最大值不一定包含根节点
     * 2、最大直接经过某个节点，那么最大直径为 该节点左右子树最大深度+1
     */
    class Solution {
    int ans = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return ans;
    }
    private int maxDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int l = maxDepth(node.left);
        int r = maxDepth(node.right);
        ans = Math.max(ans, l + r);
        return Math.max(l, r) + 1;
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