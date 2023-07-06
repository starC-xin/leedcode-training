package easy;

import utils.Common;

import java.util.Arrays;

/**
 * 2023/6/24
 *
 * @author x.z
 */
public class Solution303 {

    static class NumArray {
        int[] sort = null;
        int[] preSum = null;

        public NumArray(int[] nums) {
            sort = nums;
            preSum = new int[sort.length];
            preSum[0] = sort[0];
            for (int i = 1; i < sort.length; i++) {
                preSum[i] = preSum[i - 1] + sort[i];
            }
        }

        public int sumRange(int left, int right) {
            return left > 0 ? preSum[right] - preSum[left - 1] : preSum[right];
        }
    }

    public static void main(String[] args) {
        final int[] nums = Common.string2IntArr("[-2,0,3,-5,2,-1]");
        final NumArray demo = new NumArray(nums);
        System.out.println(demo.sumRange(0, 2));
        System.out.println(demo.sumRange(2, 5));
        System.out.println(demo.sumRange(0, 5));
    }
}
