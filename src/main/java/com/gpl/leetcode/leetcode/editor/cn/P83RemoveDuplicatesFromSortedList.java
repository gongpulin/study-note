//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。 
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

//Java：删除排序链表中的重复元素
public class P83RemoveDuplicatesFromSortedList{
    public static void main(String[] args) {
        Solution solution = new P83RemoveDuplicatesFromSortedList().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while(cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;

//        Set<Integer> set = new HashSet<>();
//        ListNode cur = head;
//        set.add(cur.val);
//        while(cur.next != null) {
//            if (set.contains(cur.next.val)) {
//                cur.next = cur.next.next;
//            }
//            cur = cur.next;
//            set.add(cur.val);
//        }
//        return head;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
}