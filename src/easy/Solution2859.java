package easy;

import utils.Common;

import java.util.List;

/**
 * 2024/01/25
 * 2859. 计算 K 置位下标对应元素的和
 * 简单
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的整数数组 nums 和一个整数 k 。
 *
 * 请你用整数形式返回 nums 中的特定元素之 和 ，这些特定元素满足：其对应下标的二进制表示中恰存在 k 个置位。
 *
 * 整数的二进制表示中的 1 就是这个整数的 置位 。
 *
 * 例如，21 的二进制表示为 10101 ，其中有 3 个置位。
 * @author x.z
 */
public class Solution2859 {
    public int sumIndicesWithKSetBits(List<Integer> nums, int k) {
        int res = 0;
        for (int i = 0; i < nums.size(); i++) {
            int tmpK = 0;
            int tmpI = i;
            while(tmpI > 0){
                tmpK += tmpI % 2;
                tmpI >>= 1;

                if(tmpK == k && tmpI > 0){
                    tmpK = -1;
                    break;
                }
            }
            if(tmpK == k){
                res += nums.get(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        final Solution2859 demo = new Solution2859();

        System.out.println(demo.sumIndicesWithKSetBits(Common.string2IntList("[5,10,1,5,2]"), 1));
    }
}
