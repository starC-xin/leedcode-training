package competition.single.week345;

import java.util.*;

/**
 * 2023/5/14
 *
 * @author x.z
 */
public class Solution {

    /**
     * 6430. 找出转圈游戏输家
     * n 个朋友在玩游戏。这些朋友坐成一个圈，按 顺时针方向 从 1 到 n 编号。从第 i 个朋友的位置开始顺时针移动 1 步会到达第 (i + 1) 个朋友的位置（1 <= i < n），而从第 n 个朋友的位置开始顺时针移动 1 步会回到第 1 个朋友的位置。
     *
     * 游戏规则如下：
     * 第 1 个朋友接球。
     * 接着，第 1 个朋友将球传给距离他顺时针方向 k 步的朋友。
     * 然后，接球的朋友应该把球传给距离他顺时针方向 2 * k 步的朋友。
     * 接着，接球的朋友应该把球传给距离他顺时针方向 3 * k 步的朋友，以此类推。
     * 换句话说，在第 i 轮中持有球的那位朋友需要将球传递给距离他顺时针方向 i * k 步的朋友。
     * 当某个朋友第 2 次接到球时，游戏结束。
     * 在整场游戏中没有接到过球的朋友是 输家 。
     * 给你参与游戏的朋友数量 n 和一个整数 k ，请按升序排列返回包含所有输家编号的数组 answer 作为答案。
     *
     */
    public int[] circularGameLosers(int n, int k) {
        if(n == 1){
            return new int[0];
        }
        boolean[] temp = new boolean[n];
        temp[0] = true;
        int i = 0;
        int step = 1;
        while(true){
            i += k * step ++;
            i %= n;
            if(! temp[i]){
                temp[i] = true;
            }else{
                break;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (i = 0; i < temp.length; i++) {
            if(! temp[i]){
                list.add(i + 1);
            }
        }
        int[] result = new int[list.size()];
        for (int i1 = 0; i1 < list.size(); i1++) {
            result[i1] = list.get(i1);
        }
        return result;
    }

    /**
     * 6431. 相邻值的按位异或
     * 下标从 0 开始、长度为 n 的数组 derived 是由同样长度为 n 的原始 二进制数组 original 通过计算相邻值的 按位异或（⊕）派生而来。
     *
     * 特别地，对于范围 [0, n - 1] 内的每个下标 i ：
     *
     * 如果 i = n - 1 ，那么 derived[i] = original[i] ⊕ original[0]
     * 否则 derived[i] = original[i] ⊕ original[i + 1]
     * 给你一个数组 derived ，请判断是否存在一个能够派生得到 derived 的 有效原始二进制数组 original 。
     *
     * 如果存在满足要求的原始二进制数组，返回 true ；否则，返回 false 。
     *
     * 二进制数组是仅由 0 和 1 组成的数组。
     *
     */
    public boolean doesValidArrayExist(int[] derived) {
        int[] origin = new int[derived.length];
        origin[0] = 0;
        for(int i = 0; i < derived.length; i++){
            int tmp = derived[i] ^ origin[i];
            if(i == derived.length - 1){
                if(tmp != origin[0]){
                    return false;
                }
            }else{
                origin[i + 1] = tmp;
            }
        }
        return true;
    }

    /**
     * 6433. 矩阵中移动的最大次数
     * 给你一个下标从 0 开始、大小为 m x n 的矩阵 grid ，矩阵由若干 正 整数组成。
     *
     * 你可以从矩阵第一列中的 任一 单元格出发，按以下方式遍历 grid ：
     *
     * 从单元格 (row, col) 可以移动到 (row - 1, col + 1)、(row, col + 1) 和 (row + 1, col + 1) 三个单元格中任一满足值 严格 大于当前单元格的单元格。
     * 返回你在矩阵中能够 移动 的 最大 次数。
     *
     * TODO 向 右/右上/右下 移动
     *      DFS、回归算法，应该比较好解决
     */
    public int maxMoves(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            max = Math.max(dfs(grid, 0, i, 0), max);
            if(max == grid[0].length - 1){
                break;
            }
        }
        return max;
    }

    private int dfs(int[][] grid, int maxStep, int row, int col) {
        int cur = grid[row][col];
        if(col == grid[0].length - 1 || maxStep == grid[0].length - 1){
            return maxStep;
        }
        int tmpStep = maxStep;
        if(row + 1 < grid.length && cur < grid[row + 1][col + 1]){
            maxStep = Math.max(maxStep, dfs(grid, tmpStep + 1, row + 1, col + 1));
        }
//        每次判断是否到达最大值，中断后续遍历，否则递归深度太大会超时
        if(maxStep == grid[0].length - 1){
            return maxStep;
        }
        if(cur < grid[row][col + 1]){
            maxStep = Math.max(maxStep, dfs(grid, tmpStep + 1, row, col + 1));
        }
//        每次判断是否到达最大值，中断后续遍历，否则递归深度太大会超时
        if(maxStep == grid[0].length - 1){
            return maxStep;
        }
        if(row - 1 >= 0 && cur < grid[row - 1][col + 1]){
            maxStep = Math.max(maxStep, dfs(grid, tmpStep + 1, row - 1, col + 1));
        }
        return maxStep;
    }

    /**
     * 6432. 统计完全连通分量的数量
     * 给你一个整数 n 。现有一个包含 n 个顶点的 无向 图，顶点按从 0 到 n - 1 编号。给你一个二维整数数组 edges 其中 edges[i] = [ai, bi] 表示顶点 ai 和 bi 之间存在一条 无向 边。
     *
     * 返回图中 完全连通分量 的数量。
     *
     * 如果在子图中任意两个顶点之间都存在路径，并且子图中没有任何一个顶点与子图外部的顶点共享边，则称其为 连通分量 。
     *
     * 如果连通分量中每对节点之间都存在一条边，则称其为 完全连通分量 。
     *
     * TODO 完全连通分量，意为每一个顶点在当前连通图中只需要一条边即可到达当前连通图中任意顶点
     *      换句话说，就是一个完全无向图，稠密图
     *      即，所含边数量为 n*(n-1)/2
     *      或者说，每个顶点含有 n-1 条边
     *      另外，一个单一顶点和仅有两个点 本身即为完全连通分量（特殊情况
     */
    public int countCompleteComponents(int n, int[][] edges) {
        boolean[][] graph = new boolean[n][n];
        int[] linkCount = new int[n];
        int[] link = new int[n];

        for (int i = 0; i < link.length; i++) {
            link[i] = i;
        }

        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = true;
            graph[edge[1]][edge[0]] = true;

            linkCount[edge[0]] ++;
            linkCount[edge[1]] ++;
//            此处必须要加入并查集来执行连通分量的检测
            link[edge[1]] = link[edge[0]];
        }

        Map<Integer, Integer> containCount = new HashMap<>();
        for (int j : link) {
            final Integer def = containCount.getOrDefault(j, 0);
            containCount.put(j, def + 1);
        }

        Set<Integer> visited = new HashSet<>();
        int result = 0;
        for (int i = 0; i < linkCount.length; i++) {
            if(visited.contains(i)){
                continue;
            }
            visited.add(i);

            final Integer hasNodeCount = containCount.get(link[i]);
            if(1 == hasNodeCount){
                result ++;
                continue;
            }
            if(2 == hasNodeCount){
                result ++;
                for (int j = 0; j < graph.length; j++) {
                    if(graph[i][j]){
                        visited.add(j);
                    }
                }
                continue;
            }
            if(linkCount[i] == hasNodeCount - 1){
                boolean flag = true;
                for (int j = 0; j < graph[i].length; j++) {
                    if(graph[i][j]){
                        if(visited.contains(j)){
                            continue;
                        }
                        visited.add(j);
                        if(linkCount[j] != hasNodeCount - 1){
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag){
                    result ++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();

//        int n = 5, k = 2;
//        for (int i : solution.circularGameLosers(n, k)) {
//            System.out.println(i);
//        }

//        int[] tmp = {1,1};
//        System.out.println(solution.doesValidArrayExist(tmp));

//        [[1000000,92910,1021,1022,1023,1024,1025,1026,1027,1028,1029,1030,1031,1032,1033,1034,1035,1036,1037,1038,1039,1040,1041,1042,1043,1044,1045,1046,1047,1048,1049,1050,1051,1052,1053,1054,1055,1056,1057,1058,1059,1060,1061,1062,1063,1064,1065,1066,1067,1068],
//        [1069,1070,1071,1072,1073,1074,1075,1076,1077,1078,1079,1080,1081,1082,1083,1084,1085,1086,1087,1088,1089,1090,1091,1092,1093,1094,1095,1096,1097,1098,1099,1100,1101,1102,1103,1104,1105,1106,1107,1108,1109,1110,1111,1112,1113,1114,1115,1116,1117,1118]]
//        int[][] grid = {
//                {2,4,3,5},
//                {5,4,9,3},
//                {3,4,2,11},
//                {10,9,13,15}
//        };
//        int[][] grid = {
//                {1000000,92910,1021,1022,1023,1024,1025,1026,1027,1028,1029,1030,1031,1032,1033,1034,1035,1036,1037,1038,1039,1040,1041,1042,1043,1044,1045,1046,1047,1048,1049,1050,1051,1052,1053,1054,1055,1056,1057,1058,1059,1060,1061,1062,1063,1064,1065,1066,1067,1068},
//                {1069,1070,1071,1072,1073,1074,1075,1076,1077,1078,1079,1080,1081,1082,1083,1084,1085,1086,1087,1088,1089,1090,1091,1092,1093,1094,1095,1096,1097,1098,1099,1100,1101,1102,1103,1104,1105,1106,1107,1108,1109,1110,1111,1112,1113,1114,1115,1116,1117,1118},
//        };
//        System.out.println(solution.maxMoves(grid));

        int n = 3;
        int[][] edges = {
                {0,1},
                {0,2},
        };
        System.out.println(solution.countCompleteComponents(n, edges));
    }
}
