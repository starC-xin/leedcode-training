package easy;

/**
 * 2023/6/26
 *
 * @author x.z
 */
public class Solution374 {
    int answer = 6;
    private int guess(int n){
        return Integer.compare(answer, n);
    }

    public int guessNumber(int n) {
        int left = 1, right = n;
        while(left <= right){
            n = left + (right - left) / 2;
            int tmp = guess(n);
            if(tmp == 0){
                return n;
            }else if(tmp == 1){
                left = n + 1;
            }else{
                right = n - 1;
            }
        }
        return -1;
    }

    public int guessNumberAnswer(int n) {
        int left = 1, right = n;
        while (left < right) { // 循环直至区间左右端点相同
            int mid = left + (right - left) / 2; // 防止计算时溢出
            if (guess(mid) <= 0) {
                right = mid; // 答案在区间 [left, mid] 中
            } else {
                left = mid + 1; // 答案在区间 [mid+1, right] 中
            }
        }
        // 此时有 left == right，区间缩为一个点，即为答案
        return left;
    }

    public static void main(String[] args) {
        final Solution374 demo = new Solution374();

        System.out.println(demo.guessNumber(10));
    }
}
