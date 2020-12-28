//给一非空的单词列表，返回前 k 个出现次数最多的单词。 
//
// 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。 
//
// 示例 1： 
//
// 
//输入: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//输出: ["i", "love"]
//解析: "i" 和 "love" 为出现次数最多的两个单词，均为2次。
//    注意，按字母顺序 "i" 在 "love" 之前。
// 
//
// 
//
// 示例 2： 
//
// 
//输入: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k
// = 4
//输出: ["the", "is", "sunny", "day"]
//解析: "the", "is", "sunny" 和 "day" 是出现次数最多的四个单词，
//    出现次数依次为 4, 3, 2 和 1 次。
// 
//
// 
//
// 注意： 
//
// 
// 假定 k 总为有效值， 1 ≤ k ≤ 集合元素数。 
// 输入的单词均由小写字母组成。 
// 
//
// 
//
// 扩展练习： 
//
// 
// 尝试以 O(n log k) 时间复杂度和 O(n) 空间复杂度解决。 
// 
// Related Topics 堆 字典树 哈希表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.*;

//Java：前K个高频单词
public class P692TopKFrequentWords{
    public static void main(String[] args) {
        Solution solution = new P692TopKFrequentWords().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {


    public List<String> topKFrequent1(String[] words, int k) {
        List<String> ans = new ArrayList();
        if (words == null || k <= 0) {
            return ans;
        }
        Map<String, Integer> countMap = new HashMap();
        for (String word : words) {
            if (word != null) {
                countMap.put(word, countMap.getOrDefault(word, 0) + 1);
            }
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
                (e1, e2) -> countMap.get(e1).equals(countMap.get(e2)) ? e2.compareTo(e1) : countMap.get(e1) - countMap.get(e2)
        );
        for (String word : countMap.keySet()) {
            heap.offer(word);
            if (heap.size() > k) {
                heap.poll();
            }
        }

        //为错误的做法
//        for (String word : heap) {
//            ans.add(heap.poll());
//        }
        while (!heap.isEmpty()) {
            ans.add(heap.poll());
        }
        Collections.reverse(ans);
        return ans;
    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> ans = new ArrayList();
        if (words == null || k <= 0) {
            return ans;
        }
        Map<String, Integer> countMap = new HashMap();
        for (String word : words) {
            if (word != null) {
                countMap.put(word, countMap.getOrDefault(word, 0) + 1);
            }
        }
        ans = new ArrayList(countMap.keySet());
        Collections.sort(ans,
                (e1, e2) -> countMap.get(e1).equals(countMap.get(e2)) ? e1.compareTo(e2) : countMap.get(e2) - countMap.get(e1)
        );
        return ans.subList(0, k);
    }


    //错误答案，类型转换错误，待解决
    public List<String> topKFrequent2(String[] words, int k) {
        List<String> ans = new ArrayList();
        if (words == null || k <= 0) {
            return ans;
        }
        Map<String, Integer> countMap = new HashMap();
        for (String word : words) {
            if (word != null) {
                countMap.put(word, countMap.getOrDefault(word, 0) + 1);
            }
        }
        PriorityQueue<Map.Entry<String, Integer>> heap = new PriorityQueue<Map.Entry<String, Integer>>();
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            if (heap.size() < k) {
                heap.offer(entry);
            } else if (heap.size() == k) {
                Map.Entry<String, Integer> item = heap.peek();
                if (entry.getValue().equals(item.getValue()) ? entry.getKey().compareTo(item.getKey()) > 0 : item.getValue() - entry.getValue() > 0) {
                    heap.poll();
                    heap.offer(item);
                }
//                if (entry.getValue() > item.getValue() || (entry.getValue().equals(item.getValue()) && (entry.getKey().compareTo(item.getKey()) < 0))) {
//
//                }
            }
        }
        while (!heap.isEmpty()) {
            ans.add(heap.poll().getKey());
        }
        Collections.reverse(ans);
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}