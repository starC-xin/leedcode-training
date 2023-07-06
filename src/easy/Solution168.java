package easy;

/**
 * 2023/5/23
 *
 * 168. Excel表列名称
 * 给你一个整数 columnNumber ，返回它在 Excel 表中相对应的列名称。
 *
 * 例如：
 *
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 * ...
 *
 */
public class Solution168 {
    /**
     * TODO 此题可以简单理解为一个进制转换
     *      即，如何快速的将十进制转为26进制
     *      即，存在如下式子：n = 1*26^0 + 1*26^1... + 1*26^x
     * @param columnNumber
     * @return
     */
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while(columnNumber > 0){
            final int tmp = columnNumber % 26;
            sb.append((char) (tmp == 0 ? 'Z' : tmp - 1 + 'A'));
            columnNumber /= 26;
            if(tmp == 0){
                columnNumber -= 1;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        final Solution168 demo = new Solution168();
        int n = 26;
        System.out.println(demo.convertToTitle(n));
    }
}
