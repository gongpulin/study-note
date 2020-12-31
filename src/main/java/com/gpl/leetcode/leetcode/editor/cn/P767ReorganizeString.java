//给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。 
//
// 若可行，输出任意可行的结果。若不可行，返回空字符串。 
//
// 示例 1: 
//
// 
//输入: S = "aab"
//输出: "aba"
// 
//
// 示例 2: 
//
// 
//输入: S = "aaab"
//输出: ""
// 
//
// 注意: 
//
// 
// S 只包含小写字母并且长度在[1, 500]区间内。 
// 
// Related Topics 堆 贪心算法 排序 字符串


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Comparator;
import java.util.PriorityQueue;

//Java：重构字符串
public class P767ReorganizeString{
    public static void main(String[] args) {
        Solution solution = new P767ReorganizeString().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reorganizeString(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }
        int len = S.length();
        if (len < 2) {
            return S;
        }
        int[] counts = new int[26];

        int maxCount = 0;
        for (int i = 0; i < len; i++) {
            int idx = S.charAt(i) - 'a';
            counts[idx]++;
            maxCount = Math.max(maxCount, counts[idx]);
        }
        if (maxCount > (len + 1) / 2) {
            return "";
        }
        PriorityQueue<Character> heap = new PriorityQueue<Character>(
                (e1, e2) -> counts[e2 - 'a'] - counts[e1 - 'a']
        );
//        PriorityQueue<Character> heap = new PriorityQueue(new Comparator<Character>(){
//            @Override
//            public int compare(Character o1, Character o2) {
//                return counts[o2 - 'a'] - counts[o1 - 'a'];
//            }
//        });
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                heap.offer(c);
            }
        }
        StringBuilder ans = new StringBuilder();
        while (heap.size() > 1) {
            char c1 = heap.poll();
            char c2 = heap.poll();
            ans.append(c1);
            ans.append(c2);
            counts[c1 - 'a']--;
            counts[c2 - 'a']--;
            if (counts[c1 - 'a'] > 0) {
                heap.offer(c1);
            }
            if (counts[c2 - 'a'] > 0) {
                heap.offer(c2);
            }
        }
        if (heap.size() > 0) {
            ans.append(heap.poll());
        }
        return ans.toString();
    }

    public String reorganizeString1(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }
        if (S.length() < 2) {
            return S;
        }
        int[] counts = new int[26];
        int maxCount = 0;
        int length = S.length();
        for (int i = 0; i < length; i++) {
            char c = S.charAt(i);
            counts[c - 'a']++;
            maxCount = Math.max(maxCount, counts[c - 'a']);
        }
        if (maxCount > (length + 1) / 2) {
            return "";
        }
        PriorityQueue<Character> queue = new PriorityQueue<Character>(new Comparator<Character>() {
            @Override
            public int compare(Character letter1, Character letter2) {
                return counts[letter2 - 'a'] - counts[letter1 - 'a'];
            }
        });
        for (char c = 'a'; c <= 'z'; c++) {
            if (counts[c - 'a'] > 0) {
                queue.offer(c);
            }
        }
        StringBuffer sb = new StringBuffer();
        while (queue.size() > 1) {
            char letter1 = queue.poll();
            char letter2 = queue.poll();
            sb.append(letter1);
            sb.append(letter2);
            int index1 = letter1 - 'a', index2 = letter2 - 'a';
            counts[index1]--;
            counts[index2]--;
            if (counts[index1] > 0) {
                queue.offer(letter1);
            }
            if (counts[index2] > 0) {
                queue.offer(letter2);
            }
        }
        if (queue.size() > 0) {
            sb.append(queue.poll());
        }
        return sb.toString();

    }
}
//leetcode submit region end(Prohibit modification and deletion)

}