package easy;

/**
 * 2023/8/9
 *
 * @author x.z
 */
public class Solution1281 {

    /**
     * 水题罢了
     */
    public int subtractProductAndSum(int n) {
        int ans1 = 1, ans2 = 0;
        while(n > 0){
            int tmp = n % 10;
            n /= 10;
            ans1 *= tmp;
            ans2 += tmp;
        }
        return ans1 - ans2;
    }
}
