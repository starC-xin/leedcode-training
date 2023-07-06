package competition.single.week324;

/**
 * 2023/4/23
 *
 * @author x.z
 */
public class Solution {
    /**
     * 6387. 计算列车到站时间
     * 给你一个正整数 arrivalTime 表示列车正点到站的时间（单位：小时），另给你一个正整数 delayedTime 表示列车延误的小时数。
     * 返回列车实际到站的时间。
     * 注意，该问题中的时间采用 24 小时制。
     *
     */
    public int findDelayedArrivalTime(int arrivalTime, int delayedTime) {
        return (arrivalTime + delayedTime) % 24;
    }

    /**
     * 6391. 倍数求和
     * 给你一个正整数 n ，请你计算在 [1，n] 范围内能被 3、5、7 整除的所有整数之和。
     * 返回一个整数，用于表示给定范围内所有满足约束条件的数字之和。
     */
    public int sumOfMultiples(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if(i % 3 == 0 || i % 5 == 0 || i % 7 == 0){
                sum += i;
            }
        }
        return sum;
    }

    /**
     * 6390. 滑动子数组的美丽值
     * 给你一个长度为 n 的整数数组 nums ，请你求出每个长度为 k 的子数组的 美丽值 。
     * 一个子数组的 美丽值 定义为：如果子数组中第 x 小整数 是 负数 ，那么美丽值为第 x 小的数，否则美丽值为 0 。
     * 请你返回一个包含 n - k + 1 个整数的数组，依次 表示数组中从第一个下标开始，每个长度为 k 的子数组的 美丽值 。
     * 子数组指的是数组中一段连续 非空 的元素序列。
     * TODO 以供731个示例，到701的时候始终超时，怀疑是大量重复元素导致的问题，应该需要从这里下手
     */
    public int[] getSubarrayBeauty(int[] nums, int k, int x) {
        int len = nums.length - k + 1;
        int[] result = new int[len];

        int[] tmpArr = new int[k];
        int[] tmpArrIndex = new int[k];
        int[] tmpArrReverse = new int[k];
        System.arraycopy(nums, 0, tmpArr, 0, k);
        for (int i = 0; i < tmpArrIndex.length; i++) {
            tmpArrIndex[i] = i;

        }
        int tmp;
        tmp = findKthLowest(tmpArr, x);
        if(tmp < 0){
            result[0] = tmp;
        }

        for (int i = 1, n = 0; i <= nums.length - k; i++, n++) {
//            窗口移动
            int index = (n + k) % tmpArr.length;
            tmpArr[index] = nums[i];

        }
        return result;
    }
    public int findKthLowest(int[] nums, int k) {
        for (int i = (nums.length - 2) / 2; i >= 0; i--) {
            shiftDownMin(nums, nums.length, i);
        }
        for (int i = 0; i < k - 1; i++) {
            nums[0] = nums[nums.length - 1 - i];
            shiftDownMin(nums, nums.length - 1 - i,0);
        }
        return nums[0];
    }
    private void shiftDownMin(int[] arr, int len, int k){
        int temp = arr[k];
        while(2 * k + 1 <= len - 1){
            int maxChildIndex = 2 * k + 1;
            if(maxChildIndex + 1 < len && arr[maxChildIndex] > arr[maxChildIndex + 1]){
                maxChildIndex += 1;
            }
            if(temp > arr[maxChildIndex]){
                arr[k] = arr[maxChildIndex];
                k = maxChildIndex;
            }else{
                break;
            }
        }
        arr[k] = temp;
    }


    /**
     * test
     */
    public static void main(String[] args) {
        final Solution test = new Solution();

        int[] nums = {-46, -34, -46};
        int k = 3;
        int x = 3;
        for (int i : test.getSubarrayBeauty(nums, k, x)) {
            System.out.println(i);
        }
    }
}
