//在英语中，我们有一个叫做 词根(root)的概念，它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。例如，词根an，
//跟随着单词 other(其他)，可以形成新的单词 another(另一个)。 
//
// 现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。 
//
// 你需要输出替换之后的句子。 
//
// 
//
// 示例 1： 
//
// 输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by th
//e battery"
//输出："the cat was rat by the bat"
// 
//
// 示例 2： 
//
// 输入：dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
//输出："a a b c"
// 
//
// 示例 3： 
//
// 输入：dictionary = ["a", "aa", "aaa", "aaaa"], sentence = "a aa a aaaa aaa aaa a
//aa aaaaaa bbb baba ababa"
//输出："a a a a a a a a bbb baba a"
// 
//
// 示例 4： 
//
// 输入：dictionary = ["catt","cat","bat","rat"], sentence = "the cattle was rattle
//d by the battery"
//输出："the cat was rat by the bat"
// 
//
// 示例 5： 
//
// 输入：dictionary = ["ac","ab"], sentence = "it is abnormal that this solution is
// accepted"
//输出："it is ab that this solution is ac"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= dictionary.length <= 1000 
// 1 <= dictionary[i].length <= 100 
// dictionary[i] 仅由小写字母组成。 
// 1 <= sentence.length <= 10^6 
// sentence 仅由小写字母和空格组成。 
// sentence 中单词的总量在范围 [1, 1000] 内。 
// sentence 中每个单词的长度在范围 [1, 1000] 内。 
// sentence 中单词之间由一个空格隔开。 
// sentence 没有前导或尾随空格。 
// 
// Related Topics 字典树 哈希表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Java：单词替换
public class P648ReplaceWords{
    public static void main(String[] args) {
        Solution solution = new P648ReplaceWords().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    class TrieNode {
        boolean isEnd;
        Map<Character, TrieNode> nexts;
        public TrieNode() {
            isEnd = false;
            nexts = new HashMap();
        }
    }
    class TrieTree{
        TrieNode root;
        public TrieTree() {
            root = new TrieNode();
        }
        public void add(String word) {
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
        public String prefix(String word) {
            if (word == null) {
                return "";
            }
            TrieNode node = root;
            char[] chars = word.toCharArray();
            int index = 0;
            for (char c : chars) {
                if (node.nexts.containsKey(c)) {
                    node = node.nexts.get(c);
                    index++;
                    if (node.isEnd) {
                        return word.substring(0,index);
                    }
                } else {
                    return word;
                }
            }
            return word;
        }
    }
    public String replaceWords(List<String> dictionary, String sentence) {
        StringBuilder ans = new StringBuilder();
        TrieTree tree = new TrieTree();
        for (String word : dictionary) {
            tree.add(word);
        }
        String[] words = sentence.split("\\s+");
        for (String word : words) {
            if (ans.length() > 0) {
                ans.append(" ");
            }
            ans.append(tree.prefix(word));
        }
        return ans.toString();
    }




//class TrieNode {
//    TrieNode[] children;
//    String word;
//    TrieNode() {
//        children = new TrieNode[26];
//    }
//}
//    public String replaceWords(List<String> roots, String sentence) {
//        TrieNode trie = new TrieNode();
//        for (String root: roots) {
//            TrieNode cur = trie;
//            for (char letter: root.toCharArray()) {
//                if (cur.children[letter - 'a'] == null) {
//                    cur.children[letter - 'a'] = new TrieNode();
//                }
//                cur = cur.children[letter - 'a'];
//            }
//            cur.word = root;
//        }
//
//        StringBuilder ans = new StringBuilder();
//
//        for (String word: sentence.split("\\s+")) {
//            if (ans.length() > 0) {
//                ans.append(" ");
//            }
//            TrieNode cur = trie;
//            for (char letter: word.toCharArray()) {
//                if (cur.children[letter - 'a'] == null || cur.word != null) {
//                    break;
//                }
//                cur = cur.children[letter - 'a'];
//            }
//            ans.append(cur.word != null ? cur.word : word);
//        }
//        return ans.toString();
//    }
}
//leetcode submit region end(Prohibit modification and deletion)

}