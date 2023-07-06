package easy;

/**
 * 2023/5/29
 * 2455. 可被三整除的偶数的平均值
 * 给你一个由正整数组成的整数数组 nums ，返回其中可被 3 整除的所有偶数的平均值。
 *
 * 注意：n 个元素的平均值等于 n 个元素 求和 再除以 n ，结果 向下取整 到最接近的整数。
 *
 * @author x.z
 */
public class Solution2455 {

    /**
     * f**k error case: [1,2,4,7,10]
     */
    public int averageValue(int[] nums) {
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            if(num % 3 == 0 && num % 2 == 0){
                sum += num;
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }
}
