//给定一个 m x n 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用原地算法。 
//
// 示例 1: 
//
// 输入: 
//[
//  [1,1,1],
//  [1,0,1],
//  [1,1,1]
//]
//输出: 
//[
//  [1,0,1],
//  [0,0,0],
//  [1,0,1]
//]
// 
//
// 示例 2: 
//
// 输入: 
//[
//  [0,1,2,0],
//  [3,4,5,2],
//  [1,3,1,5]
//]
//输出: 
//[
//  [0,0,0,0],
//  [0,4,5,0],
//  [0,3,1,0]
//] 
//
// 进阶: 
//
// 
// 一个直接的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。 
// 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。 
// 你能想出一个常数空间的解决方案吗？ 
// 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

//Java：矩阵置零
public class P73SetMatrixZeroes{
    public static void main(String[] args) {
        Solution solution = new P73SetMatrixZeroes().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public void setZeroes(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            int m = matrix.length, n = matrix[0].length;
            Set<Integer> rows = new HashSet<>();
            Set<Integer> cols = new HashSet<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        rows.add(i);
                        cols.add(j);
                    }
                }
            }
            for (int i : rows) {
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
            for (int i : cols) {
                for (int j = 0; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }




        public void setZeroes1(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                return;
            }
            int MODIFIED = -100000000;
            int m = matrix.length, n = matrix[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == 0) {
                        for (int k = 0; k < m; k++) {
                            if (matrix[k][j] != 0) {
                                matrix[k][j] = MODIFIED;
                            }
                        }
                        for (int k = 0; k < n; k++) {
                            if (matrix[i][k] != 0) {
                                matrix[i][k] = MODIFIED;
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] == MODIFIED) {
                        matrix[i][j] = 0;
                    }
                }
            }

        }





    public void setZeroes2(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return;
        }
        int m = matrix.length, n = matrix[0].length;
        int[][] points = new int[m*n][2];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    points[index][0] = i;
                    points[index++][1] = j;
                }
            }
        }
        for (int i = 0; i < index; i++) {
            int[] point = points[i];
            for (int k = 0; k < m; k++) {
                matrix[k][point[1]] = 0;
            }
            for (int l = 0; l < n; l++) {
                matrix[point[0]][l] = 0;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}