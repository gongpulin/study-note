//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。 
//
// 示例: 
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");   
//trie.search("app");     // 返回 true 
//
// 说明: 
//
// 
// 你可以假设所有的输入都是由小写字母 a-z 构成的。 
// 保证所有输入均为非空字符串。 
// 
// Related Topics 设计 字典树


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

//Java：实现 Trie (前缀树)
public class P208ImplementTriePrefixTree{
    public static void main(String[] args) {
        Trie solution = new P208ImplementTriePrefixTree().new Trie();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Trie {
    class TrieNode {
        boolean isEnd;
        Map<Character, TrieNode> nexts;
        public TrieNode() {
            isEnd = false;
            nexts = new HashMap<>();
        }
    }
    TrieNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null) {
            return;
        }
        TrieNode node = root;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            node.nexts.putIfAbsent(c, new TrieNode());
            node = node.nexts.get(c);
        }
        node.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        TrieNode node = root;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (!node.nexts.containsKey(c)) {
                return false;
            }
            node = node.nexts.get(c);
        }
        return node.isEnd == true;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null) {
            return false;
        }
        TrieNode node = root;
        char[] chars = prefix.toCharArray();
        for (char c : chars) {
            if (!node.nexts.containsKey(c)) {
                return false;
            }
            node = node.nexts.get(c);
        }
        return true;
    }


//    class TrieNode {
//        boolean isEnd;
//        TrieNode[] nexts;
//        public TrieNode() {
//            isEnd = false;
//            nexts = new TrieNode[26];
//        }
//    }
//    TrieNode root;
//    /** Initialize your data structure here. */
//    public Trie() {
//        root = new TrieNode();
//    }
//
//    /** Inserts a word into the trie. */
//    public void insert(String word) {
//        if (word == null) {
//            return;
//        }
//        TrieNode node = root;
//        char[] chars = word.toCharArray();
//        int index = 0;
//        for (char c : chars) {
//            index = c - 'a';
//            if (node.nexts[index] == null) {
//                node.nexts[index] = new TrieNode();
//            }
//            node = node.nexts[index];
//        }
//        node.isEnd = true;
//    }
//
//    /** Returns if the word is in the trie. */
//    public boolean search(String word) {
//        if (word == null) {
//            return false;
//        }
//        char[] chars = word.toCharArray();
//        int index = 0;
//        TrieNode node = root;
//        for (char c : chars) {
//            index = c - 'a';
//            if (node.nexts[index] == null) {
//                return false;
//            }
//            node = node.nexts[index];
//        }
//        return node.isEnd == true;
//    }
//
//    /** Returns if there is any word in the trie that starts with the given prefix. */
//    public boolean startsWith(String prefix) {
//        if (prefix == null) {
//            return false;
//        }
//        char[] chars = prefix.toCharArray();
//        int index = 0;
//        TrieNode node = root;
//        for (char c : chars) {
//            index = c - 'a';
//            if (node.nexts[index] == null) {
//                return false;
//            }
//            node = node.nexts[index];
//        }
//        return true;
//    }
}


/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}