package easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2023/6/23
 * 349. 两个数组的交集
 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
 *
 * @author x.z
 */
public class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> cache = new HashSet<>();
        for (int j : nums1) {
            cache.add(j);
        }
        Set<Integer> resList = new HashSet<>();
        for (int j : nums2) {
            if (cache.contains(j)) {
                resList.add(j);
            }
        }
        int[] res = new int[resList.size()];
        int i = 0;
        for (Integer item : resList) {
            res[i++] = item;
        }
        return res;
    }

    /**
     * 数据范围较小，所以有此官方题解
     */
    static class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            int[] temp = new int[1000];

            for (int i = 0; i < nums1.length; i ++) {
                temp[nums1[i]] = 1;
            }

            int[] result = new int[1000];
            int count = 0;
            for (int i = 0; i < nums2.length; i++) {
                int j = temp[nums2[i]];
                if (j == 1) {
                    result[count] = nums2[i];
                    count ++;
                    temp[nums2[i]] = 0;
                }
            }

            int[] res = new int[count];
            for(int i = 0; i < count; i ++) {
                res[i] = result[i];
            }

            return res;
        }
    }
}
