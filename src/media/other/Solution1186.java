package media.other;

/**
 * 2023/6/27
 *
 * @author x.z
 */
public class Solution1186 {
    /**
     * TODO 官解
     *      又是看官解的一天，什么时候我才能学会自己写状态转换方程啊
     */
    public int maximumSum(int[] arr) {
        int dp0 = arr[0], dp1 = 0, res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            dp1 = Math.max(dp0, dp1 + arr[i]);
            dp0 = Math.max(dp0, 0) + arr[i];
            res = Math.max(res, Math.max(dp0, dp1));
        }
        return res;
    }
}
