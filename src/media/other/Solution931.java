package media.other;

import utils.Common;

/**
 * 2023/7/13
 *
 * @author x.z
 */
public class Solution931 {
    /**
     * 动规水题罢了
     * 不过二维dp应该是可以用滚动数组来替换的
     * 这样就只有一维数组了
     */
    public int minFallingPathSum(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        System.arraycopy(matrix[0], 0, dp[0], 0, matrix[0].length);
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                cal(i, j, dp, matrix);
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < dp[0].length; i++) {
            res = Math.min(res, dp[dp.length - 1][i]);
        }
        return res;
    }

    int[][] dirs = new int[][]{
            {-1, -1}, {-1, 0}, {-1, 1}
    };
    private void cal(int row, int col, int[][] dp, int[][] matrix){
        for (int[] dir : dirs) {
            int tmpRow = row + dir[0];
            int tmpCol = col + dir[1];
            if (tmpRow < 0 || tmpRow >= matrix.length || tmpCol < 0 || tmpCol >= matrix[0].length) {
                continue;
            }
            dp[row][col] = dp[row][col] == 0
                    ? dp[tmpRow][tmpCol] + matrix[row][col]
                    : Math.min(dp[row][col], dp[tmpRow][tmpCol] + matrix[row][col]);
        }
    }

    public static void main(String[] args) {
        final Solution931 demo = new Solution931();

        System.out.println(demo.minFallingPathSum(Common.string2IntIntArr("[[2,1,3],[6,5,4],[7,8,9]]")));
    }
}
