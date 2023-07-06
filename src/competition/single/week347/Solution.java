package competition.single.week347;

import utils.Common;

import java.sql.ClientInfoStatus;
import java.util.*;

/**
 * 2023/5/28
 *
 * @author x.z
 */
public class Solution {

    /**
     * 6457. 移除字符串中的尾随零 显示英文描述
     * 通过的用户数0
     * 尝试过的用户数0
     * 用户总通过次数0
     * 用户总提交次数0
     * 题目难度Easy
     * 给你一个用字符串表示的正整数 num ，请你以字符串形式返回不含尾随零的整数 num 。
     */
    public String removeTrailingZeros(String num) {
        final char[] chs = num.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (int i = chs.length - 1; i >= 0; i--) {
            if(flag && chs[i] == '0'){
                continue;
            }
            flag = false;
            sb.append(chs[i]);
        }
        return sb.reverse().toString();
    }

    /**
     * 6440. 对角线上不同值的数量差 显示英文描述
     * 通过的用户数0
     * 尝试过的用户数0
     * 用户总通过次数0
     * 用户总提交次数0
     * 题目难度Easy
     * 给你一个下标从 0 开始、大小为 m x n 的二维矩阵 grid ，请你求解大小同样为 m x n 的答案矩阵 answer 。
     *
     * 矩阵 answer 中每个单元格 (r, c) 的值可以按下述方式进行计算：
     *
     * 令 topLeft[r][c] 为矩阵 grid 中单元格 (r, c) 左上角对角线上 不同值 的数量。
     * 令 bottomRight[r][c] 为矩阵 grid 中单元格 (r, c) 右下角对角线上 不同值 的数量。
     * 然后 answer[r][c] = |topLeft[r][c] - bottomRight[r][c]| 。
     *
     * 返回矩阵 answer 。
     *
     * 矩阵对角线 是从最顶行或最左列的某个单元格开始，向右下方向走到矩阵末尾的对角线。
     *
     * 如果单元格 (r1, c1) 和单元格 (r, c) 属于同一条对角线且 r1 < r ，则单元格 (r1, c1) 属于单元格 (r, c) 的左上对角线。类似地，可以定义右下对角线。
     * TODO 早知道数据量不大就直接暴力解了
     */
    public int[][] differenceOfDistinctValues(int[][] grid) {
        int[][] answer = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            int[] tmp = grid[i];
            for (int j = 0; j < tmp.length; j++) {
//                top-left
                int topLeft = sumDif(grid, i, j, true);
//                bottom-right
                int bottomRight = sumDif(grid, i, j, false);
                answer[i][j] = Math.abs(topLeft - bottomRight);
            }
        }
        return answer;
    }

    private int sumDif(int[][] grid, int i, int j, boolean flag) {
        Set<Integer> dif = new HashSet<>();
        if(flag){
            while(i-- > 0 && j-- > 0){
                dif.add(grid[i][j]);
            }
        }else{
            while(++i < grid.length && ++j < grid[0].length){
                dif.add(grid[i][j]);
            }
        }
        return dif.size();
    }

    /**
     * 6455. 使所有字符相等的最小成本 显示英文描述
     * 通过的用户数1
     * 尝试过的用户数1
     * 用户总通过次数1
     * 用户总提交次数1
     * 题目难度Medium
     * 给你一个下标从 0 开始、长度为 n 的二进制字符串 s ，你可以对其执行两种操作：
     *
     * 选中一个下标 i 并且反转从下标 0 到下标 i（包括下标 0 和下标 i ）的所有字符，成本为 i + 1 。
     * 选中一个下标 i 并且反转从下标 i 到下标 n - 1（包括下标 i 和下标 n - 1 ）的所有字符，成本为 n - i 。
     * 返回使字符串内所有字符 相等 需要的 最小成本 。
     *
     * 反转 字符意味着：如果原来的值是 '0' ，则反转后值变为 '1' ，反之亦然。
     * TODO 有思路，但中断了
     *      讲道理应该是动规，但不知道怎么画转换方程
     */
    public long minimumCost(String s) {
        boolean[] cs = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1'){
                cs[i] = true;
            }
        }
        if(end(cs)){
            return 0;
        }
