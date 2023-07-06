package easy;

import java.util.HashMap;

/**
 * 2023/6/5
 * 169. 多数元素
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * @author x.z
 */
public class Solution169 {
    /**
     * 常规思路
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int maxCount = 0;
        for (int num : nums) {
            Integer count = map.getOrDefault(num, 0);
            count ++;
            if(count > maxCount){
                max = num;
                maxCount = count;
            }
            map.put(num, count);
        }
        return max;
    }

    /**
     * 很诡异的思路，但也很神奇
     * TODO 如果一个元素出现次数大于 n/2，那么他出现的次数应该是大于其他元素的总和的
     * @param nums
     * @return
     */
    public int majorityElementAnswer(int[] nums) {
        int count = 0;
        int candidate = 0;
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += candidate == num ? 1 : -1;
        }
        return candidate;
    }
}
