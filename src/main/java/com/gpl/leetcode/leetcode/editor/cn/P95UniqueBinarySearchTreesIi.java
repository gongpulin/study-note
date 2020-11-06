//给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。 
//
// 
//
// 示例： 
//
// 输入：3
//输出：
//[
//  [1,null,3,2],
//  [3,2,null,1],
//  [3,1,null,null,2],
//  [2,1,3],
//  [1,null,2,null,3]
//]
//解释：
//以上的输出对应以下 5 种不同结构的二叉搜索树：
//
//   1         3     3      2      1
//    \       /     /      / \      \
//     3     2     1      1   3      2
//    /     /       \                 \
//   2     1         2                 3
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 8 
// 
// Related Topics 树 动态规划


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.LinkedList;
import java.util.List;

//Java：不同的二叉搜索树 II
public class P95UniqueBinarySearchTreesIi{
    public static void main(String[] args) {
        Solution solution = new P95UniqueBinarySearchTreesIi().new Solution();
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
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<TreeNode>();
        }
        return generateTrees(1, n);
    }
//    public List<TreeNode> generateTrees(int start, int end) {
//        List<TreeNode> allTrees = new LinkedList<TreeNode>();
//        if (start > end) {
//            allTrees.add(null);
//            return allTrees;
//        }
//        for (int i = start; i <= end; i++) {
//            List<TreeNode> leftTrees = generateTrees(start, i-1);
//            List<TreeNode> rightTrees = generateTrees(i+1, end);
//            for (TreeNode left : leftTrees) {
//                for (TreeNode right : rightTrees) {
//                    TreeNode currTree = new TreeNode(i);
//                    currTree.left = left;
//                    currTree.right = right;
//                    allTrees.add(currTree);
//                }
//            }
//        }
//        return allTrees;
//    }



//    public List<TreeNode> generateTrees(int start, int end) {
//        List<TreeNode> allTrees = new LinkedList<>();
//        if (start > end) {
//            allTrees.add(null);
//            return allTrees;
//        }
//        for (int i = start; i <= end; i++) {
//            List<TreeNode> leftTree = generateTrees(start, i-1);
//            List<TreeNode> rightTree = generateTrees(i+1, end);
//            for (TreeNode left : leftTree) {
//                for (TreeNode right : rightTree) {
//                    TreeNode curNode = new TreeNode(i);
//                    curNode.left = left;
//                    curNode.right = right;
//                    allTrees.add(curNode);
//                }
//            }
//
//        }
//        return allTrees;
//
//    }



    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTree = generateTrees(start, i-1);
            List<TreeNode> rightTree = generateTrees(i+1, end);
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    TreeNode curNode = new TreeNode(i);
                    curNode.left = left;
                    curNode.right = right;
                    allTrees.add(curNode);
                }
            }
        }
        return allTrees;

    }
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