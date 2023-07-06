package media.other;

import utils.Common;

import java.util.Arrays;

/**
 * 2023/6/6
 * 2352. 相等行列对
 * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
 *
 * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
 *
 * @author x.z
 */
public class Solution2352 {
    /**
     * TODO 先尝试暴力解，反正也就是200的矩阵
     *      过了，暴力解可行，但效率极差 O(2 * n^2)
     */
    public int equalPairs(int[][] grid) {
        int n = grid.length;

        String[] row = new String[n];
        String[] col = new String[n];
        Arrays.fill(row, "");
        Arrays.fill(col, "");
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                row[i] += grid[i][j] + " ";
                col[j] += grid[i][j] + " ";
            }
        }

        int count = 0;
        for (String s : row) {
            for (String c : col) {
                if(s.equals(c)){
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final Solution2352 demo = new Solution2352();

        System.out.println(demo.equalPairs(Common.string2IntIntArr("[[3,1,2,2],[1,4,4,4],[2,4,2,2],[2,5,2,2]]")));
    }
}
