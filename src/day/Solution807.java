package day;

/**
 * 2021/12/13
 * 给你一座由 n x n 个街区组成的城市，每个街区都包含一座立方体建筑。给你一个下标从 0 开始的 n x n 整数矩阵 grid ，其中 grid[r][c] 表示坐落于 r 行 c 列的建筑物的 高度 。
 *
 * 城市的 天际线 是从远处观察城市时，所有建筑物形成的外部轮廓。从东、南、西、北四个主要方向观测到的 天际线 可能不同。
 *
 * 我们被允许为 任意数量的建筑物 的高度增加 任意增量（不同建筑物的增量可能不同） 。 高度为 0 的建筑物的高度也可以增加。然而，增加的建筑物高度 不能影响 从任何主要方向观察城市得到的 天际线 。
 *
 * 在 不改变 从任何主要方向观测到的城市 天际线 的前提下，返回建筑物可以增加的 最大高度增量总和 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution807 {
    /**
     * 读题初步思路，分别求出横纵分别存在的最大值
     * 针对一个确切的坐标，则取该坐标所在值与横纵的最大值的较小值的差值
     * 累积这个差值，即为返回值
     *
     * 已过，1ms，java极限耗时
     *
     * @param grid 一个矩阵
     * @return
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int[] row = new int[grid.length];
        int[] col = new int[grid.length];
//        分别获取行列最大值
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                row[i] = Math.max(row[i], grid[i][j]);
                col[j] = Math.max(col[j], grid[i][j]);
            }
        }
//        累积求和
        int count = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                count += Math.min(row[i], col[j]) - grid[i][j];
            }
        }
        return count;
    }
}