//        for ()

        return 0;
    }

    private boolean end(boolean[] end){
        boolean flag = end[0];
        for (int i = 1; i < end.length; i++) {
            if(end[i] != flag){
                return false;
            }
        }
        return true;
    }

    /**
     * TODO 草了，这么简单吗？贪心就完事了？
     */
    public long minimumCost1(String s) {
        long res = 0;
//        记录第一个ch
        char c = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
//            当遇到一个与ch不同的字符时执行翻转
            if (s.charAt(i) != c) {
                int after = s.length() - i;
//                如果翻转前半代价小就翻转前半，否则翻转后半
                if (i <= after) {
//                    TODO 这里之所以是 i ，而不是 i+1
//                         是因为当前字符与上一字符不相同，要翻转的字符集不包含本字符，只包含 [0, i-1]
//                         所以是 i
                    res += i;
                } else {
                    res += after;
                }
//                更新 ch
                c = s.charAt(i);
            }
        }
        return res;
    }

    /**
     * 6456. 矩阵中严格递增的单元格数 显示英文描述
     * 通过的用户数41
     * 尝试过的用户数102
     * 用户总通过次数42
     * 用户总提交次数142
     * 题目难度Hard
     * 给你一个下标从 1 开始、大小为 m x n 的整数矩阵 mat，你可以选择任一单元格作为 起始单元格 。
     *
     * 从起始单元格出发，你可以移动到 同一行或同一列 中的任何其他单元格，但前提是目标单元格的值 严格大于 当前单元格的值。
     *
     * 你可以多次重复这一过程，从一个单元格移动到另一个单元格，直到无法再进行任何移动。
     *
     * 请你找出从某个单元开始访问矩阵所能访问的 单元格的最大数量 。
     *
     * 返回一个表示可访问单元格最大数量的整数。
     * TODO 一个深度搜索，和之前的题类似，copy一下
     *      深度搜索行不通，这应该是动规
     *      对整个矩阵排序，数据从小到大，优先队列，poll后对其横列执行 dp+1，最后遍历step得出最大步
     *      思路上应该是可行的，但最后的暴力 case 总是超时，目前认为应该有不需要排序的 dp 方式
     */
    public int maxIncreasingCells(int[][] mat) {
        int[][] step = new int[mat.length][mat[0].length];

//        dp
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[2] == b[2]){
                return 0;
            }
            return a[2] > b[2] ? 1 : -1;
        });
        for (int i = 0; i < mat.length; i++) {
            int[] line = mat[i];
            for (int j = 0; j < line.length; j++) {
                pq.add(new int[]{i, j, mat[i][j]});
            }
        }

        int max = 0;
        while (pq.size() > 0){
            final int[] poll = pq.poll();
            max = comp(mat, step, poll, max);
        }
        return max + 1;
    }

    public int maxIncreasingCells1(int[][] mat) {
        int[][] step = new int[mat.length][mat[0].length];
//        dp
        int max = 0;
        for (int i = 0; i < mat.length; i++) {
            int[] line = mat[i];
            for (int j = 0; j < line.length; j++) {
                max = comp(mat, step, i, j, max);
            }
        }
        return max + 1;
    }

    public int maxIncreasingCells2(int[][] mat) {
//        dp
        TreeMap<Integer, ArrayList<int[]>> sortMap = new TreeMap<>();
        for (int i = 0; i < mat.length; i++) {
            int[] line = mat[i];
            for (int j = 0; j < line.length; j++) {
                sortMap.computeIfAbsent(line[j], var -> new ArrayList<>()).add(new int[]{i, j, 0});
            }
        }

        int max = 0;
        int[] row = new int[mat.length], col = new int[mat[0].length];
        for (Map.Entry<Integer, ArrayList<int[]>> entry : sortMap.entrySet()) {
            for (int[] pos : entry.getValue()) {
                pos[2] = Math.max(pos[2], Math.max(row[pos[0]], col[pos[1]]) + 1);
                max = Math.max(max, pos[2]);
            }
            for (int[] pos : entry.getValue()) {
                row[pos[0]] = Math.max(row[pos[0]], pos[2]);
                col[pos[1]] = Math.max(col[pos[1]], pos[2]);
            }
        }
        return max;
    }

    private int comp(int[][] mat, int[][] step, int row, int col, int max) {
        int num = mat[row][col];
        int curStep = step[row][col];
//        row
        int[] line = mat[row];
        for (int i = 0; i < line.length; i++) {
            if(line[i] > num){
                step[row][i] = Math.max(step[row][i], curStep + 1);
                max = Math.max(step[row][i], max);
                max = comp(mat, step, row, i, max);
            }
        }

//        col
        for (int i = 0; i < mat.length; i++) {
            if(mat[i][col] > num){
                step[i][col] = Math.max(step[i][col], curStep + 1);
                max = Math.max(step[i][col], max);
                max = comp(mat, step, i, col, max);
            }
        }

        return max;
    }

    private int comp(int[][] mat, int[][] step, int[] pos, int max){
        int row = pos[0];
        int col = pos[1];
        int num = mat[row][col];
        int curStep = step[row][col];
//        row
        int[] line = mat[row];
        for (int i = 0; i < line.length; i++) {
            if(line[i] > num){
                step[row][i] = Math.max(step[row][i], curStep + 1);
                max = Math.max(step[row][i], max);
            }
        }

//        col
        for (int i = 0; i < mat.length; i++) {
            if(mat[i][col] > num){
                step[i][col] = Math.max(step[i][col], curStep + 1);
                max = Math.max(step[i][col], max);
            }
        }

        return max;
    }

    /**
     * top选手，参考一下
     */
    public int maxIncreasingCellsAnswer(int[][] mat) {
        TreeMap<Integer, ArrayList<int[]>> map = new TreeMap<>();
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                map.computeIfAbsent(mat[i][j], t -> new ArrayList<>()).add(new int[] { i, j, 0 });
            }
        }
