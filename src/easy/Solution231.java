package easy;

/**
 * 2023/6/17
 *
 * @author x.z
 */
public class Solution231 {
    public boolean isPowerOfTwo(int n) {
        if(0 == n){
            return false;
        }
        if(1 == n){
            return true;
        }
        while(n % 2 == 0){
            n = n >> 1;
        }
        return 1 == n;
    }
}
