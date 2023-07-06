package easy;

/**
 * 2023/6/28
 *
 * @author x.z
 */
public class Solution476 {
    public int findComplement(int num) {
        int i = 0;
        int res = 0;
        while(num > 0){
            final int tmp = num & 1;
            if(tmp == 0){
                res += Math.pow(2, i);
            }
            num >>= 1;
            i ++;
        }
        return res;
    }

    public static void main(String[] args) {
        final Solution476 demo = new Solution476();
        System.out.println(demo.findComplement(5));
    }
}
