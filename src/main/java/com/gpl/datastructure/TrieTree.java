package com.gpl.datastructure;

/**
 * @author gongpulin
 * date 2020-12-16
 * 前缀树
 */
public class TrieTree {
    public TrieNode root;
    public TrieTree() {
        root = new TrieNode();
    }
    public void add(String word) {
        if (word == null) {
            return;
        }
        char[] chars = word.toCharArray();
        int index = 0;
        TrieNode node = root;
        for (char c : chars) {
            index = c - 'a';
            if (node.nexts[index] != null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.path++;
        }
        node.end++;
    }

    public int get(String word) {
        if (word == null) {
            return 0;
        }
        TrieNode node = root;
        int index = 0;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            index = c - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

}


class TrieNode {
    public int path;            //路过节点的次数
    public int end;             //以该节点为结束的节点个数,  若不统计次数，此字段可为boolean
    public TrieNode[] nexts;    //下一个节点

    public TrieNode() {
        this.path = 0;
        this.end = 0;
        nexts = new TrieNode[26];
    }
}
