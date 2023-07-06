package easy;

import utils.Common;

import java.util.Arrays;

/**
 * 2023/6/27
 *
 * @author x.z
 */
public class Solution414 {
    /**
     * 耍赖解法
     */
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int no = 1;
        int res = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            if(res != nums[i]){
                res = nums[i];
                no ++;
                if(3 == no){
                    return res;
                }
            }
        }
        return nums[nums.length - 1];
    }

    /**
     * O(n) 解法
     */
    public int thirdMax0(int[] nums) {
        Integer[] tmp = new Integer[3];
        for (int num : nums) {
            sort(tmp, num);
        }
        return tmp[2] != null ? tmp[2] : tmp[0];
    }
    private void sort(Integer[] tmp, int num){
        for (int i = 0; i < tmp.length; i++) {
            if(tmp[i] == null){
                tmp[i] = num;
                break;
            }else{
                if(tmp[i] < num){
                    int sw = tmp[i];
                    tmp[i] = num;
                    num = sw;
                }else if (tmp[i] == num){
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        final Solution414 demo = new Solution414();

        System.out.println(demo.thirdMax0(Common.string2IntArr("[3,2,1]")));
    }
}
