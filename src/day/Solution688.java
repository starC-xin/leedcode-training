package day;

/**
 * 2022/2/17
 * 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * <p>
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 * <p>
 * 每次骑士要移动时，它都会随机从8种可能的移动中选择一种(即使棋子会离开棋盘)，然后移动到那里。
 * <p>
 * 骑士继续移动，直到它走了 k 步或离开了棋盘。
 * <p>
 * 返回 骑士在棋盘停止移动后仍留在棋盘上的概率 。
 * <p>
 * 示例 1：
 * 输入: n = 3, k = 2, row = 0, column = 0
 * 输出: 0.0625
 * 解释: 有两步(到(1,2)，(2,1))可以让骑士留在棋盘上。
 * 在每一个位置上，也有两种移动可以让骑士留在棋盘上。
 * 骑士留在棋盘上的总概率是0.0625。
 * <p>
 * 示例 2：
 * 输入: n = 1, k = 0, row = 0, column = 0
 * 输出: 1.00000
 *  
 * <p>
 * 提示:
 * 1 <= n <= 25
 * 0 <= k <= 100
 * 0 <= row, column <= n
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/knight-probability-in-chessboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution688 {
    /**
     * TODO 题都读不懂，看起来多半是数学的概率论和排列组合
     * 标签提示是动态规划
     * 也许可以通过暴力求解，深度遍历，离开棋盘和走完了预定的步数为回溯条件
     * 直接使用深度遍历暴力求解
     * 超时了，dfs 行不通的，笨蛋
     *
     * TODO 看了下官方题解，推测了一下官方对于总次数的定义
     *      无法理解为什么总得次数是 8^k 次方
     *      既然总情况数是这样，那么中文描述就不应该是 “直到它走了 k 步或离开了棋盘。”
     *      而应该是 “直到它走了k步，即使中途离开了棋盘也要走下去，并将中途离开了棋盘的情况也归为离开棋盘，
     *               即使最终它回到了棋盘内，也要被定义为未在棋盘中”
     *      不这么解释的话，理论上中途离开棋盘，这一条排列就已经终止了，后续步数直接作废，那就根本不存在8^k种情况
     *      无法理解，官方在此的定义，此题跳过不再做
     */
    public double knightProbability(int n, int k, int row, int column) {
        if (k <= 0) {
            return 1;
        }
        if (n < 2) {
            return 0;
        }
        int[] arr = {0, 0};
        dfs(n, k, row, column, arr);
        return 1.0 * arr[0] / Math.pow(8, k);
    }

    /**
     * 深度遍历，暴力破解
     *
     * @param n      棋盘大小
     * @param curK   当前剩余步数
     * @param row    当前所在行
     * @param column 当前所在列
     * @param arr    暂存跳出棋盘或试用完了所有步数
     *               第一位为留在棋盘的组合
     *               第二位为跳出棋盘的组合和留在棋盘组合的总和
     */
    private void dfs(int n, int curK, int row, int column, int[] arr) {
//        设定跳出条件，优先判定是否位于棋盘外，其次判定是否消耗完步数
        if (row < 0 || column < 0 || row >= n || column >= n) {
//            arr[1] ++;
            return;
        }
        if (curK <= 0) {
            arr[0]++;
//            arr[1] ++;
            return;
        }
//        从最左边开始，顺时针遍历所有方向
        dfs(n, curK - 1, row - 1, column - 2, arr);
        dfs(n, curK - 1, row - 2, column - 1, arr);
        dfs(n, curK - 1, row - 2, column + 1, arr);
        dfs(n, curK - 1, row - 1, column + 2, arr);
        dfs(n, curK - 1, row + 1, column + 2, arr);
        dfs(n, curK - 1, row + 2, column + 1, arr);
        dfs(n, curK - 1, row + 2, column - 1, arr);
        dfs(n, curK - 1, row + 1, column - 2, arr);
//        遍历完毕
    }

    /**
     * 8 30 6 4 超时，意料之中
     */
    public static void main(String[] args) {
        System.out.println(new Solution688().knightProbability(3, 3, 0, 2));
    }

    /**
     * TODO 官方题解，看了半天没太懂，但貌似使用到了矩阵计算
     *
     */
    static int[][] dirs = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
    public double knightProbability1(int n, int k, int row, int column) {
        double[][][] dp = new double[k + 1][n][n];
        for (int step = 0; step <= k; step++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (step == 0) {
                        dp[step][i][j] = 1;
                    } else {
                        for (int[] dir : dirs) {
                            int ni = i + dir[0], nj = j + dir[1];
                            if (ni >= 0 && ni < n && nj >= 0 && nj < n) {
                                dp[step][i][j] += dp[step - 1][ni][nj] / 8;
                            }
                        }
                    }
                }
            }
        }
        return dp[k][row][column];
    }

    /**
     * 来自评论区一个大佬
     * TODO 耗时 6ms
     */
    int d[][] = {{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};
    public double knightProbability2(int n, int k, int row, int column) {
        double[][] pos = new double[n][n];
        pos[row][column] = 1;
        for (int i = 0; i < k; i++) {
            double nextPos[][] = new double[n][n];
            for (int j = 0; j < n; j++) {
                for (int l = 0; l < n; l++) {
                    for (int p = 0; p < 8; p++) {
                        int x = j + d[p][0], y = l + d[p][1];
                        if (x >= 0 && x < n && y >= 0 && y < n) {
                            nextPos[j][l] += pos[x][y] / 8;
                        }
                    }
                }
            }
            pos = nextPos;
        }
        double ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += pos[i][j];
            }
        }
        return ans;
    }
}
