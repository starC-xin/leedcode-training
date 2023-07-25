package media.other;

import utils.Common;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2023/7/25
 *
 * @author x.z
 */
public class Solution2208 {
    /**
     * 思路上，这道题采用贪心算法直接一步到位
     * 想要方便一点的话，采用优先队列或者最大堆的设定来解决选择上的问题
     *
     * 一次过，但是速度堪忧
     */
    public int halveArray(int[] nums) {
        PriorityQueue<Double> pq = new PriorityQueue<>(Comparator.reverseOrder());
        double total = 0;
        for (int num : nums) {
            pq.add((double) num);
            total += num;
        }
        int res =0;
        double tmp = total / 2;
        while(total - tmp> 0){
            Double poll = pq.poll();
            poll /= 2;
            total -= poll;
            pq.offer(poll);
            res ++;
        }
        return res;
    }

    public static void main(String[] args) {
        final Solution2208 demo = new Solution2208();

        System.out.println(demo.halveArray(Common.string2IntArr("[5,19,8,1]")));
    }
}
