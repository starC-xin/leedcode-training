package easy;

import utils.Common;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 2023/7/1
 *
 * @author x.z
 */
public class Solution1 {
    /**
     * 直接暴力吧
     * 今天不想动脑子
     */
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if(j == i){
                    continue;
                }
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    /**
     * TODO 很久很久之前我自己提交的代码
     *      好像还不错
     */
    public int[] twoSum0(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i ++){
            int div = target - nums[i];
            if(map.containsKey(div)){
                result[0] = i;
                result[1] = map.get(div);
                break;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    public static void main(String[] args) {
        final Solution1 demo = new Solution1();

        System.out.println(Arrays.toString(demo.twoSum(Common.string2IntArr("[2,7,11,15]"), 9)));
    }
}
