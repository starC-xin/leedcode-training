package media.other;

import utils.Common;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2023/6/18
 * 1254. 统计封闭岛屿的数目
 * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
 *
 * 请返回 封闭岛屿 的数目。
 * @author x.z
 */
public class Solution1254 {
    /**
     * 暴力思路，逐一遍历，另外，记录已遍历位置
     */
    public int closedIsland(int[][] grid) {
        int sum = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1){
                    continue;
                }
                if(visited[i][j]){
                    continue;
                }
                sum += visit(grid, i, j, visited) ? 1 : 0;
            }
        }
        return sum;
    }

    private boolean visit(int[][] grid, int beginI, int beginJ, boolean[][] visited) {
        boolean flag = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{beginI, beginJ});
        while(!queue.isEmpty()){
            final int[] poll = queue.poll();
            if(poll[0] == 0 || poll[1] == 0 || poll[0] == grid.length - 1 || poll[1] == grid[0].length - 1){
                flag = false;
            }
            visited[poll[0]][poll[1]] = true;
            int top = poll[0] - 1;
            int down = poll[0] + 1;
            int left = poll[1] - 1;
            int right = poll[1] + 1;
            if(top >= 0 && grid[top][poll[1]] == 0 && !visited[top][poll[1]]){
                queue.offer(new int[]{top, poll[1]});
            }
            if(down < grid.length && grid[down][poll[1]] == 0 && !visited[down][poll[1]]){
                queue.offer(new int[]{down, poll[1]});
            }
            if(left >= 0 && grid[poll[0]][left] == 0 && !visited[poll[0]][left]){
                queue.offer(new int[]{poll[0], left});
            }
            if(right < grid[0].length && grid[poll[0]][right] == 0 && !visited[poll[0]][right]){
                queue.offer(new int[]{poll[0], right});
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        final Solution1254 demo = new Solution1254();
        System.out.println(demo.closedIsland(Common.string2IntIntArr("[[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]")));
    }
}
