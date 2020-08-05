//给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//给定如下二叉树，以及目标和 sum = 22， 
//
//               5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \    / \
//        7    2  5   1
// 
//
// 返回: 
//
// [
//   [5,4,11,2],
//   [5,8,4,5]
//]
// 
// Related Topics 树 深度优先搜索


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//Java：路径总和 II
public class P113PathSumIi{
    public static void main(String[] args) {
        Solution solution = new P113PathSumIi().new Solution();
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        List<Integer> path = new ArrayList<>();
        DFS(root, ans, path, sum);
        return ans;
    }
    private void DFS(TreeNode node, List<List<Integer>> ans, List<Integer> path, int sum) {
        if (node == null) {
            return;
        }
        path.add(node.val);
        if (node.left == null && node.right == null && node.val == sum) {
            ans.add(new ArrayList<>(path)); //注意这里必须做深拷贝
        }
        DFS(node.left, ans, path, sum - node.val);
        DFS(node.right, ans, path, sum - node.val);
        path.remove(path.size()-1);
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