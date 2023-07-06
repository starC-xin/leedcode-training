package media.math;

public class Solution {
    public int trailingZeroes(int n){
        int temp = 0;
        for(int i = 0; i <= n; i += 5){
            int cur = i;
            while(cur % 5 == 0 && cur != 0){
                cur /= 5;
                temp ++;
            }
        }
        return temp;
    }

    /***
     * 方法详解
     * https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/jie-cheng-hou-de-ling-by-leetcode/#comment
     * @param n
     * @return
     */
    public int trailingZeroes_1(int n){
        int temp = 0;
        while(n > 0){
            n /= 5;
            temp += n;
        }
        return temp;
    }

    public static void main(String[] args){
        Solution test = new Solution();
//        for (int i = 1; i < 16; i++){
//            System.out.println(test.trailingZeroes(i) + " " + solution(i));
//        }
        System.out.println(test.trailingZeroes(1808548329));
        System.out.println(test.trailingZeroes_1(1808548329));
    }

    private static long solution(int var){
        long temp = 1;
        while(var > 0){
            temp *= var--;
        }
        return temp;
    }
}
