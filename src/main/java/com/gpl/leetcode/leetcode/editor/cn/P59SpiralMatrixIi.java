//给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。 
//
// 示例: 
//
// 输入: 3
//输出:
//[
// [ 1, 2, 3 ],
// [ 8, 9, 4 ],
// [ 7, 6, 5 ]
//] 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;
//Java：螺旋矩阵 II
public class P59SpiralMatrixIi{
    public static void main(String[] args) {
        Solution solution = new P59SpiralMatrixIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int[][] direction = {{0,1},{1,0},{0,-1},{-1,0}};
        int row = 0, col = 0, dIndex = 0;
        for (int i = 1; i <= n * n; i++) {
            ans[row][col] = i;
            int newRow = row + direction[dIndex][0];
            int newCol = col + direction[dIndex][1];
            if (newRow < 0 || newRow >= n || newCol < 0 || newCol >= n || ans[newRow][newCol] != 0) {
                dIndex = (dIndex + 1) % 4;
            }
            row = row + direction[dIndex][0];
            col = col + direction[dIndex][1];
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}