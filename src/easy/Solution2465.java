package easy;

import utils.Common;

import java.util.HashSet;

/**
 * 2023/6/4
 * 2465. 不同的平均值数目
 * 给你一个下标从 0 开始长度为 偶数 的整数数组 nums 。
 *
 * 只要 nums 不是 空数组，你就重复执行以下步骤：
 *
 * 找到 nums 中的最小值，并删除它。
 * 找到 nums 中的最大值，并删除它。
 * 计算删除两数的平均值。
 * 两数 a 和 b 的 平均值 为 (a + b) / 2 。
 *
 * 比方说，2 和 3 的平均值是 (2 + 3) / 2 = 2.5 。
 * 返回上述过程能得到的 不同 平均值的数目。
 *
 * 注意 ，如果最小值或者最大值有重复元素，可以删除任意一个。
 *
 * @author x.z
 */
public class Solution2465 {
    public int distinctAverages(int[] nums) {
        int count = 0;
        HashSet<Double> set = new HashSet<>();
        while(count < nums.length){
            int minI = -1;
            int maxI = -1;
            int min = 101;
            int max = -1;
            for (int i = 0; i < nums.length; i++) {
                if(nums[i] == -1){
                    continue;
                }
                if(nums[i] < min){
                    minI = i;
                    min = nums[i];
                }
                if(nums[i] >= max){
                    maxI = i;
                    max = nums[i];
                }
            }
            count += 2;
            set.add((double) (nums[minI] + nums[maxI]) / 2);
            nums[minI] = -1;
            nums[maxI] = -1;
        }
        return set.size();
    }

    public static void main(String[] args) {
        final Solution2465 demo = new Solution2465();

        System.out.println(demo.distinctAverages(Common.string2IntArr("[1,1,1,1,1,1]")));
    }
}
