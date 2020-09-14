//给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。 
//
// 示例 1: 
//
// 输入: 1->2->3->3->4->4->5
//输出: 1->2->5
// 
//
// 示例 2: 
//
// 输入: 1->1->1->2->3
//输出: 2->3 
// Related Topics 链表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

//Java：删除排序链表中的重复元素 II
public class P82RemoveDuplicatesFromSortedListIi{
    public static void main(String[] args) {
        Solution solution = new P82RemoveDuplicatesFromSortedListIi().new Solution();
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
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while (head != null && head.next != null) {
            if (head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {  //head.next != null 这个条件不加会出现NullPointerException
                    head = head.next;
                }
                head = head.next;
                pre.next = head;
            } else {
                head = head.next;
                pre = pre.next;
            }

        }
        return dummy.next;



//        Set<Integer> set = new HashSet<>();
//        ListNode cur = head;
//        while (cur != null) {
//            set.add(cur.val);
//            cur = cur.next;
//        }
//        ListNode dummy = new ListNode(-1);
//        dummy.next = head;
//        cur = dummy;
//        while (cur != null && cur.next != null) {
//            if (set.contains(cur.next.val)) {
//                cur.next = cur.next.next;
//            } else {
//                cur = cur.next;
//            }
//        }
//        return dummy.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
}