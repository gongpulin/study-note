//给出一个字符串数组words组成的一本英语词典。从中找出最长的一个单词，该单词是由words词典中其他单词逐步添加一个字母组成。若其中有多个可行的答案，则返
//回答案中字典序最小的单词。 
//
// 若无答案，则返回空字符串。 
//
// 
//
// 示例 1： 
//
// 输入：
//words = ["w","wo","wor","worl", "world"]
//输出："world"
//解释： 
//单词"world"可由"w", "wo", "wor", 和 "worl"添加一个字母组成。
// 
//
// 示例 2： 
//
// 输入：
//words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
//输出："apple"
//解释：
//"apply"和"apple"都能由词典中的单词组成。但是"apple"的字典序小于"apply"。
// 
//
// 
//
// 提示： 
//
// 
// 所有输入的字符串都只包含小写字母。 
// words数组长度范围为[1,1000]。 
// words[i]的长度范围为[1,30]。 
// 
// Related Topics 字典树 哈希表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

//Java：词典中最长的单词
public class P720LongestWordInDictionary{
    public static void main(String[] args) {
        Solution solution = new P720LongestWordInDictionary().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class TrieNode {
        TrieNode[] nexts;
        int idx;  //该word在words数组中的下标，方便定位该word
        public TrieNode() {
            nexts = new TrieNode[26];
            idx = 0;
        }
    }

    class TrieTree{
        TrieNode root;
        String[] words;
        public TrieTree() {
            root = new TrieNode();
        }
        public void add(String word, int idx) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (char c : chars) {
                index = c - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new TrieNode();
                }
                node = node.nexts[index];
            }
            node.idx = idx;
        }

        public String dfs() {
            String ans = "";
            Stack<TrieNode> stack = new Stack();
            stack.push(root);
            while (!stack.empty()) {
                TrieNode node = stack.pop();
                if (node.idx > 0 || node == root) {
                    if (node != root) {
                        String word = words[node.idx - 1];
                        if (word.length() > ans.length() ||
                                (word.length() == ans.length() && word.compareTo(ans) < 0)) {
                            ans = word;
                        }
                    }
                    for (int i = 0; i < 26; i++) {
                        if (node.nexts[i] != null) {
                            stack.push(node.nexts[i]);
                        }
                    }
                }
            }
            return ans;
        }


    }

    public String longestWord(String[] words) {
        TrieTree tree = new TrieTree();
        tree.words = words;
        int idx = 0;
        for (String word : words) {
            tree.add(word, ++idx);
        }
        return tree.dfs();
    }







    public String longestWord1(String[] words) {
        Trie trie = new Trie();
        int index = 0;
        for (String word: words) {
            trie.insert(word, ++index); //indexed by 1
        }
        trie.words = words;
        return trie.dfs();
    }

    public String longestWord2(String[] words) {
        HashSet<String> set = new HashSet<>();
        for (String word : words) {
            set.add(word);
        }
        String ans = "";
        for (String word : words) {
            int len = word.length();
            String str = "";
            for (int i = 0; i < len; i++) {
                str = str + word.charAt(i);
                if (!set.contains(str)) {
                    break;
                }
            }
            if (str.length() == word.length()) {
                if (word.length() > ans.length()) {
                    ans = word;
                } else if (word.length() == ans.length()) {
                    if (word.compareTo(ans) < 0) {
                        ans = word;
                    }
                }
            }
        }
        return ans;
    }
}
    class Node {
        char c;
        HashMap<Character, Node> children = new HashMap();
        int end;
        public Node(char c){
            this.c = c;
        }
    }

    class Trie {
        Node root;
        String[] words;
        public Trie() {
            root = new Node('0');
        }

        public void insert(String word, int index) {
            Node cur = root;
            for (char c: word.toCharArray()) {
                cur.children.putIfAbsent(c, new Node(c));
                cur = cur.children.get(c);
            }
            cur.end = index;
        }

        public String dfs() {
            String ans = "";
            Stack<Node> stack = new Stack();
            stack.push(root);
            while (!stack.empty()) {
                Node node = stack.pop();
                if (node.end > 0 || node == root) {
                    if (node != root) {
                        String word = words[node.end - 1];
                        if (word.length() > ans.length() ||
                                word.length() == ans.length() && word.compareTo(ans) < 0) {
                            ans = word;
                        }
                    }
                    for (Node nei: node.children.values()) {
                        stack.push(nei);
                    }
                }
            }
            return ans;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}