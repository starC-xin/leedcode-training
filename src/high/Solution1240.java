package high;

/**
 * 2023/6/8
 * 1240. 铺瓷砖
 * 你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。
 *
 * 房子的客厅大小为 n x m，为保持极简的风格，需要使用尽可能少的 正方形 瓷砖来铺盖地面。
 *
 * 假设正方形瓷砖的规格不限，边长都是整数。
 *
 * 请你帮设计师计算一下，最少需要用到多少块方形瓷砖？
 *
 * @author x.z
 */
public class Solution1240 {
    /**
     * TODO 可以分解为，n个数的平方的和等于指定面积
     *      且 每个数不得大于指定长宽的短边长
     *      要求以最少个数获得指定面积大小
     *      常使用 dfs 解决一下
     *      不会，看下题解
     */
    public int tilingRectangle(int n, int m) {
        return 0;
    }

    /**
     * 这是答案，暴力递推
     * 利用了边长均为整数的特性，直接以二维矩阵来表示整个面积
     */
    int ans;

    public int tilingRectangleAnswer(int n, int m) {
        ans = Math.max(n, m);
        boolean[][] rect = new boolean[n][m];
        dfs(0, 0, rect, 0);
        return ans;
    }

    public void dfs(int x, int y, boolean[][] rect, int cnt) {
        int n = rect.length, m = rect[0].length;
        if (cnt >= ans) {
            return;
        }
        if (x >= n) {
            ans = cnt;
            return;
        }
        /* 检测下一行 */
        if (y >= m) {
            dfs(x + 1, 0, rect, cnt);
            return;
        }
        /* 如当前已经被覆盖，则直接尝试下一个位置 */
        if (rect[x][y]) {
            dfs(x, y + 1, rect, cnt);
            return;
        }

        for (int k = Math.min(n - x, m - y); k >= 1 && isAvailable(rect, x, y, k); k--) {
            /* 将长度为 k 的正方形区域标记覆盖 */
            fillUp(rect, x, y, k, true);
            /* 跳过 k 个位置开始检测 */
            dfs(x, y + k, rect, cnt + 1);
            fillUp(rect, x, y, k, false);
        }
    }

    public boolean isAvailable(boolean[][] rect, int x, int y, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                if (rect[x + i][y + j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void fillUp(boolean[][] rect, int x, int y, int k, boolean val) {
        for (int i = 0; i < k; i++){
            for (int j = 0; j < k; j++) {
                rect[x + i][y + j] = val;
            }
        }
    }
}
