package easy;

import java.util.Arrays;

/**
 * 2023/6/24
 *
 * @author x.z
 */
public class Solution338 {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            res[i] = calCount(i);
        }
        return res;
    }

    private int calCount(int n){
        int count = 0;
        while(n > 0){
            if((n & 1) == 1){
                count ++;
            }
            n >>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        final Solution338 demo = new Solution338();
        System.out.println(Arrays.toString(demo.countBits(32)));
    }
}
