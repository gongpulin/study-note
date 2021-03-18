//给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words，找出所有同时在二维网格和字典中出现的单词。 
//
// 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使
//用。 
//
// 
//
// 示例 1： 
//[["o","a","a","n"],
// ["e","t","a","e"],
// ["i","h","k","r"],
// ["i","f","l","v"]]
// 
//输入：board = [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l"
//,"v"]], words = ["oath","pea","eat","rain"]
//输出：["eat","oath"]
// 
//
// 示例 2： 
//
// 
//输入：board = [["a","b"],["c","d"]], words = ["abcb"]
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// m == board.length 
// n == board[i].length 
// 1 <= m, n <= 12 
// board[i][j] 是一个小写英文字母 
// 1 <= words.length <= 3 * 104 
// 1 <= words[i].length <= 10 
// words[i] 由小写英文字母组成 
// words 中的所有字符串互不相同 
// 
// Related Topics 字典树 回溯算法


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Java：单词搜索 II
public class P212WordSearchIi{
    public static void main(String[] args) {
        Solution solution = new P212WordSearchIi().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

        class TrieNode {
            HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
            String word = null;
            public TrieNode() {}
        }


        char[][] _board = null;
        ArrayList<String> _result = new ArrayList<String>();

        public List<String> findWords(char[][] board, String[] words) {

            // Step 1). Construct the Trie
            TrieNode root = new TrieNode();
            for (String word : words) {
                TrieNode node = root;

                for (Character letter : word.toCharArray()) {
                    if (node.children.containsKey(letter)) {
                        node = node.children.get(letter);
                    } else {
                        TrieNode newNode = new TrieNode();
                        node.children.put(letter, newNode);
                        node = newNode;
                    }
                }
                node.word = word;  // store words in Trie
            }

            this._board = board;
            // Step 2). Backtracking starting for each cell in the board
            for (int row = 0; row < board.length; ++row) {
                for (int col = 0; col < board[row].length; ++col) {
                    if (root.children.containsKey(board[row][col])) {
                        backtracking(row, col, root);
                    }
                }
            }

            return this._result;
        }

        private void backtracking(int row, int col, TrieNode parent) {
            Character letter = this._board[row][col];
            TrieNode currNode = parent.children.get(letter);

            // check if there is any match
            if (currNode.word != null) {
                this._result.add(currNode.word);
                currNode.word = null;
            }

            // mark the current letter before the EXPLORATION
            this._board[row][col] = '#';

            // explore neighbor cells in around-clock directions: up, right, down, left
            int[] rowOffset = {-1, 0, 1, 0};
            int[] colOffset = {0, 1, 0, -1};
            for (int i = 0; i < 4; ++i) {
                int newRow = row + rowOffset[i];
                int newCol = col + colOffset[i];
                if (newRow < 0 || newRow >= this._board.length || newCol < 0
                        || newCol >= this._board[0].length) {
                    continue;
                }
                if (currNode.children.containsKey(this._board[newRow][newCol])) {
                    backtracking(newRow, newCol, currNode);
                }
            }

            // End of EXPLORATION, restore the original letter in the board.
            this._board[row][col] = letter;

            // Optimization: incrementally remove the leaf nodes
            if (currNode.children.isEmpty()) {
                parent.children.remove(letter);
            }
        }


//        class TrieNode {
//            TrieNode[] nexts;
//            public TrieNode() {
//                nexts = new TrieNode[26];
//            }
//        }
//        class TrieTree {
//            TrieNode root;
//            public TrieTree() {
//                root = new TrieNode();
//            }
//            public void add(String word) {
//                if (word == null) {
//                    return;
//                }
//                TrieNode node = root;
//                char[] chars = word.toCharArray();
//                int index = 0;
//                for (char c : chars) {
//                    index = c - 'a';
//                    if (node.nexts[index] == null) {
//                        node.nexts[index] = new TrieNode();
//                    }
//                    node = node.nexts[index];
//                }
//            }
//        }
//    public List<String> findWords(char[][] board, String[] words) {
//        List<String> ans = new ArrayList();
//        if (board == null || board[0].length == 0) {
//            return ans;
//        }
//        int[][] direction = {{1,0},{0,1},{-1,0},{0,-1}};
//        int m = board.length, n = board[0].length;
//        for (String word : words) {
//            if (word == null || word.length() == 0) {
//                continue;
//            }
//            char c = word.charAt(0);
//            for (int i = 0; i < m; i++) {
//                for (int j = 0; j < n; j++) {
//                    if (c == board[i][j]) {
//                        boolean[][] used = new boolean[m][n];
//                        if (backtrack(used, word, 1, board, i, j, direction)) {
//                            ans.add(word);
//                        }
//                    }
//                }
//            }
//        }
//        return ans;
//    }
//
//    public boolean backtrack(boolean[][] userd, String word, int index, char[][] board, int i, int j, int[][] direction) {
//            if (index == word.length()) {
//                return true;
//            }
//            int m = board.length, n = board[0].length;
//            for (int k = 0; k < 4; k++) {
//                int nexti = i + direction[k][0];
//                int nextj = j + direction[k][1];
//                if (nexti >= 0 && nexti < m && nextj >= 0 && nextj < n) {
//                    if (board[nexti][nextj] == word.charAt(index) && backtrack(userd, word, index++, board, nexti, nextj, direction)) {
//                        return true;
//                    }
//                }
//            }
//            return false;
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}