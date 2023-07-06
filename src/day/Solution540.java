package day;

/**
 * 2022/2/14
 *
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 *
 * 请你找出并返回只出现一次的那个数。
 *
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 *
 * 示例 1:
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 *
 * 示例 2:
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 *  
 *
 * 提示:
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/single-element-in-a-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution540 {
    /**
     * TODO 暴力破解，时间复杂度为O(n)，空间复杂度为O(1)
     *      耗时 1ms，击败22.98%
     *      空间击败12.54%，这个当乐子吧，不太具备参考价值
     *      还有一种 (异或) 的解法，思路和我这里类似，不过不具备中途跳出的条件，需要完整地遍历整个数组，时间换空间
     */
    public int singleNonDuplicate(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        int pre, cur;
        pre = nums[0];
        cur = nums[1];
        if(pre != cur){
            return pre;
        }
        for(int i = 2; i < nums.length; i++){
            if(i % 2 == 0){
                cur = nums[i];
            }else{
                pre = nums[i];
                if(pre != cur){
                    return cur;
                }
            }
        }
        return cur;
    }

    /**
     * TODO 官方题解给出的解答
     *      采用的是二分，将时间复杂度降低到O(logN)
     *      思路是该有序数组的特性，这个单个数的左边和右边都是成对的数
     *      这个单个数的下标一定是偶数，且右边的对数满足x，x为奇数，x=x+1
     *                              左边的对数满足y，y为偶数，y=y+1
     *      以此为分界线，使用二分搜索该分界线
     */
    public int singleNonDuplicate1(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = (high - low) / 2 + low;
            mid -= mid & 1;
            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

    /**
     * TODO 写着玩的
     * @param args
     */
    public static void main(String[] args){
        int temp = 20;
        while(temp > 0){
            System.out.println(temp-- ^ 1);
        }
    }
}