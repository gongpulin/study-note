//给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。 
//
// 你可以假设除了数字 0 之外，这两个数字都不会以零开头。 
//
// 
//
// 进阶： 
//
// 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。 
//
// 
//
// 示例： 
//
// 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 8 -> 0 -> 7
// 
// Related Topics 链表


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.Stack;

//Java：两数相加 II
public class P445AddTwoNumbersIi{
    public static void main(String[] args) {
        Solution solution = new P445AddTwoNumbersIi().new Solution();
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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode reverseL1Head = reverseList(l1);
        ListNode reverseL2Head = reverseList(l2);
        int carry = 0;
        ListNode p = reverseL1Head;
        ListNode q = reverseL2Head;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (p != null || q != null) {
            int x = p != null ? p.val : 0;
            int y = q != null ? q.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            ListNode node = new ListNode(sum % 10);
            cur.next = node;
            cur = cur.next;
            p = p != null ? p.next : null;
            q = q != null ? q.next : null;
        }
        if (carry != 0) {
            ListNode n = new ListNode(carry);
            cur.next = n;
        }
        ListNode newHead = reverseList(dummy.next);
        return newHead;
    }
    private ListNode reverseList(ListNode head) {
        ListNode thrid = null;
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            thrid = cur.next;
            cur.next = pre;
            pre = cur;
            cur = thrid;
        }
        return pre;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2){
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        ListNode head = null;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int x = stack1.isEmpty() ? 0 : stack1.pop();
            int y = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = x + y + carry;
            ListNode node = new ListNode(sum % 10);
            carry = sum / 10;
            node.next = head;
            head = node;
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
}