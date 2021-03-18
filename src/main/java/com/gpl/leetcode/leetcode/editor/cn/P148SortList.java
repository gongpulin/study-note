//在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。 
//
// 示例 1: 
//
// 输入: 4->2->1->3
//输出: 1->2->3->4
// 
//
// 示例 2: 
//
// 输入: -1->5->3->4->0
//输出: -1->0->3->4->5 
// Related Topics 排序 链表


package com.gpl.leetcode.leetcode.editor.cn;
//Java：排序链表
public class P148SortList{
    public static void main(String[] args) {
        Solution solution = new P148SortList().new Solution();
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
    /**
     *
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode rightHead = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(rightHead);
        return merge(left, right);
    }
    private ListNode merge(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(-1);
        ListNode p = dummy;
        while (h1 != null && h2 != null) {
            if (h1.val > h2.val) {
                p.next = h2;
                h2 = h2.next;
            } else {
                p.next = h1;
                h1 = h1.next;
            }
            p = p.next;
        }
        if (h1 != null) {
            p.next = h1;
        } else {
            p.next = h2;
        }
        return dummy.next;
    }







//    public ListNode sortList(ListNode head) {
//        if(head==null || head.next==null) {
//            return head;
//        }
//        ListNode slow = head; //慢指针
//        ListNode fast = head.next; //快指针
//
//        while(fast!=null && fast.next!=null){ //快慢指针找到链表中点
//            slow = slow.next; //慢指针走一步
//            fast = fast.next.next; //快指针走两步
//        }
//        ListNode rightHead = slow.next; //链表第二部分的头节点
//        slow.next = null; //cut 链表
//
//        ListNode left = sortList(head); //递归排序前一段链表
//        ListNode right = sortList(rightHead); //递归排序后一段链表
//        return merge(left,right);
//    }
//    public ListNode merge(ListNode h1,ListNode h2){ //合并两个有序链表
//        ListNode dummy = new ListNode(-1);
//        ListNode p = dummy;
//        while(h1!=null && h2!=null){
//            if(h1.val < h2.val){
//                p.next = h1;
//                h1 = h1.next;
//            }else{
//                p.next = h2;
//                h2 = h2.next;
//            }
//            p = p.next;
//        }
//        if(h1!=null) {
//            p.next = h1;
//        }
//        else if(h2!=null) {
//            p.next = h2;
//        }
//        return dummy.next;
//
//    }
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