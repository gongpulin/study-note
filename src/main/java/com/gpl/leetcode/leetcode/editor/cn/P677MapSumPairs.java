//实现一个 MapSum 类，支持两个方法，insert 和 sum： 
//
// 
// MapSum() 初始化 MapSum 对象 
// void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 ke
//y 已经存在，那么原来的键值对将被替代成新的键值对。 
// int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MapSum", "insert", "sum", "insert", "sum"]
//[[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
//输出：
//[null, null, 3, null, 5]
//
//解释：
//MapSum mapSum = new MapSum();
//mapSum.insert("apple", 3);  
//mapSum.sum("ap");           // return 3 (apple = 3)
//mapSum.insert("app", 2);    
//mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
// 
//
// 
//
// 提示： 
//
// 
// 1 <= key.length, prefix.length <= 50 
// key 和 prefix 仅由小写英文字母组成 
// 1 <= val <= 1000 
// 最多调用 50 次 insert 和 sum 
// 
// Related Topics 字典树


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;

//Java：键值映射
public class P677MapSumPairs{
    public static void main(String[] args) {
        MapSum solution = new P677MapSumPairs().new MapSum();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class MapSum {
    class TrieNode {
        int val;
        TrieNode[] nexts;
        public TrieNode() {
            nexts = new TrieNode[26];
        }
    }
    TrieNode root;
    HashMap<String, Integer> map;

    /** Initialize your data structure here. */
    public MapSum() {
        root = new TrieNode();
        map = new HashMap();
    }
    
    public void insert(String key, int val) {
        if (key == null) {
            return;
        }
        int newVal = val;
        if (map.containsKey(key)) {
            int oldVal = map.get(key);
            val = newVal - oldVal;
        }
        map.put(key, newVal);
        TrieNode node = root;
        char[] chars = key.toCharArray();
        int index = 0;
        for (char c : chars) {
            index = c - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new TrieNode();
            }
            node = node.nexts[index];
            node.val += val;
        }
    }
    
    public int sum(String prefix) {
        if (prefix == null) {
            return 0;
        }
        TrieNode node = root;
        char[] chars = prefix.toCharArray();
        int index = 0;
        for (char c : chars) {
            index = c - 'a';
            if (node.nexts[index] == null) {
                return 0;
            }
            node = node.nexts[index];
        }
        return node.val;
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
//leetcode submit region end(Prohibit modification and deletion)

}