package easy;

/**
 * 2023/6/25
 *
 * @author x.z
 */
public class Solution367 {
    public boolean isPerfectSquare(int num) {
        if(1 == num){
            return true;
        }
        int edge = num >> 1;
        int left = 1;
        int right = edge;
        while(left < right){
            int mid = left + (right - left + 1) / 2;
            final double sqrt = num * 1.0 / mid;
            if(sqrt == mid){
                return true;
            }else{
                if(sqrt > mid){
                    left = mid;
                }else{
                    right = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * 更加严谨的方式
     * 避开了浮点数的精度问题
     */
    public boolean isPerfectSquareAnswer(int num) {
        int low = 1;
        int high = num;
        while (low <= high) {
            int mid = low + (high - low) / 2;
//            这里很重要，直接取整数
            int t = num / mid;
            if (t == mid) {
//                然后判断余数，避开了浮点数的精度问题
                if (num%mid == 0) return true;
                low = mid + 1;
            } else if (t < mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        final Solution367 demo = new Solution367();

        System.out.println(demo.isPerfectSquare(808201));
        System.out.println(demo.isPerfectSquare(120));
        System.out.println(demo.isPerfectSquare(121));
        System.out.println(demo.isPerfectSquare(4));
    }
}
