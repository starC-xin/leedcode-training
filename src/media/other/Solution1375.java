package media.other;

import utils.Common;

/**
 * 2023/6/14
 * 1375. 二进制字符串前缀一致的次数
 * 给你一个长度为 n 、下标从 1 开始的二进制字符串，所有位最开始都是 0 。我们会按步翻转该二进制字符串的所有位（即，将 0 变为 1）。
 *
 * 给你一个下标从 1 开始的整数数组 flips ，其中 flips[i] 表示对应下标 i 的位将会在第 i 步翻转。
 *
 * 二进制字符串 前缀一致 需满足：在第 i 步之后，在 闭 区间 [1, i] 内的所有位都是 1 ，而其他位都是 0 。
 *
 * 返回二进制字符串在翻转过程中 前缀一致 的次数。
 * @author x.z
 */
public class Solution1375 {
    public int numTimesAllBlue(int[] flips) {
        boolean[] num = new boolean[flips.length];
        int last = 0;
        int sum = 0;
        for (int i = 0; i < flips.length; i++) {
            num[flips[i] - 1] = !num[flips[i] - 1];
            boolean flag = true;
            while(last <= i){
                if(!num[last]){
                    flag = false;
                    break;
                }
                last ++;
            }
            if(flag){
                sum ++;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        final Solution1375 demo = new Solution1375();

        System.out.println(demo.numTimesAllBlue(Common.string2IntArr("[3,2,4,1,5]")));
    }
}
