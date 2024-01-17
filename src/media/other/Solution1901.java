package media.other;

import utils.Common;

import java.util.Arrays;

/**
 * 2023/12/19
 *
 * 1901. 寻找峰值 II
 * 提示
 * 中等
 * 123
 * 相关企业
 * 一个 2D 网格中的 峰值 是指那些 严格大于 其相邻格子(上、下、左、右)的元素。
 *
 * 给你一个 从 0 开始编号 的 m x n 矩阵 mat ，其中任意两个相邻格子的值都 不相同 。找出 任意一个 峰值 mat[i][j] 并 返回其位置 [i,j] 。
 *
 * 你可以假设整个矩阵周边环绕着一圈值为 -1 的格子。
 *
 * 要求必须写出时间复杂度为 O(m log(n)) 或 O(n log(m)) 的算法
 * @author x.z
 */
public class Solution1901 {
    public int[] findPeakGrid(int[][] mat) {
        int[] max = {0, 0};
        int[] tmpMax = isMax(max[0], max[1], mat);
        while(tmpMax[0] != -1){
            max = tmpMax;
            tmpMax = isMax(max[0], max[1], mat);
        }
        return max;
    }

    private static final int[][] dur = {
            {-1, 0}, {0, -1}, {1, 0}, {0, 1}
    };
    private int[] isMax(int x, int y, int[][] mat){
        int tmp = mat[x][y];
        int[] result = {-1, -1};
        for (int[] ints : dur) {
            int tmpX = x + ints[0];
            int tmpY = y + ints[1];
            if((tmpX < 0 || tmpX >= mat.length) || (tmpY < 0 || tmpY >= mat[x].length )){
                continue;
            }
            if(mat[tmpX][tmpY] > tmp){
                tmp = mat[tmpX][tmpY];
                result[0] = tmpX;
                result[1] = tmpY;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        final Solution1901 solution = new Solution1901();

//        System.out.println(Arrays.toString(solution.findPeakGrid(Common.string2IntIntArr("[[10,20,15],[21,30,14],[7,16,32]]"))));

//        0 3  [[41,8,2,48,18],[16,15,9,7,44],[48,35,6,38,28],[3,2,14,15,33],[39,36,13,46,42]]
        System.out.println(Arrays.toString(solution.findPeakGrid(Common.string2IntIntArr("[[41,8,2,48,18],[16,15,9,7,44],[48,35,6,38,28],[3,2,14,15,33],[39,36,13,46,42]]"))));
    }
}
