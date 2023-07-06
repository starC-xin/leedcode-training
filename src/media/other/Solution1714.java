package media.other;

/**
 * 2023/4/22
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 *
 * 示例：
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 *
 * 提示：
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/smallest-k-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * TODO 相似题目二，也采用堆方法实现
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *
 * 提示：
 * 1 <= k <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author x.z
 */
public class Solution1714 {
    /**
     * 尝试以队列的方式实现
     * 优先队列就是堆
     * 问题一
     */
    public int[] smallestK(int[] arr, int k) {
        int[] result = new int[k];
        for (int i = (arr.length - 2) / 2; i >= 0; i--) {
            shiftDown(arr, arr.length, i);
        }
        for (int i = 0; i < k; i++) {
            result[i] = arr[0];
            arr[0] = arr[arr.length - 1 - i];
            shiftDown(arr, arr.length - 1 - i,0);
        }
        return result;
    }

    /**
     * 最小堆
     * @param arr
     * @param len
     * @param k
     */
    private void shiftDown(int[] arr, int len, int k){
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
     * 最大堆
     */
    private void shiftDownMax(int[] arr, int len, int k){
        int temp = arr[k];
        while(2 * k + 1 <= len - 1){
            int maxChildIndex = 2 * k + 1;
            if(maxChildIndex + 1 < len && arr[maxChildIndex] < arr[maxChildIndex + 1]){
                maxChildIndex += 1;
            }
            if(temp < arr[maxChildIndex]){
                arr[k] = arr[maxChildIndex];
                k = maxChildIndex;
            }else{
                break;
            }
        }
        arr[k] = temp;
    }

    /**
     * 问题二
     */
    public int findKthLargest(int[] nums, int k) {
        for (int i = (nums.length - 2) / 2; i >= 0; i--) {
            shiftDownMax(nums, nums.length, i);
        }
        for (int i = 0; i < k - 1; i++) {
            nums[0] = nums[nums.length - 1 - i];
            shiftDownMax(nums, nums.length - 1 - i,0);
        }
        return nums[0];
    }

    /**
     * test
     */
    public static void main(String[] args) {
        final Solution1714 test = new Solution1714();
        int[] arr = {1,3,5,7,2,4,6,8};
        int k = 4;
        for (int i : test.smallestK(arr, k)) {
            System.out.println(i);
        }
    }
}
