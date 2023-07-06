package easy;

/**
 * 2023/6/22
 * 258. 各位相加
 * 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 * @author x.z
 */
public class Solution258 {
    public int addDigits(int num) {
        while(num / 10 > 0){
            num = addDigits0(num);
        }
        return num;
    }

    private int addDigits0(int num){
        int sum = 0;
        while(num > 0){
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    /**
     * 官方示例
     * 感觉，很优雅
     * 但是，看不懂
     */
    static class Solution {
        public int addDigits(int num) {
            if (num<=9)
                return num;
            if (num%9==0)
                return 9;
            int n = num%9;
            return n;
        }
    }
}
