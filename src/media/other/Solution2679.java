package media.other;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2023/7/4
 *
 * @author x.z
 */
public class Solution2679 {
    public int matrixSum(int[][] nums) {
        final PriorityQueue<Integer>[] pqs = new PriorityQueue[nums.length];
        for (int i = 0; i < nums.length; i++) {
            pqs[i] = new PriorityQueue<>(Comparator.reverseOrder());
            for (int j = 0; j < nums[i].length; j++) {
                pqs[i].offer(nums[i][j]);
            }
        }

        int res = 0;
        for (int i = 0; i < nums[0].length; i++) {
            int max = 0;
            for (PriorityQueue<Integer> pq : pqs) {
                max = Math.max(pq.poll(), max);
            }
            res += max;
        }
        return res;
    }
}
