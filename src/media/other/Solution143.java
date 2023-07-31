package media.other;

import struct.ListNode;
import utils.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * 2023/7/31
 *
 * @author x.z
 */
public class Solution143 {
    /**
     * 链表转换为数组，然后利用数组的性质执行计算会比较简单
     * 否则可能需要采用递归的方式执行节点切换
     */
    public void reorderList(ListNode head) {
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while(null != cur){
            list.add(cur);
            cur = cur.next;
        }
        int begin = 0, end = list.size() - 1;
        ListNode curNode = null;
        while(begin <= end){
            final ListNode beginNode = list.get(begin++);
            final ListNode endNode = list.get(end--);
            beginNode.next = null;
            endNode.next = null;
            if(begin < end){
                beginNode.next = endNode;
            }else{
                if(begin - end == 1 || begin == end){
                    beginNode.next = endNode;
                }
            }
            if (curNode != null) {
                curNode.next = beginNode;
            }
            curNode = endNode;
        }
    }

    public static void main(String[] args) {
        final Solution143 demo = new Solution143();

        final ListNode head = Common.string2ListNode("[1,2,3,4,5]");
        demo.reorderList(head);
        Common.printListNode(head);
    }
}
