//给定一个二维网格和一个单词，找出该单词是否存在于网格中。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例: 
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false 
//
// 
//
// 提示： 
//
// 
// board 和 word 中只包含大写和小写英文字母。 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics 数组 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：单词搜索
public class P79WordSearch{
    public static void main(String[] args) {
        Solution solution = new P79WordSearch().new Solution();
        // TO TEST
        String word = "ABCCED";
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
//        String word = "aaa";
//        char[][] board = {{'a','a'}};
        System.out.println(solution.exist(board, word));

    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}}; //方向：左右上下
    public boolean exist(char[][] board, String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        boolean[][] used = new boolean[board.length][board[0].length];
        char firstChar = word.charAt(0);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(firstChar == board[i][j]) {
//                    System.out.println(board[i][j]);
                    if (backtrack(word, board, used, i, j, 0)) {
                        return true;
                    }

                }
            }
        }
        return false;
    }
    private boolean backtrack(String word, char[][] board, boolean[][] used, int i, int j, int start) {
        if (start == word.length()-1) {
            return word.charAt(start) == board[i][j];
        }
        if (word.charAt(start) == board[i][j]) {
            used[i][j] = true;
            for (int k = 0; k < 4; k++) {
                int x = i + direction[k][0];
                int y = j + direction[k][1];
                if (x >= board.length || x < 0 || y >= board[0].length || y < 0 || used[x][y]) {
                    continue;
                }
                if (backtrack(word, board, used, x, y, start+1)) {
                    return true;
                }
            }
            used[i][j] = false;
        }
        return false;
    }


        /**
         *
         *错误解答思路
         */

        //        if (!used[i][j] && i+1 < board.length) {
//            path.append(board[i][j]);
//            used[i][j] = true;
//            backtrack(ans, path, word, board, used, i+1, j, start+1);
//            path.deleteCharAt(path.length()-1);
//            used[i][j] = false;
//        }
//        if (!used[i][j] && i-1 >= 0) {
//            path.append(board[i][j]);
//            used[i][j] = true;
//            backtrack(ans, path, word, board, used, i-1, j, start+1);
//            path.deleteCharAt(path.length()-1);
//            used[i][j] = false;
//        }
//
//        if (!used[i][j] && j+1 < board[0].length) {
//            path.append(board[i][j]);
//            used[i][j] = true;
//            backtrack(ans, path, word, board, used, j+1, j, start+1);
//            path.deleteCharAt(path.length()-1);
//            used[i][j] = false;
//        }
//        if (!used[i][j] && j-1 >= 0) {
//            path.append(board[i][j]);
//            used[i][j] = true;
//            backtrack(ans, path, word, board, used, j-1, j, start+1);
//            path.deleteCharAt(path.length()-1);
//            used[i][j] = false;
//        }






}
//leetcode submit region end(Prohibit modification and deletion)

}