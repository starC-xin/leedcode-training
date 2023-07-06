package easy;

/**
 * 2023/6/24
 *
 * @author x.z
 */
public class Solution342 {
    public boolean isPowerOfFour(int n) {
        while(n % 4 == 0){
            n /= 4;
        }
        return n == 1;
    }

    /**
     * 呐，这，就是纯粹的数学了
     */
    static class Solution {
        public boolean isPowerOfFour(int n) {
            return n > 0 && (n & (n - 1)) == 0 && n % 3 == 1;
        }
    }
}
