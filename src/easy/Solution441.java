package easy;

/**
 * 2023/6/28
 *
 * @author x.z
 */
public class Solution441 {
    public int arrangeCoins(int n) {
        long sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
            if(sum > n){
                return  i - 1;
            }
            if(sum == n){
                return i;
            }
        }
        return 0;
    }

    /**
     * 这，大概就是数学的魅力
     */
    public int arrangeCoinsAnswer(int n) {
        return ((int) Math.sqrt(1 + (long) 8 * n) - 1) / 2;
    }

    public static void main(String[] args) {
        final Solution441 demo = new Solution441();

        System.out.println(demo.arrangeCoins(1));
    }
}
