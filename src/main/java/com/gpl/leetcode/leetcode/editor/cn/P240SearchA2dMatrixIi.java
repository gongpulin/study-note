//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性： 
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 示例: 
//
// 现有矩阵 matrix 如下： 
//
// [
//  [1,   4,  7, 11, 15],
//  [2,   5,  8, 12, 19],
//  [3,   6,  9, 16, 22],
//  [10, 13, 14, 17, 24],
//  [18, 21, 23, 26, 30]
//]
// 
//
// 给定 target = 5，返回 true。 
//
// 给定 target = 20，返回 false。 
// Related Topics 二分查找 分治算法


package com.gpl.leetcode.leetcode.editor.cn;
//Java：搜索二维矩阵 II
public class P240SearchA2dMatrixIi{
    public static void main(String[] args) {
        Solution solution = new P240SearchA2dMatrixIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        //时间复杂度O(m+n)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }


        /**
         * 错误答案，注意和 P74的区别
         */
        public boolean searchMatrix1(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                return false;
            }
            int m = matrix.length, n = matrix[0].length;
            int left = 0, right = m * n - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                int midValue = matrix[mid / n][mid % n];
                if (midValue == target) {
                    return true;
                } else if (midValue > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return false;
        }
}
//leetcode submit region end(Prohibit modification and deletion)

}