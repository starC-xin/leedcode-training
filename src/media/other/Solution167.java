package media.other;

import utils.Common;

import java.util.Arrays;

/**
 * 2023/7/8
 *
 * @author x.z
 */
public class Solution167 {
    /**
     * TODO 很简单的一道题
     *      在二分搜索上衍生一下即可
     *      因为原数组为非递减顺序（即隐含条件为存在多个相同元素
     *      此算法还可以再执行一步优化
     *      即再次获取的mid为相同数时，将原 left/right 区间执行一步收缩，越过 left/right 的相同元素
     *      我这里因为 4ms过了，暂时就不再修改了
     */
    public int[] twoSum(int[] numbers, int target) {
        int num1, num2;
        for (int i = 0; i < numbers.length; i++) {
            num1 = numbers[i];
            num2 = target - num1;
            int left = i + 1;
            int right = numbers.length - 1;
            while(left <= right){
                int mid = left + (right - left) / 2;
                if(num2 == numbers[mid]){
                    return new int[]{i + 1, mid + 1};
                }else if(num2 > numbers[mid]){
                    left = mid + 1;
                }else{
//                    num2 < numbers[mid]
                    right = mid - 1;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        final Solution167 demo = new Solution167();

        System.out.println(Arrays.toString(demo.twoSum(Common.string2IntArr("[2,7,11,15]"), 9)));
    }
}
