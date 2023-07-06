package day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2021/10/22
 *
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 *
 * 示例 1：
 * 输入：[3,2,3]
 * 输出：[3]
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：[1]
 *
 * 示例 3：
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 *
 * 提示：
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 *  
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution229 {
    /**
     * TODO 摩尔投票法
     */
    public List<Integer> majorityElement(int[] nums) {
        ArrayList<Integer> result = new ArrayList<>();
        int countX = 0, countY = 0;
        int x = 0, y = 0;

        for(int num : nums){
            if((num == x || countX == 0) && num != y){
                x = num;
                countX ++;
            }else if(num == y || countY == 0){
                y = num;
                countY ++;
            }else{
                countX --;
                countY --;
            }
        }
        countX = countY = 0;
        for(int num : nums){
            if(num == x){
                countX ++;
            }
            if(num == y){
                countY ++;
            }
        }
        if(countX > nums.length / 3){
            result.add(x);
        }
        if(x != y && countY > nums.length / 3){
            result.add(y);
        }
        return result;
    }
}
