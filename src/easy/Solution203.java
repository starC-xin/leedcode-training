package easy;

import struct.ListNode;

/**
 * 2023/6/10
 * 203. 移除链表元素
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * @author x.z
 */
public class Solution203 {

    public ListNode removeElements(ListNode head, int val) {
        while(head != null){
            if(head.val == val){
                head = head.next;
            }else{
                break;
            }
        }
        ListNode pre = head;
        if(null == pre){
            return null;
        }
        ListNode cur = head.next;
        while(cur != null){
            if(cur.val == val){
                pre.next = cur.next;
            }else{
                pre = cur;
            }
            cur = pre.next;
        }
        return head;
    }
}
