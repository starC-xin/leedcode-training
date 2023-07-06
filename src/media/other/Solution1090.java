package media.other;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 2023/5/23
 * 1090. 受标签影响的最大值
 * 我们有一个 n 项的集合。给出两个整数数组 values 和 labels ，第 i 个元素的值和标签分别是 values[i] 和 labels[i]。还会给出两个整数 numWanted 和 useLimit 。
 * 从 n 个元素中选择一个子集 s :
 * 子集 s 的大小 小于或等于 numWanted 。
 * s 中 最多 有相同标签的 useLimit 项。
 * 一个子集的 分数 是该子集的值之和。
 *
 * 返回子集 s 的最大 分数 。
 *
 * @author x.z
 */
public class Solution1090 {
    /**
     * 最简单的方式，直接使用集合解决
     * TODO 思路
     *      使用优先队列
     *
     * TODO 案例直接手搓了一个随机快排，排序时同时交换 values 和 labels，其他思路都是一致的
     */
    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        final PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[0] == b[0]){
                return 0;
            }
            return a[0] > b[0] ? -1 : 1;
        });
        int maxLabel = 1;
        for (int i = 0; i < values.length; i++) {
            pq.offer(new int[]{values[i], labels[i]});
            if(labels[i] > maxLabel){
                maxLabel = labels[i];
            }
        }

        int result = 0;
        int[] use = new int[maxLabel + 1];
        int count = 0;
        while(pq.size() > 0 && count < numWanted){
            final int[] poll = pq.poll();
            if(use[poll[1]] >= useLimit){
                continue;
            }
            result += poll[0];
            count ++;
            use[poll[1]] ++;
        }

        return result;
    }

    public static void main(String[] args) {
        final Solution1090 demo = new Solution1090();

//        例子都没过
//        [5,4,3,2,1]
//        [1,1,2,2,3]
//        3
//        1
        int[] values = {5,4,3,2,1};
        int[] labels = {1,1,2,2,3};
        int wanted = 3;
        int useLimit = 1;

        System.out.println(demo.largestValsFromLabels(values, labels, wanted, useLimit));
    }
}
