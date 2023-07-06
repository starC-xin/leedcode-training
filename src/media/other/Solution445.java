package media.other;

import struct.ListNode;

import java.util.Stack;

/**
 * 2023/7/3
 *
 * @author x.z
 */
public class Solution445 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while(l1 != null){
            s1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null){
            s2.push(l2.val);
            l2 = l2.next;
        }
        int cache = 0;
        ListNode node = null;
        while(!s1.isEmpty() || !s2.isEmpty()){
            int num1 = s1.isEmpty() ? 0 : s1.pop();
            int num2 = s2.isEmpty() ? 0 : s2.pop();
            int tmpSum = num1 + num2 + cache;
            if(null == node){
                node = new ListNode(tmpSum % 10);
            }else{
                ListNode tmp = new ListNode(tmpSum % 10);
                tmp.next = node;
                node = tmp;
            }
            cache = tmpSum / 10;
        }
        if(cache > 0){
            ListNode tmp = new ListNode(1);
            tmp.next = node;
            node = tmp;
        }

        return node;
    }
}
