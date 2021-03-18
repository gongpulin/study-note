//给定字符串 S 和单词字典 words, 求 words[i] 中是 S 的子序列的单词个数。 
//
// 
//示例:
//输入: 
//S = "abcde"
//words = ["a", "bb", "acd", "ace"]
//输出: 3
//解释: 有三个是 S 的子序列的单词: "a", "acd", "ace"。
// 
//
// 注意: 
//
// 
// 所有在words和 S 里的单词都只由小写字母组成。 
// S 的长度在 [1, 50000]。 
// words 的长度在 [1, 5000]。 
// words[i]的长度在[1, 50]。 
// 
// Related Topics 数组


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：匹配子序列的单词数
public class P792NumberOfMatchingSubsequences{
    public static void main(String[] args) {
        Solution solution = new P792NumberOfMatchingSubsequences().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int numMatchingSubseq(String S, String[] words) {
        if (words == null) {
            return 0;
        }

        int ans = 0;
        ArrayList<Node>[] heads = new ArrayList[26];
        for (int i = 0; i < 26; ++i) {
            heads[i] = new ArrayList<Node>();
        }
        for (String word: words) {
            heads[word.charAt(0) - 'a'].add(new Node(word, 0));
        }
        for (char c: S.toCharArray()) {
            ArrayList<Node> old_bucket = heads[c - 'a'];
            heads[c - 'a'] = new ArrayList<Node>();

            for (Node node: old_bucket) {
                node.index++;

                if (node.index == node.word.length()) {
                    ans++;
                } else {
                    heads[node.word.charAt(node.index) - 'a'].add(node);
                }
            }
            old_bucket.clear();
        }
        return ans;

    }




        //超时了
    public int numMatchingSubseq1(String S, String[] words) {
        if (words == null) {
            return 0;
        }
        int ans = 0;
        for (String word : words) {
            if (isSubSequence(S, word)) {
                ans++;
            }
        }
        return ans;
    }
    private boolean isSubSequence(String s, String word) {
        int i = 0, j = 0;
        while (i < s.length() && j < word.length()) {
            if (s.charAt(i) == word.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == word.length();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Node {
        String word;
        int index;
        public Node(String word, int index) {
            word = word;
            index = index;
        }
}
}