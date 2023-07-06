package easy;

/**
 * 2023/6/26
 *
 * @author x.z
 */
public class Solution2485 {

    public int pivotInteger(int n) {
        if(1 == n){
            return 1;
        }
        final int total = n * (n + 1) / 2;
        for (int i = 1; i < n; i++) {
            final int tmp = i * i;
            if(tmp == total){
                return i;
            }else if(tmp > total){
                break;
            }
        }
        return -1;
    }

    /**
     * 我觉得我等差求和就挺好的
     * 结果官解有更逆天的
     * 我甚至都没有明白发生了什么
     *
     */
    static class Solution {
        public int pivotInteger(int n) {
            int m = n * (n + 1) / 2;
            int x = (int) Math.sqrt(m);
            return x * x == m ? x : -1;
        }
    }

    public static void main(String[] args) {
        final Solution2485 demo = new Solution2485();

        System.out.println(demo.pivotInteger(8));
    }
}
