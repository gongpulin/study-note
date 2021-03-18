//判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 
//
// 上图是一个部分填充的有效的数独。 
//
// 数独部分空格内已填入了数字，空白格用 '.' 表示。 
//
// 示例 1: 
//
// 输入:
//[
//  ["5","3",".",".","7",".",".",".","."],
//  ["6",".",".","1","9","5",".",".","."],
//  [".","9","8",".",".",".",".","6","."],
//  ["8",".",".",".","6",".",".",".","3"],
//  ["4",".",".","8",".","3",".",".","1"],
//  ["7",".",".",".","2",".",".",".","6"],
//  [".","6",".",".",".",".","2","8","."],
//  [".",".",".","4","1","9",".",".","5"],
//  [".",".",".",".","8",".",".","7","9"]
//]
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//[
//  ["8","3",".",".","7",".",".",".","."],
//  ["6",".",".","1","9","5",".",".","."],
//  [".","9","8",".",".",".",".","6","."],
//  ["8",".",".",".","6",".",".",".","3"],
//  ["4",".",".","8",".","3",".",".","1"],
//  ["7",".",".",".","2",".",".",".","6"],
//  [".","6",".",".",".",".","2","8","."],
//  [".",".",".","4","1","9",".",".","5"],
//  [".",".",".",".","8",".",".","7","9"]
//]
//输出: false
//解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
//     但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。 
//
// 说明: 
//
// 
// 一个有效的数独（部分已被填充）不一定是可解的。 
// 只需要根据以上规则，验证已经填入的数字是否有效即可。 
// 给定数独序列只包含数字 1-9 和字符 '.' 。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Java：有效的数独
public class P36ValidSudoku{
    public static void main(String[] args) {
        Solution solution = new P36ValidSudoku().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        public boolean isValidSudoku(char[][] board) {
            int[][] rows = new int[9][9];
            int[][] cloumns = new int[9][9];
            int[][] boxs = new int[9][9];

            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    char c = board[i][j];
                    if (c != '.') {
                        int n = c - '1';
                        int boxIndex = (i / 3) * 3 + j / 3;//注意这个用法
                        int rowCount = ++rows[i][n];
                        int columnCount = ++cloumns[j][n];
                        int boxCount = ++boxs[boxIndex][n];
                        if (rowCount > 1 || columnCount > 1 || boxCount > 1) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

//        public boolean isValidSudoku(char[][] board) {
//            Map<Integer, Integer>[] rows = new HashMap[9];
//            Map<Integer, Integer>[] cloumns = new HashMap[9];
//            Map<Integer, Integer>[] boxs = new HashMap[9];
//            for (int i = 0; i < 9; i++) {
//                rows[i] = new HashMap<>();
//                cloumns[i] = new HashMap<>();
//                boxs[i] = new HashMap<>();
//            }
//            for (int i = 0; i < 9; i++) {
//                for (int j = 0; j < 9; j++) {
//                    if (board[i][j] != '.') {
//                        int n = board[i][j] - '0';
//                        int box_index = (i / 3) * 3 + j / 3;
//                        rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
//                        cloumns[j].put(n, cloumns[j].getOrDefault(n, 0) + 1);
//                        boxs[box_index].put(n, boxs[box_index].getOrDefault(n, 0) + 1);
//                        if (rows[i].get(n) > 1 || cloumns[j].get(n) > 1 || boxs[box_index].get(n) > 1) {
//                            return false;
//                        }
//                    }
//                }
//            }
//            return true;
//        }


        //没有判断3X3,错误答案
//    public boolean isValidSudoku(char[][] board) {
//        int[] count = new int[9];
//        boolean ans = true;
//        for (int i = 0; i < board.length && ans; i++) {
//            for (int j = 0; j < board[0].length && ans; j++) {
//                if (board[i][j] != '.') {
//                    ans = checkIsValid(board, count, i, j);
//                }
//            }
//        }
//        return ans;
//    }
//    private boolean checkIsValid(char[][] board, int[] count, int i, int j) {
//        Arrays.fill(count, 0);
//        for (int x = 0; x < board.length; x++) {
//            if (board[x][j] != '.') {
//                count[board[x][j] - '1']++;
//                if (count[board[x][j] - '1'] > 1) {
//                    return false;
//                }
//            }
//        }
//        Arrays.fill(count, 0);
//        for (int x = 0; x < board[0].length; x++) {
//            if (board[i][x] != '.') {
//                count[board[i][x] - '1']++;
//                if (count[board[i][x] - '1'] > 1) {
//                    return false;
//                }
//            }
//        }
//
//        return true;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}