//        TODO
//         这里就是差距了
//         我原本超时的块依然会逐个遍历整个矩阵，顶不住最后的暴力case
//         我原本的时间复杂度 O((mat.length * mat[0].length)^2) 简化一下就是 O(n^2)
//         这里的时间复杂度 O(mat.length * mat[0].length * 2) 简化一下就是 O(n)
//         思路上没啥区别，就时间复杂差距太大
//         还是辅助结构没设置好，只需要遍历 行最大值 与 列最大值就好了，而我却是针对每个元素都遍历 行和列
        int row[] = new int[mat.length], col[] = new int[mat[0].length], max = 0;
        for (Map.Entry<Integer, ArrayList<int[]>> entry : map.entrySet()) {
            for (int[] i : entry.getValue()) {
                max = Math.max(max, i[2] = Math.max(row[i[0]], col[i[1]]) + 1);
            }
            for (int[] i : entry.getValue()) {
                row[i[0]] = Math.max(row[i[0]], i[2]);
                col[i[1]] = Math.max(col[i[1]], i[2]);
            }
        }
        return max;
    }

    /**
     * 以及，使用优先队列和 treeMap 都可以，只是优先队列看起来会更加复杂一点，
     * @param mat
     * @return
     */
    public int maxIncreasingCellsAnswer1(int[][] mat) {
        // 最大长度
        // 全局排序，每行每列再维护优先队列
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> mat[a[0]][a[1]]));
        int row = mat.length;
        int col = mat[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                priorityQueue.add(new int[] {i, j});
            }
        }
        int[] hangMax = new int[row];
        int[] lieMax = new int[col];
        while (!priorityQueue.isEmpty()) {
            int[] poll = priorityQueue.poll();
            int target = mat[poll[0]][poll[1]];
            List<int[]> list = new ArrayList<>();
            list.add(poll);
            while (!priorityQueue.isEmpty() && target == mat[priorityQueue.peek()[0]][priorityQueue.peek()[1]]) {
                list.add(priorityQueue.poll());
            }
            List<Integer> res = new ArrayList<>();
            for (int[] p : list) {
                // 每个p判断对应行列的最大值
                int max = Math.max(hangMax[p[0]], lieMax[p[1]]) + 1;
                res.add(max);
            }
            for (int i = 0; i < res.size(); i++) {
                int[] p = list.get(i);
                hangMax[p[0]] = Math.max(hangMax[p[0]], res.get(i));
                lieMax[p[1]] = Math.max(lieMax[p[1]], res.get(i));
            }
        }
        int ans = 0;
        for (int max : hangMax) {
            ans = Math.max(ans, max);
        }
        for (int max : lieMax) {
            ans = Math.max(ans, max);
        }
        return ans;
    }

    public static void main(String[] args) {
        final Solution demo = new Solution();

//        error，一个未知case，应该是边界条件
//        超出时间限制，优化一下算法
        System.out.println(demo.maxIncreasingCells2(Common.string2IntIntArr("[[39,-7,48,-13,-23,34,34,13,23,-14,-49,24,34,1,19,47,-36,29,-1,1,-50,42,27,11,-5,-37,20,38,43,3,-23,-41,22]]")));

    }
}
