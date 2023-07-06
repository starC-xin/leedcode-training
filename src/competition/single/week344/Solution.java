package competition.single.week344;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 2023/5/7
 *
 * @author x.z
 */
public class Solution {

    /**
     * 6416. 找出不同元素数目差数组
     * 给你一个下标从 0 开始的数组 nums ，数组长度为 n 。
     * nums 的 不同元素数目差 数组可以用一个长度为 n 的数组 diff 表示，其中 diff[i] 等于前缀 nums[0, ..., i] 中不同元素的数目 减去 后缀 nums[i + 1, ..., n - 1] 中不同元素的数目。
     * 返回 nums 的 不同元素数目差 数组。
     * 注意 nums[i, ..., j] 表示 nums 的一个从下标 i 开始到下标 j 结束的子数组（包含下标 i 和 j 对应元素）。特别需要说明的是，如果 i > j ，则 nums[i, ..., j] 表示一个空子数组。
     */
    public int[] distinctDifferenceArray(int[] nums) {
        if(nums.length == 1){
            return new int[]{1};
        }
        int[] result = new int[nums.length];
        Set<Integer> pre = new HashSet<>();
        Set<Integer> sub = new HashSet<>();

        for (int i = 0; i < result.length; i++) {
            pre.clear();
            sub.clear();
            for (int j = 0; j <= i; j++) {
                pre.add(nums[j]);
            }
            for (int j = i + 1; j < nums.length; j++) {
                sub.add(nums[j]);
            }
            result[i] = pre.size() - sub.size();
        }

        return result;
    }


    /**
     * 6417. 频率跟踪器
     * 请你设计并实现一个能够对其中的值进行跟踪的数据结构，并支持对频率相关查询进行应答。
     * 实现 FrequencyTracker 类：
     * FrequencyTracker()：使用一个空数组初始化 FrequencyTracker 对象。
     * void add(int number)：添加一个 number 到数据结构中。
     * void deleteOne(int number)：从数据结构中删除一个 number 。数据结构 可能不包含 number ，在这种情况下不删除任何内容。
     * bool hasFrequency(int frequency): 如果数据结构中存在出现 frequency 次的数字，则返回 true，否则返回 false。
     *
     * Your FrequencyTracker object will be instantiated and called as such:
     * FrequencyTracker obj = new FrequencyTracker();
     * obj.add(number);
     * obj.deleteOne(number);
     * boolean param_3 = obj.hasFrequency(frequency);
     */
    static class FrequencyTracker {

        private HashMap<Integer, Integer> map;
        private HashMap<Integer, Integer> countMap;

        public FrequencyTracker() {
            map = new HashMap<>();
            countMap = new HashMap<>();
        }

        public void add(int number) {
            final Integer tmpCount = map.get(number);
            if(tmpCount == null){
                map.put(number, 1);
                countMap.merge(1, 1, Integer::sum);
            }else{
                map.put(number, tmpCount + 1);
                countMap.merge(tmpCount, -1, Integer::sum);
                countMap.merge(tmpCount + 1, 1, Integer::sum);
            }
        }

        public void deleteOne(int number) {
            final Integer tmp = map.get(number);
            if(null == tmp || tmp == 0){
                return;
            }
            if(tmp - 1 == 0){
                map.remove(number);
            }else{
                map.put(number, tmp - 1);
                countMap.merge(tmp - 1, 1, Integer::sum);
            }
            countMap.merge(tmp, -1, Integer::sum);
        }

        public boolean hasFrequency(int frequency) {
            final Integer integer = countMap.get(frequency);
            return null != integer && 0 != integer;
        }
    }

    /**
     * 6418. 有相同颜色的相邻元素数目
     * 给你一个下标从 0 开始、长度为 n 的数组 nums 。一开始，所有元素都是 未染色 （值为 0 ）的。
     * 给你一个二维整数数组 queries ，其中 queries[i] = [indexi, colori] 。
     * 对于每个操作，你需要将数组 nums 中下标为 indexi 的格子染色为 colori 。
     * 请你返回一个长度与 queries 相等的数组 answer ，其中 answer[i]是前 i 个操作 之后 ，相邻元素颜色相同的数目。
     * 更正式的，answer[i] 是执行完前 i 个操作后，0 <= j < n - 1 的下标 j 中，满足 nums[j] == nums[j + 1] 且 nums[j] != 0 的数目。
     *
     * TODO 思路：每次操作后，顶多在变化位置有相邻元素增加/减少，直接改变前后，左右分别判定一下即可
     */
    public int[] colorTheArray(int n, int[][] queries) {
        if(1 == n || queries.length == 1){
            return new int[queries.length];
        }
        int[] source = new int[n];
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] tmp = queries[i];
            if(0 == i){
                source[tmp[0]] = tmp[1];
                continue;
            }
            result[i] = result[i - 1];
            if(0 != source[tmp[0]]){
                if(tmp[0] > 0 && source[tmp[0] - 1] == source[tmp[0]] && result[i] > 0){
                    result[i] -= 1;
                }
                if(tmp[0] < n - 1 && source[tmp[0] + 1] == source[tmp[0]] && result[i] > 0){
                    result[i] -= 1;
                }
            }

            source[tmp[0]] = tmp[1];
//            染色后相邻计算
            if(tmp[0] > 0 && source[tmp[0] - 1] == source[tmp[0]]){
                result[i] += 1;
            }
            if(tmp[0] < n - 1 && source[tmp[0] + 1] == source[tmp[0]]){
                result[i] += 1;
            }
        }

        return result;
    }

    /**
     * 6419. 使二叉树所有路径值相等的最小代价
     * 给你一个整数 n 表示一棵 满二叉树 里面节点的数目，节点编号从 1 到 n 。根节点编号为 1 ，树中每个非叶子节点 i 都有两个孩子，分别是左孩子 2 * i 和右孩子 2 * i + 1 。
     * 树中每个节点都有一个值，用下标从 0 开始、长度为 n 的整数数组 cost 表示，其中 cost[i] 是第 i + 1 个节点的值。每次操作，你可以将树中 任意 节点的值 增加 1 。你可以执行操作 任意 次。
     * 你的目标是让根到每一个 叶子结点 的路径值相等。请你返回 最少 需要执行增加操作多少次。
     *
     * 注意：
     * 满二叉树 指的是一棵树，它满足树中除了叶子节点外每个节点都恰好有 2 个节点，且所有叶子节点距离根节点距离相同。
     * 路径值 指的是路径上所有节点的值之和。
     *
     * TODO 思路：按照树的性质找出最大路径，记住这条路径并且不让该路径上的节点变动
     *           在其余路径中选出次大路径，然后在公共节点上增加权值直至等于最大路径，然后固定改路径，重复操作，记录操作的增加权值的次数
     *           最后只剩八分钟了，来不及写了
     *           涉及核心算法：DFS
     */
    public int minIncrements(int n, int[] cost) {
        return 0;
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();


//        final FrequencyTracker tracker = new FrequencyTracker();
//        tracker.add(1);
//        tracker.deleteOne(1);
//        System.out.println(tracker.hasFrequency(1));

        int[][] op = {
                {0, 2},
                {1, 2},
                {3,1},
                {1,1},
                {2,1},
        };
        int n = 1;
        for (int i : solution.colorTheArray(n, op)) {
            System.out.println(i);
        }

    }
}
