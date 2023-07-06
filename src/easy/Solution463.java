package easy;

import utils.Common;

/**
 * 2023/6/28
 *
 * @author x.z
 */
public class Solution463 {
    public int islandPerimeter(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1){
                    return dfs(grid, i, j, new boolean[grid.length][grid[i].length]);
                }
            }
        }
        return 0;
    }

    final int[][] dir = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };
    private int dfs(int[][] grid, int x, int y, boolean[][] visited){
        if(x < 0 || x >= grid.length || y < 0 || y >= grid[0].length){
            return 1;
        }
        if(visited[x][y]){
            return 0;
        }
        if(grid[x][y] == 0){
            return 1;
        }
        int sum = 0;
        visited[x][y] = true;
        for (int[] ints : dir) {
            sum += dfs(grid, x + ints[0], y + ints[1], visited);
        }
        return sum;
    }

    /**
     * 官解
     */
    static class Solution {
        static int[] dx = {0, 1, 0, -1};
        static int[] dy = {1, 0, -1, 0};

        public int islandPerimeter(int[][] grid) {
            int n = grid.length, m = grid[0].length;
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < m; ++j) {
                    if (grid[i][j] == 1) {
                        int cnt = 0;
                        for (int k = 0; k < 4; ++k) {
                            int tx = i + dx[k];
                            int ty = j + dy[k];
                            if (tx < 0 || tx >= n || ty < 0 || ty >= m || grid[tx][ty] == 0) {
                                cnt += 1;
                            }
                        }
                        ans += cnt;
                    }
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        final Solution463 demo = new Solution463();

        System.out.println(demo.islandPerimeter(Common.string2IntIntArr("[[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]")));
    }
}
