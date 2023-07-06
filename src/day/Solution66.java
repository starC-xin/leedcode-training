package day;

/**
 * 2021/10/21
 *
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/plus-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution66 {
    public int[] plusOne(int[] digits) {
        digits[digits.length - 1] ++;
        for(int i = digits.length - 1; i > 0; i--){
            if(digits[i] >= 10){
                digits[i] = 0;
                digits[i - 1] ++;
            }
        }
        if(digits[0] >= 10){
            int[] old = digits;
            digits = new int[old.length + 1];
            old[0] = 0;
            digits[0] = 1;
            System.arraycopy(old, 0, digits, 1, old.length);
        }
        return digits;
    }
}
