//给定一个 m x n 的非负整数矩阵来表示一片大陆上各个单元格的高度。“太平洋”处于大陆的左边界和上边界，而“大西洋”处于大陆的右边界和下边界。 
//
// 规定水流只能按照上、下、左、右四个方向流动，且只能从高到低或者在同等高度上流动。 
//
// 请找出那些水流既可以流动到“太平洋”，又能流动到“大西洋”的陆地单元的坐标。 
//
// 
//
// 提示： 
//
// 
// 输出坐标的顺序不重要 
// m 和 n 都小于150 
// 
//
// 
//
// 示例： 
//
// 
//
// 
//给定下面的 5x5 矩阵:
//
//  太平洋 ~   ~   ~   ~   ~ 
//       ~  1   2   2   3  (5) *
//       ~  3   2   3  (4) (4) *
//       ~  2   4  (5)  3   1  *
//       ~ (6) (7)  1   4   5  *
//       ~ (5)  1   1   2   4  *
//          *   *   *   *   * 大西洋
//
//返回:
//
//[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (上图中带括号的单元).
// 
//
// 
// Related Topics 深度优先搜索 广度优先搜索


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Java：太平洋大西洋水流问题
public class P417PacificAtlanticWaterFlow{
    public static void main(String[] args) {
        Solution solution = new P417PacificAtlanticWaterFlow().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    private int[][] direction = {{0,1},{0,-1},{1,0},{-1,0}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList();
        if (matrix == null | matrix.length == 0) {
            return ans;
        }

        int m = matrix.length, n = matrix[0].length;
        boolean[][] dvisit = new boolean[m][n];
        boolean[][] tvisit = new boolean[m][n];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (!dvisit[i][j] && (i == m - 1 || j == n - 1)) {
//                    dfs(matrix, i, j, dvisit, matrix[i][j]);
//                }
//                if (!tvisit[i][j] && (i == 0 || j == 0)) {
//                    dfs(matrix, i, j, tvisit, matrix[i][j]);
//                }
//            }
//        }
        //等价与上面的
        for (int i = 0; i < m; i++) {
            dfs(matrix, i, n - 1, dvisit, matrix[i][n - 1]);
            dfs(matrix, i, 0, tvisit, matrix[i][0]);
        }
        for (int j = 0; j < n; j++) {
            dfs(matrix, m - 1, j, dvisit, matrix[m - 1][j]);
            dfs(matrix, 0, j, tvisit, matrix[0][j]);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dvisit[i][j] && tvisit[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }
    private void dfs(int[][] matrix, int x, int y, boolean[][] visit, int pre) {
        visit[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nextX = x + direction[i][0];
            int nextY = y + direction[i][1];
            if (inMatrix(matrix, nextX, nextY) && !visit[nextX][nextY] && matrix[nextX][nextY] >= pre) {
                dfs(matrix, nextX, nextY, visit, matrix[nextX][nextY]);
            }
        }
    }

    private boolean inMatrix(int[][] matrix, int x, int y) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
    }


//    private void dfs(int[][] matrix, int x, int y, boolean[][] visit, int pre) {
//        if (x < 0 || x >= matrix.length - 1 || y < 0 || y >= matrix[0].length - 1 || matrix[x][y] < pre) {
//            return;
//        }
//        visit[x][y] = true;
//        dfs(matrix, x + 1, y, visit, matrix[x][y]);
//        dfs(matrix, x - 1, y, visit, matrix[x][y]);
//        dfs(matrix, x, y + 1, visit, matrix[x][y]);
//        dfs(matrix, x, y - 1, visit, matrix[x][y]);
//    }

















        //错误答案
//    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
//        List<List<Integer>> ans = new ArrayList();
//        if (matrix == null) {
//            return ans;
//        }
//        int m = matrix.length, n = matrix[0].length;
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (dfsD(matrix, i, j, matrix[i][j]) && dfsT(matrix, i, j, matrix[i][j])) {
//                    List<Integer> item = new ArrayList();
//                    item.add(i);
//                    item.add(j);
//                    ans.add(item);
//                }
//            }
//        }
//        return ans;
//    }
//    private boolean dfsT(int[][] matrix, int x, int y, int pre) {
//        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length ) {
//            return true;
//        }
//        if (matrix[x][y] > pre) {
//            return false;
//        }
//        for (int i = 0; i < 2; i++) {
//            dfsT(matrix, x - 1, y, matrix[x][y]);
//            dfsT(matrix, x, y - 1, matrix[x][y]);
//        }
//        return false;
//    }
//    private boolean dfsD(int[][] matrix, int x, int y, int pre) {
//        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length ) {
//            return true;
//        }
//        if (matrix[x][y] > pre) {
//            return false;
//        }
//        for (int i = 0; i < 2; i++) {
//            dfsD(matrix, x + 1, y, matrix[x][y]);
//            dfsD(matrix, x, y + 1, matrix[x][y]);
//        }
//        return false;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
















