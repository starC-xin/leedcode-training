package easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2021/10/20
 *
 * 给定一个非负索引 rowIndex，返回「杨辉三角」的第 rowIndex 行。
 *
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 *
 * 示例 1:
 *
 * 输入: rowIndex = 3
 * 输出: [1,3,3,1]
 * 示例 2:
 *
 * 输入: rowIndex = 0
 * 输出: [1]
 * 示例 3:
 *
 * 输入: rowIndex = 1
 * 输出: [1,1]
 *  
 *
 * 提示:
 *
 * 0 <= rowIndex <= 33
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/pascals-triangle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author x.c
 */
public class Solution119 {
    public static void main(String[] args){
//        System.out.println((float) 1.4 * 330);
        new Solution119().getRow(28).forEach(System.out::println);
    }

    public List<Integer> getRow(int rowIndex) {
        Integer[] nums = new Integer[rowIndex + 1];
        if(rowIndex == 0){
            nums = new Integer[]{1};
            return Arrays.asList(nums);
        }
        nums[0] = nums[nums.length - 1] = 1;
        if(nums.length == 1){
            return Arrays.asList(nums);
        }

        int len = (rowIndex + 1) / 2;
        int cur = rowIndex;
        int i;
        for(i = 1; i < len; i++){
            nums[i] = nums[rowIndex - i] = cur;
//            此处的精度问题是一个大坑
            cur = (int)Math.round((rowIndex - i) / (double)(i + 1) * cur) ;
        }
        if(nums.length % 2 != 0){
            int mid = nums.length / 2;
//            此处的精度问题是一个大坑
//            此处可不使用 Math.round 直接强转，因为其本身为偶数的概率较大，一般不存在精度问题
//            但为了严谨，依旧是保持 round 四舍五入比较好
            nums[mid] = (int)Math.round(((rowIndex + 1 - i) / (double)i * nums[mid - 1]));
        }
        return Arrays.asList(nums);
    }
}
