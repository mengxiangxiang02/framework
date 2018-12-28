package com.architecture;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/26 14:07
 * @Description:链表反转
 */
public class LinkedRevert {
    public static void main(String args[]) {
        Node head = new LinkedRevert().new Node(1);
        head.next = new LinkedRevert().new Node(2);
        head.next.next = new LinkedRevert().new Node(3);
        System.out.println(head.num + head.next.num + head.next.next.num);
        Node revert = revert(head);
        System.out.println(revert.num);
    }

    public static Node revert(Node head) {
        //如果链表为空或者只有一个节点，无需反转，直接返回原链表的头结点
        if (head == null || head.next == null) {
            return head;
        }

        Node current=head;
        Node next = null; //定义当前结点的下一个结点

        Node reverseHead = null;  //反转后新链表的表头
        while(current!=null)
        {
            next=current.next;

            current.next=reverseHead;

            reverseHead=current;

            current=next;
        }

        return reverseHead;
    }

    class Node {
        Node next;
        long num;

        public Node(long num) {
            this.num = num;
        }
    }

}
