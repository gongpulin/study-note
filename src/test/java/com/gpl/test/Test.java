package com.gpl.test;

/**
 * @author gongpulin
 * date 2020-07-23
 */
public class Test {
    private static int i = 0;
    private static int j = 0;
    public static void main(String[] args) {
        System.out.println(String.valueOf(Double.valueOf("3.0").intValue()));
//        ListNode head = new ListNode(0);
//
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        head.next = node1;
//        node1.next = node2;
//        node2.next = node3;
//        traverse(head);
    }
    private static void traverse(ListNode node) {
        if (node == null) {
            return;
        }
        i++;
        System.out.println("i:"+i);
        System.out.println("nodei:"+node.val);
        System.out.println("----------traverse before-----------------");
        traverse(node.next);
        System.out.println("----------traverse after-----------------");
        j++;
        System.out.println("j:"+j);
        System.out.println("nodej:"+node.val);
    }
}

class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
    }
}