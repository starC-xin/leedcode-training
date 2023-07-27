package easy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2023/7/27
 *
 * @author x.z
 */
public class Solution2500 {
    /**
     * 维护一个最大删除元素作为累计值
     * 每一行的维护有类似三种方式：
     *   将每一行转为优先队列
     *   为每一行设置一个访问标志位，每次搜寻最大值
     *   对每一行排序，逐位累计
     */
    public int deleteGreatestValue(int[][] grid) {
        PriorityQueue<Integer>[] pqs = new PriorityQueue[grid.length];
        for (int i = 0; i < grid.length; i++) {
            pqs[i] = new PriorityQueue<>(Comparator.reverseOrder());
            for (int num : grid[i]) {
                pqs[i].offer(num);
            }
        }
        int res = 0;
        while(!pqs[pqs.length - 1].isEmpty()){
            int tmpMax = 0;
            for (PriorityQueue<Integer> pq : pqs) {
                tmpMax = Math.max(tmpMax, pq.poll());
            }
            res += tmpMax;
        }
        return res;
    }
}
