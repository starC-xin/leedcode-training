package media.other;

import struct.ListNode;
import utils.Common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2023/6/11
 * 1171. 从链表中删去总和值为零的连续节点
 * 给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。
 *
 * 删除完毕后，请你返回最终结果链表的头节点。
 *
 * 你可以返回任何满足题目要求的答案。
 *
 * （注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）
 * @author x.z
 */
public class Solution1171 {
    public ListNode removeZeroSumSublists(ListNode head) {
        Map<ListNode, Integer> map = new HashMap<>();
        ListNode tmp = head;
        ListNode pre = null;
        while(null != tmp){
            if(tmp.val == 0){
                if(null == pre){
                    head = tmp.next;
                }else{
                    pre.next = tmp.next;
                }
                tmp = tmp.next;
                continue;
            }

            ListNode wanna = null;
            for (Map.Entry<ListNode, Integer> entry : map.entrySet()) {
                final int sum = entry.getValue() + tmp.val;
                if(0 == sum){
                    wanna = entry.getKey();
                }else{
                    entry.setValue(sum);
                }
            }

            if(null != wanna){
                ListNode tmpPre = null;
                ListNode tmpNode = head;
                while(tmpNode != null && tmpNode != wanna){
                    tmpPre = tmpNode;
                    tmpNode = tmpNode.next;
                }

                if(head == wanna){
                    head = tmp.next;
                }else{
                    if(null == tmpPre){
                        head.next = tmp.next;
                    }else{
                        tmpPre.next = tmp.next;
                    }
                }

                while(wanna != null && wanna != tmp){
                    map.remove(wanna);
                    wanna = wanna.next;
                }
                pre = tmpPre;
            }else{
                map.put(tmp, tmp.val);
                pre = tmp;
            }
            tmp = tmp.next;
        }
        return head;
    }

    /**
     * 因为节点也就1000个，暴力递归也不是不行
     */
    public ListNode removeZeroSumSublistsAnswer(ListNode head) {
        if (head == null) return null;
        int sum = head.val;
        if (sum == 0) return removeZeroSumSublistsAnswer(head.next);
        var hn = head.next;
        for (var cur = hn; cur != null; cur = cur.next) if ((sum += cur.val) == 0) return removeZeroSumSublistsAnswer(cur.next);
        head.next = removeZeroSumSublistsAnswer(hn);
        return head;
    }

    /**
     * 好诡异的解法
     * 但是线性
     */
    public ListNode removeZeroSumSublistsAnswer1(ListNode head) {
        Map<Integer, ListNode> dict = new HashMap<>();
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = head;
        int sum = 0;
        while(cur != null){
            sum += cur.val;
            dict.put(sum, cur);
            cur = cur.next;
        }

        cur = dummy;
        sum = 0;
        while(cur != null){
            sum += cur.val;
            if(dict.get(sum) != null && dict.get(sum) != cur){
                cur.next = dict.get(sum).next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        final Solution1171 demo = new Solution1171();

        final ListNode head = demo.removeZeroSumSublists(Common.string2ListNode("[1,1,2,-3,0,0,-1,3,1]"));

        Common.printListNode(head);
    }
}
