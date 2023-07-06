package media.other;

import utils.Common;

import java.util.Arrays;

/**
 * 2023/6/1
 * 2517. 礼盒的最大甜蜜度
 * 给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。
 *
 * 商店组合 k 类 不同 糖果打包成礼盒出售。礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。
 *
 * 返回礼盒的 最大 甜蜜度。
 *
 * @author x.z
 */
public class Solution2517 {
    /**
     * todo 第一反应就是动规，但是不会写转换方程
     *      尝试写一下dp方程
     *      dp[k] = min(dp[k - 1], dp[k - 2]) ( k>=2)
     * todo 先排序，然后取两端点值，若 k 大于 2，则以此为初始开始选择第三个值
     *      查找方式依据二分查找
     * todo 思绪太模糊了，看看官方题解
     */
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int left = 0, right = price[price.length - 1] - price[0];
        while(left < right){
            int mid = (right + left + 1) / 2;
            if(check(price, k, mid)){
                left = mid;
            }else{
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int[] price, int k, int curMid){
        int tmp = price[0];
        int count = 1;
        for (int i = 1; i < price.length; i++) {
            if(price[i] - tmp >= curMid){
                count ++;
                tmp = price[i];
            }
        }
        return count >= k;
    }

    public static void main(String[] args) {
        final Solution2517 demo = new Solution2517();

        int[] price = Common.string2IntArr("[13,5,1,8,21,2]");
        int k = 3;
        System.out.println(demo.maximumTastiness(price, k));
    }
}
