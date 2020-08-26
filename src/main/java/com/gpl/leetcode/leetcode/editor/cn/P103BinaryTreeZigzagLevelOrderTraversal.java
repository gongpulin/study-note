//给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。 
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回锯齿形层次遍历如下： 
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
// 
// Related Topics 栈 树 广度优先搜索


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Java：二叉树的锯齿形层次遍历
public class P103BinaryTreeZigzagLevelOrderTraversal{
    public static void main(String[] args) {
        Solution solution = new P103BinaryTreeZigzagLevelOrderTraversal().new Solution();
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        LinkedList<TreeNode> queue = new LinkedList<>();
        boolean l2r = true;  //从左到右遍历，反之从右到左
        queue.offer(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> path = new ArrayList<>();
            while(size > 0) {
                if (l2r) {
                    TreeNode node = queue.pollFirst();
                    path.add(node.val);
                    if (node.left != null) {
                        queue.offerLast(node.left);
                    }
                    if (node.right != null) {
                        queue.offerLast(node.right);
                    }
                } else {
                    TreeNode node = queue.pollLast();
                    path.add(node.val);
                    if (node.right != null) {   //注意这里是先将右节点加入队列
                        queue.offerFirst(node.right);
                    }
                    if (node.left != null) {
                        queue.offerFirst(node.left);
                    }

                }
                size--;

            }
            ans.add(path);
            l2r = !l2r;
        }
        return ans;
    }
//    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
//        List<List<Integer>> ans = new ArrayList<>();
//        if (root == null) {
//            return ans;
//        }
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        boolean l2r = true;
//        queue.offer(root);
//        while(!queue.isEmpty()) {
//            int size = queue.size();
//            List<Integer> path = new ArrayList<>();
//            while(size > 0) {
//                if (l2r) {
//                    TreeNode node = queue.pollLast();
//                    path.add(node.val);
//                    if (node.left != null) {
//                        queue.offerFirst(node.left);
//                    }
//                    if (node.right != null) {
//                        queue.offerFirst(node.right);
//                    }
//                } else {
//                    TreeNode node = queue.pollFirst();
//                    path.add(node.val);
//                    if (node.right != null) {
//                        queue.offerLast(node.right);
//                    }
//                    if (node.left != null) {
//                        queue.offerLast(node.left);
//                    }
//
//                }
//                size--;
//
//            }
//            ans.add(path);
//            l2r = !l2r;
//        }
//        return ans;
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