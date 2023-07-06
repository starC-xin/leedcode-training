package day;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 2022/1/19
 *
 * 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,1], k = 3
 * 输出：true
 *
 * 示例 2：
 * 输入：nums = [1,0,1,1], k = 1
 * 输出：true
 *
 * 示例 3：
 * 输入：nums = [1,2,3,1,2,3], k = 2
 * 输出：false
 *  
 * 提示：
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 0 <= k <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author x.c
 */
public class Solution219 {
    /**
     * 尝试优化，优化关键字：哈希、滑动窗口
     *
     * 思路：滑动窗口
     *      使用set实现，逐个遍历每一个窗口
     *
     * 已完成，耗时大幅下降，时间复杂度为 O(n)
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> window = new HashSet<>();

        for(int i = 0; i < nums.length; i++){
            if(window.size() > k){
                window.remove(nums[i - k - 1]);
            }
            if(window.contains(nums[i])){
                return true;
            }
            window.add(nums[i]);
        }
        return false;
    }

    /**
     * 尝试暴力破解
     * 耗时max：1535ms
     */
    public boolean containsNearbyDuplicate1(int[] nums, int k) {
        for(int i = 0; i < nums.length - 1; i++){
            for(int j = i + 1; j < nums.length && j - i <= k; j ++){
                if(nums[i] == nums[j]){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * [1,2,3,1,2,3]
     * 2
     * @param args
     */
    public static void main(String[] args){
        System.out.println(new Solution219().containsNearbyDuplicate(new int[]{1,2,3,1,2,3}, 2));
    }
}
