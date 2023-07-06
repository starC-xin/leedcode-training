package day;

import utils.Common;

/**
 * 2022/3/7
 * 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 *
 * 示例 1:
 * 输入: num = 100
 * 输出: "202"
 * 示例 2:
 * 输入: num = -7
 * 输出: "-10"
 *  
 * 提示：
 * -10^7 <= num <= 10^7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/base-7
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author x.c
 */
public class Solution504 {
    /**
     * TODO 直接使用短除法，迭代的将得到的数除以目标进制数
     *      余数按照倒序排列，直到除到最后结果为 0 为止
     *      过了，虽然耗时 2ms 只击败了 18.07%，但思路上已经没有改善的余地了
     *      前面那些比较快的也采用了和我一模一样的思路，但可能是因为之前案例的
     *      原因，使得他们耗时很少，更有甚者直接
     */
    public String convertToBase7(int num) {
        if(num < 7 && num > -7){
            return num + "";
        }
        boolean flag = num < 0;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder();
        while(num > 0){
            sb.append(num % 7);
            num /= 7;
        }
        return (flag ? '-' : "") + sb.reverse().toString();
    }

    public static void main(String[] args){
        Common.run(o -> {
            System.out.println(new Solution504().convertToBase7((int)Math.pow(10, 7)));
        });
    }
}
