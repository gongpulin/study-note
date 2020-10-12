//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性： 
//
// 
// 每行中的整数从左到右按升序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 示例 1: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//matrix = [
//  [1,   3,  5,  7],
//  [10, 11, 16, 20],
//  [23, 30, 34, 50]
//]
//target = 13
//输出: false 
// Related Topics 数组 二分查找


package com.gpl.leetcode.leetcode.editor.cn;
//Java：搜索二维矩阵
public class P74SearchA2dMatrix{
    public static void main(String[] args) {
        Solution solution = new P74SearchA2dMatrix().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
        //matrix = [
        //  [1,   3,  5,  7],
        //  [10, 11, 16, 20],
        //  [23, 30, 34, 50]
        //]
        //[ m=3,n=5
//            [1,2,3,4,5]
//            [6,7,8,9,10]
//            [11,12,13,14,15]
        //时间复杂度O(log(mn))
        public boolean searchMatrix1(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                return false;
            }

            int m = matrix.length, n = matrix[0].length;
            int left = 0, right = m * n - 1;
            while (left <= right) {
                int midIndex = left + (right - left) / 2;
                int midElement = matrix[midIndex / n][midIndex % n]; //注意这个获取中间数的方式
                if (midElement == target) {
                    return true;
                } else if (midElement < target) {
                    left = midIndex + 1;
                } else {
                    right = midIndex - 1;
                }
            }
            return false;
        }

        //时间复杂度 O(m+n)
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}