package competition.single.week353;

import utils.Common;

/**
 * 2023/7/9
 * 排名 1251
 * @author x.z
 */
public class Solution {
    public int theMaximumAchievableX(int num, int t) {
        return num + 2 * t;
    }

    /**
     * 最大跳跃次数，即每一个都尽量跳跃
     * 可以考虑贪心
     * 贪心过不了，用动规试试
     * 动规过了，虽然一开始的转换方程画错了，导致出了几个 wa
     */
    public int maximumJumps(int[] nums, int target) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length - 1; i++) {
            if(i != 0 && res[i] == 0){
                continue;
            }
            for (int j = i + 1; j < nums.length; j++) {
                if(Math.abs(nums[j] - nums[i]) <= target){
                    res[j] = Math.max(res[j], res[i] + 1);
                }
            }
        }
        return res[nums.length - 1] == 0 ? -1 : res[nums.length - 1];
    }

    /**
     * TODO 貌似也可以使用贪心，虽然感觉是过不了
     *      每次赋值一个不小于上一个元素，且尽量小的值
     * TODO 过不了，改动规吧
     *      AC
     */
    public int maxNonDecreasingLength(int[] nums1, int[] nums2) {
        int[][] sw = new int[nums1.length][2];
        sw[0][0] = sw[0][1] = 1;
        int res = 1;
        for (int i = 1; i < nums1.length; i++) {
            sw[i][0] = Math.max(
                    nums1[i] >= nums1[i - 1] ? sw[i - 1][0] + 1 : 1,
                    nums1[i] >= nums2[i - 1] ? sw[i - 1][1] + 1 : 1
            );

            sw[i][1] = Math.max(
                    nums2[i] >= nums1[i - 1] ? sw[i - 1][0] + 1 : 1,
                    nums2[i] >= nums2[i - 1] ? sw[i - 1][1] + 1 : 1
            );

            res = Math.max(res, Math.max(sw[i][1], sw[i][0]));
        }
        return res;
    }

    /**
     * TODO 当任意区间 k 中的最大与最小相差值大于 1 时，则返回 false
     *      那么直接寻找整个数组的最大值和最小值，对其周围 k 区间执行判断
     *      不满足上一条件则返回 false
     *      过不了，思路有问题，转 {@link #checkArray0(int[], int)}
     */
    public boolean checkArray(int[] nums, int k) {
        if(1 == k){
            return true;
        }
        int maxI = 0;
        int minI = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[maxI]){
                maxI = i;
            }
            if(nums[i] < nums[minI]){
                minI = i;
            }
        }
        return checkSubArr(nums, maxI, k, false) && checkSubArr(nums, minI, k, true);
    }

    private boolean checkSubArr(int[] nums, int index, int k, boolean flag){
        int begin = Math.max(index - k, 0);
        int end = Math.min(begin + k, nums.length);
        int tmpI = -1;
        while(end <= nums.length){
            tmpI = findIndex(begin, end, tmpI, nums, flag);
            if(Math.abs(nums[index] - nums[tmpI]) <= 1){
                return true;
            }
            end ++;
            begin ++;
        }
        return false;
    }

    private int findIndex(int begin, int end, int lastI, int[] nums, boolean flag){
        if(lastI >= begin && lastI < end){
            return lastI;
        }
        int tmpI = begin;
        for (int i = begin + 1; i < end; i++) {
            if(flag){
                if(nums[i] > nums[tmpI]){
                    tmpI = i;
                }
            }else{
                if(nums[i] < nums[tmpI]){
                    tmpI = i;
                }
            }
        }
        return tmpI;
    }

    /**
     * TODO 从头部或尾部选择 k 区间逐个递减
     *      也没过，看看别人的参考
     */
    public boolean checkArray0(int[] nums, int k) {
        for (int i = 0; i < nums.length - k; i++) {
            if(nums[i] == 0){
                continue;
            }
            int tmp = nums[i];
            for (int j = i; j < i + k; j++) {
                if(nums[j] < 0){
                    return false;
                }
                nums[j] -= tmp;
            }
        }
//        check
        for (int num : nums) {
            if(num != 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 又来看看a佬的提交
     * 说实话，每次看a佬的代码都搞不懂是怎么回事
     */
    static class Solution4 {
        public boolean checkArray(int[] nums, int k) {
            int curr = 0, count[] = new int[nums.length + k];
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < (curr -= count[i]) || nums[i] > curr && i + k > nums.length) {
                    return false;
                }
                curr += count[i + k] = nums[i] - curr;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        final Solution demo = new Solution();

//        System.out.println(demo.maximumJumps(Common.string2IntArr("[0,2,1,3]"), 1));
//        System.out.println(demo.maxNonDecreasingLength(Common.string2IntArr("[2,3,1]"), Common.string2IntArr("[1,2,1]")));

//        case 432
        System.out.println(demo.checkArray0(Common.string2IntArr("[0,45,82,98,99]"), 4));
    }
}
