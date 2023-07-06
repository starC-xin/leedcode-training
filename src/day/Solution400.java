package day;

/**
 * 2021/11/30
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位数字。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：3
 *
 * 示例 2：
 * 输入：n = 11
 * 输出：0
 * 解释：第 11 位数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是 0 ，它是 10 的一部分。
 *  
 *
 * 提示：
 * 1 <= n <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nth-digit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author x.c
 */
public class Solution400 {

    public static void main(String[] args){
        System.out.println(new Solution400().findNthDigit(1024));
    }

    /**
     * 完美，耗时 几乎0ms，但有类型限制，数字较大时可能溢出
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        if(n < 10){
            return n;
        }
        int base = 9;
        int curMax = base * 10;
        int i = 2;
        n -= base;
        int num = n / i;
        while (num > curMax) {
            base = base * 10 + 9;
            n -= curMax * i;
            num = n / ++i;
            curMax *= 10;
        }
        int temp = n % i;
        if(temp != 0){
            num ++;
        }else{
            return (base + num) % 10;
        }
        char[] result = String.valueOf(base + num).toCharArray();
        return result[temp - 1] - '0';
    }
}
