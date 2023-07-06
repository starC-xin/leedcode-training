package media.other;

import struct.ListNode;

/**
 * 2023/7/2
 *
 * @author x.z
 */
public class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode root = null;
        ListNode node = null;
        int cache = 0;
        while(l1 != null || l2 != null){
            int num1 = null == l1 ? 0 : l1.val;
            int num2 = null == l2 ? 0 : l2.val;
            int tmp = num1 + num2 + cache;
            if(node == null){
                node = new ListNode(tmp % 10);
            }else{
                node.next = new ListNode(tmp % 10);
                node = node.next;
            }
            cache = tmp / 10;
            if(null == root){
                root = node;
            }
            if(null != l1){
                l1 = l1.next;
            }
            if(null != l2){
                l2 = l2.next;
            }
        }

        if(cache == 1){
            node.next = new ListNode(1);
        }
        return root;
    }

    /**
     * 四年前我的代码还是很稚嫩啊
     * 而且执行速度远没有我现在随手写的快
     */
    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode(-1);
            ListNode p1 = l1;
            ListNode p2 = l2;
            ListNode p = dummy;
            int flag = 0;
            while (p1 != null || p2 != null) {
                int temp = flag;
                if (p1 != null) {
                    temp += p1.val;
                    p1 = p1.next;
                }
                if (p2 != null) {
                    temp += p2.val;
                    p2 = p2.next;
                }
                if (temp > 9) {
                    temp -= 10;
                    flag = 1;
                } else {
                    flag = 0;
                }
                p.next = new ListNode(temp);
                p = p.next;
            }
            if (flag == 1) {
                p.next = new ListNode(1);
            }
            return dummy.next;
        }
    }
}
