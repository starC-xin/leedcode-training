package easy;

/**
 * 2023/6/22
 * 263. 丑数
 * 丑数 就是只包含质因数 2、3 和 5 的正整数。
 *
 * 给你一个整数 n ，请你判断 n 是否为 丑数 。如果是，返回 true ；否则，返回 false 。
 * @author x.z
 */
public class Solution263 {
    /**
     * 会超时
     */
    public boolean isUgly(int n) {
        int len = Math.abs(n / 2);
        for (int i = 6; i <= len; i++) {
            if(n % i == 0){
                if(isNe(i)){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isNe(int n){
        for (int i = 2; i < Math.sqrt(n); i++) {
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 注意读题 ”正整数“
     */
    public boolean isUgly0(int n){
        if(n <= 0){
            return false;
        }
        while(n % 2 == 0){
            n /= 2;
        }
        while(n % 3 == 0){
            n /= 3;
        }
        while(n % 5 == 0){
            n /= 5;
        }
        return n == 1;
    }

    public static void main(String[] args) {
        final Solution263 demo = new Solution263();

        System.out.println(demo.isUgly0(-2147483648));
    }
}
