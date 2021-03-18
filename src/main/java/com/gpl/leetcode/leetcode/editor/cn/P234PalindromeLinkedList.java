//请判断一个链表是否为回文链表。 
//
// 示例 1: 
//
// 输入: 1->2
//输出: false 
//
// 示例 2: 
//
// 输入: 1->2->2->1
//输出: true
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 链表 双指针


package com.gpl.leetcode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

//Java：回文链表
public class P234PalindromeLinkedList{
    public static void main(String[] args) {
        Solution solution = new P234PalindromeLinkedList().new Solution();
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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        //反转后半部分链表
        ListNode secondHalfHead = reverseList(slow.next);
        ListNode p2 = secondHalfHead;
        ListNode p1 = head;
        boolean result = true;
        while(p2 != null && p1 != null && result) {
            if (p2.val != p1.val) {
                result = false;
            }
            p2 = p2.next;
            p1 = p1.next;
        }
        slow.next = reverseList(secondHalfHead);
        return result;

    }
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode third = null;
        ListNode cur = head;
        while(cur != null) {
            third = cur.next;
            cur.next = pre;
            pre = cur;
            cur = third;
        }
        return pre;
    }


//      public boolean isPalindrome(ListNode head) {
//          if (head == null || head.next == null) {
//              return true;
//          }
//
//          ArrayList<Integer> list = new ArrayList<>();
//          ListNode cur = head;
//          while (cur != null) {
//              list.add(cur.val);
//              cur = cur.next;
//          }
//          int i = 0;
//          int j = list.size()-1;
//          while (i <= j) {
//              if (!list.get(i).equals(list.get(j))) {
//                  return false;
//              }
//              i++;
//              j--;
//          }
//          return true;
//      }
}
//leetcode submit region end(Prohibit modification and deletion)
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
}