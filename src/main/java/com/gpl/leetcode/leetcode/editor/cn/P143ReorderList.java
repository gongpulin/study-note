//给定一个单链表 L：L0→L1→…→Ln-1→Ln ， 
//将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→… 
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
// 示例 1: 
//
// 给定链表 1->2->3->4, 重新排列为 1->4->2->3. 
//
// 示例 2: 
//
// 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3. 
// Related Topics 链表


package com.gpl.leetcode.leetcode.editor.cn;
//Java：重排链表
public class P143ReorderList{
    public static void main(String[] args) {
        Solution solution = new P143ReorderList().new Solution();
        // TO TEST
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        solution.reorderList(head);
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHead = reverseList(slow.next);
        slow.next = null; //避免死循环


//        while(secondHead != null) {
//            ListNode temp =  secondHead.next;
//            secondHead.next = head.next;
//            head.next = secondHead;
//            head = secondHead.next;
//            secondHead = temp;
//        }
//        return;

        ListNode cur = head;
        ListNode p1 = head.next;
        ListNode p2 = secondHead;
        while(p1 != null || p2 != null) {
            if (p2 != null) {
                cur.next = p2;
                cur = cur.next;
                p2 = p2.next;
            }
            if (p1 != null) {
                cur.next = p1;
                cur = cur.next;
                p1 = p1.next;
            }
        }
        return;
    }

    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode third = null;
        while(cur != null) {
            third = cur.next;
            cur.next = pre;
            pre = cur;
            cur = third;
        }
        return pre;
    }


}
//leetcode submit region end(Prohibit modification and deletion)
static class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
}