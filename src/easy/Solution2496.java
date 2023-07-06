package easy;

/**
 * 2023/6/23
 * 2496. 数组中字符串的最大值
 * 一个由字母和数字组成的字符串的 值 定义如下：
 *
 * 如果字符串 只 包含数字，那么值为该字符串在 10 进制下的所表示的数字。
 * 否则，值为字符串的 长度 。
 * 给你一个字符串数组 strs ，每个字符串都只由字母和数字组成，请你返回 strs 中字符串的 最大值 。
 * @author x.z
 */
public class Solution2496 {
    public int maximumValue(String[] strs) {
        int res = 0;
        for (String str : strs) {
            try{
                res = Math.max(Integer.parseInt(str), res);
            }catch (NumberFormatException e){
                res = Math.max(str.length(), res);
            }
        }
        return res;
    }

    /**
     * 优化一下
     * 依赖 try-catch 以及 Integer.parseInt 还是太慢了
     */
    public int maximumValue1(String[] strs) {
        int res = 0;
        for (String str : strs) {
            int tmp = 0;
            boolean flag = false;
            for (int i = 0; i < str.length(); i++) {
                final char c = str.charAt(i);
                if(c >= '0' && c <= '9'){
                    tmp *= 10;
                    tmp += c - '0';
                }else{
                    flag = true;
                    break;
                }
            }
            tmp = flag ? str.length() : tmp;
            res = Math.max(res, tmp);
        }
        return res;
    }
}
