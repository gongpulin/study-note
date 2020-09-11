//反转一个单链表。 
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表


package com.gpl.leetcode.leetcode.editor.cn;
//Java：反转链表
public class P206ReverseLinkedList{
    public static void main(String[] args) {
        Solution solution = new P206ReverseLinkedList().new Solution();
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
    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode third = null;
        while (cur != null) {
            third = cur.next;
            cur.next = pre;
            pre = cur;
            cur = third;
        }
        return pre;
    }

    /**
     * 递归，时间复杂度O(n),空间复杂度O(n)
     * @param head
     * @return
     */
//      public ListNode reverseList(ListNode head) {
//          if (head == null || head.next == null) {
//              return head;
//          }
//          ListNode  p = reverseList(head.next);
//          head.next.next = head;
//          head.next = null;
//          return p;
//      }
}
//leetcode submit region end(Prohibit modification and deletion)
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}
}