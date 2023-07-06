package day;

import utils.Common;

/**
 * 2022/3/2
 *
 * 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 *
 * “最近的”定义为两个整数差的绝对值最小。
 *
 *  
 *
 * 示例 1:
 * 输入: n = "123"
 * 输出: "121"
 *
 * 示例 2:
 * 输入: n = "1"
 * 输出: "0"
 * 解释: 0 和 2是最近的回文，但我们返回最小的，也就是 0。
 *  
 * 提示:
 * 1 <= n.length <= 18
 * n 只由数字组成
 * n 不含前导 0
 * n 代表在 [1, 1018 - 1] 范围内的整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-the-closest-palindrome
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution564 {
    /**
     * TODO 暴力解
     *      第一个错误 "2147483647" 这里超出了 int 上限
     *      这个参数超时了 "807045053224792883"  答案 807045053350540708
     *
     * TODO 修正思路，不依赖逐个遍历，而是直接从高位取数，将高位数直接对称复制到低位
     *
     * TODO 看了下题解，没有特别的计算方法，就是纯粹的逻辑和分类排错，我这里的思路已经很接近了
     *      但要接着写下去，最后的分类结果包含四类
     *      1、用原数的前半部分替换后半部分得到的回文整数。
     *      2、用原数的前半部分加一后的结果替换后半部分得到的回文整数。
     *      3、用原数的前半部分减一后的结果替换后半部分得到的回文整数。
     *      4、为防止位数变化导致构造的回文整数错误，因此直接构造 999…999 和 100…001 作为备选答案。
     *
     * TODO 今天的战斗就到这里结束吧
     */
    public String nearestPalindromic(String n) {
//        去除单数
        long num = Long.parseLong(n);
        if(num <= 11){
            return 11 == num ? 9 + "" : num - 1 + "";
        }
//        暴力
//        long min = num - 1;
//        long max = num + 1;
//        while(true){
//            if(isPa(min)){
//                return min + "";
//            }
//            min --;
//            if(isPa(max)){
//                return max + "";
//            }
//            max ++;
//        }
        StringBuilder sb = new StringBuilder();
        char[] chs = n.toCharArray();
        boolean flag = chs.length % 2 == 0;
        int i = 0;
        String temp;
        for(; i < chs.length / 2; i++){
            sb.append(chs[i]);
        }
        if(! flag){
            sb.append(chs[i]);
        }
        temp = sb.toString();
        i --;
        for(; i >= 0; i --){
            sb.append(chs[i]);
        }
//        若得到的新回文与原回文相同
        if(sb.toString().equals(n)){
            sb.delete(0, sb.length());
            temp = (Long.parseLong(temp) - 1) + "";
            sb.append(temp);
            chs = temp.toCharArray();
            i = flag ? temp.length() - 1 : temp.length() - 2;
            for(; i >= 0; i--){
                sb.append(chs[i]);
            }
        }
        return sb.toString();
    }

    private boolean isPa(long num){
        if(num < 10){
            return true;
        }
        if(num % 10 == 0){
            return false;
        }
        char[] chs = (num + "").toCharArray();
        for(int i = 0; i < chs.length / 2; i++){
            if(chs[i] != chs[chs.length - 1 - i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
//        System.out.println(Integer.MAX_VALUE);
//        System.out.println(Math.pow(2, 32));
        Common.run(o->{
            System.out.println(new Solution564().nearestPalindromic("100"));
        });
    }
}
