//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。 
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。 
//
// 此外，你可以假设该网格的四条边均被水包围。 
//
// 
//
// 示例 1： 
//
// 
//输入：grid = [
//  ["1","1","1","1","0"],
//  ["1","1","0","1","0"],
//  ["1","1","0","0","0"],
//  ["0","0","0","0","0"]
//]
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
//]
//输出：3
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 300 
// grid[i][j] 的值为 '0' 或 '1' 
// 
// Related Topics 深度优先搜索 广度优先搜索 并查集


package com.gpl.leetcode.leetcode.editor.cn;

import scala.collection.$colon$plus$;

//Java：岛屿数量
public class P200NumberOfIslands{
    public static void main(String[] args) {
        Solution solution = new P200NumberOfIslands().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int numIslands(char[][] grid) {
        if (grid == null || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    ans++;
                    dfs(grid, i, j);
                }
            }
        }
        return ans;
    }
    private void dfs(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0') {
            return;
        }
        grid[x][y] = '0';
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }


        //不改变原数组
//    public int numIslands(char[][] grid) {
//        if (grid == null || grid[0].length == 0) {
//            return 0;
//        }
//        int m = grid.length, n = grid[0].length;
//        int ans = 0;
//        boolean[][] used = new boolean[m][n];
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                if (!used[i][j] && grid[i][j] == '1') {
//                    dfs(grid, i, j, used);
//                    ans++;
//                }
//            }
//        }
//        return ans;
//    }
//    private void dfs(char[][] grid, int x, int y, boolean[][] used) {
//        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || used[x][y] || grid[x][y] == '0') {
//            return;
//        }
//        used[x][y] = true;
//        dfs(grid, x + 1, y, used);
//        dfs(grid, x - 1, y, used);
//        dfs(grid, x, y + 1, used);
//        dfs(grid, x, y - 1, used);
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}