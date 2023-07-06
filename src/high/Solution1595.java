package high;

import utils.Common;

import java.util.Arrays;
import java.util.List;

/**
 * 2023/6/20
 * 1595. 连通两组点的最小成本
 * 给你两组点，其中第一组中有 size1 个点，第二组中有 size2 个点，且 size1 >= size2 。
 *
 * 任意两点间的连接成本 cost 由大小为 size1 x size2 矩阵给出，其中 cost[i][j] 是第一组中的点 i 和第二组中的点 j 的连接成本。如果两个组中的每个点都与另一组中的一个或多个点连接，则称这两组点是连通的。换言之，第一组中的每个点必须至少与第二组中的一个点连接，且第二组中的每个点必须至少与第一组中的一个点连接。
 *
 * 返回连通两组点所需的最小成本。
 *
 * TODO 状态压缩+动态规划
 *      是我永远的痛点，永远都做不明白
 * @author x.z
 */
public class Solution1595 {
    public int connectTwoGroups(List<List<Integer>> cost) {
        int[] size1 = new int[cost.size()];
        int[] size2 = new int[cost.get(0).size()];
        int sum = 0;
        for (int i = 0; i < cost.size(); i++) {
            final List<Integer> size1to2 = cost.get(i);
            int size2i = 0;
            for (int j = 1; j < size1to2.size(); j++) {
                if (size1to2.get(size2i) > size1to2.get(j)) {
                    size2i = j;
                }
            }
            size1[i] = size2i;
            size2[size2i] ++;
            sum += size1to2.get(size2i);
        }
        for (int i = 0; i < size2.length; i++) {
            if(size2[i] > 0){
                continue;
            }
            int tmpMin = 200;
            for (int j = 0; j < cost.size(); j++) {
                final int s2i = size1[j];
                if(size2[s2i] == 1){
                    tmpMin = Math.min(tmpMin, cost.get(j).get(i));
                }else{
                    tmpMin = Math.min(tmpMin, cost.get(j).get(i) - cost.get(j).get(s2i));
                }
            }
            sum += tmpMin;
        }
        return sum;
    }

    public int connectTwoGroupsAnswer(List<List<Integer>> cost) {
        int size1 = cost.size(), size2 = cost.get(0).size(), m = 1 << size2;
        int[][] dp = new int[size1 + 1][m];
        for (int i = 0; i <= size1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        dp[0][0] = 0;
        for (int i = 1; i <= size1; i++) {
            for (int s = 0; s < m; s++) {
                for (int k = 0; k < size2; k++) {
                    if ((s & (1 << k)) == 0) {
                        continue;
                    }
                    dp[i][s] = Math.min(dp[i][s], dp[i][s ^ (1 << k)] + cost.get(i - 1).get(k));
                    dp[i][s] = Math.min(dp[i][s], dp[i - 1][s] + cost.get(i - 1).get(k));
                    dp[i][s] = Math.min(dp[i][s], dp[i - 1][s ^ (1 << k)] + cost.get(i - 1).get(k));
                }
            }
        }
        return dp[size1][m - 1];
    }

    /**
     * 灵神题解-初，使用递归
     */
    static class Solution0 {
        private List<List<Integer>> cost;
        private int[] minCost;
        private int[][] memo;

        public int connectTwoGroups(List<List<Integer>> cost) {
            this.cost = cost;
            int n = cost.size(), m = cost.get(0).size();
            minCost = new int[m];
            Arrays.fill(minCost, Integer.MAX_VALUE);
            for (int j = 0; j < m; j++)
                for (var c : cost)
                    minCost[j] = Math.min(minCost[j], c.get(j));

            memo = new int[n][1 << m];
            for (int i = 0; i < n; i++)
                Arrays.fill(memo[i], -1); // -1 表示没有计算过
            return dfs(n - 1, (1 << m) - 1);
        }

        private int dfs(int i, int j) {
            if (i < 0) {
                int res = 0;
                for (int k = 0; k < minCost.length; k++)
                    if ((j >> k & 1) == 1) // 第二组的点 k 未连接
                        res += minCost[k]; // 去第一组找个成本最小的点连接
                return res;
            }
            if (memo[i][j] != -1) return memo[i][j]; // 之前算过了
            int res = Integer.MAX_VALUE;
            for (int k = 0; k < minCost.length; k++) // 第一组的点 i 与第二组的点 k
                res = Math.min(res, dfs(i - 1, j & ~(1 << k)) + cost.get(i).get(k));
            return memo[i][j] = res; // 记忆化
        }
    }

    /**
     * 灵神题解-去除递归
     */
    static class Solution1 {
        public int connectTwoGroups(List<List<Integer>> cost) {
            int n = cost.size(), m = cost.get(0).size();
            var minCost = new int[m];
            Arrays.fill(minCost, Integer.MAX_VALUE);
            for (int j = 0; j < m; j++)
                for (var c : cost)
                    minCost[j] = Math.min(minCost[j], c.get(j));

//            此处是在计算，当点集 k 未连接时
//            即第二组点存在的未连接的点，将其与一个最小成本的第一组的点链接起来
//            k 则是枚举所有第二组点集子集的可能，或者说状态
            var f = new int[n + 1][1 << m];
            for (int j = 0; j < 1 << m; j++)
                for (int k = 0; k < m; k++)
//                    TODO 这里的点k未连接，严格来讲，应该是点集 k 未连接
                    if ((j >> k & 1) == 1) // 第二组的点 k 未连接
                        f[0][j] += minCost[k]; // 去第一组找个成本最小的点连接

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 1 << m; j++) {
                    int res = Integer.MAX_VALUE;
                    for (int k = 0; k < m; k++) // 第一组的点 i 与第二组的点 k
                        res = Math.min(res, f[i][j & ~(1 << k)] + cost.get(i).get(k));
                    f[i + 1][j] = res;
                }
            }
            return f[n][(1 << m) - 1];
        }
    }

    public static void main(String[] args) {
        final Solution1595 demo = new Solution1595();

        String case54 = "[[93,56,92],[53,44,18],[86,44,69],[54,60,30]]";
        String case64 = "[[1,3,5],[4,1,1],[1,5,3]]";
        String case62 = "[[74,48,50,22],[94,12,35,28],[32,5,0,71],[25,23,34,95]]";
        String case72 = "[[21,76,97,7,47,87]," +
                        "[28,65,45,89,98,60]," +
                        "[99,59,30,38,43,69]," +
                        "[81,56,62,83,26,31]," +
                        "[28,31,74,74,34,20]," +
                        "[65,45,14,90,28,93]," +
                        "[47,60,21,27,47,77]," +
                        "[47,68,71,75,26,42]]";
//           21 31 14 7 26 20
//        7           7
//        28 28
//        30       30
//        26            26
//        20    31         20
//        14       14
//        21       21
//        26            26

//        System.out.println(demo.connectTwoGroupsAnswer(Common.string2IntIntList(case72)));

        final Solution0 subDemo = new Solution0();
        System.out.println(subDemo.connectTwoGroups(Common.string2IntIntList(case72)));
    }
}
