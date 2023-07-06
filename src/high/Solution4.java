package high;

/**
 * 2022/2/9
 *
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 *
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 *  
 *
 * 示例 1：
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 *
 * 示例 2：
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * 示例 3：
 * 输入：nums1 = [0,0], nums2 = [0,0]
 * 输出：0.00000
 *
 * 示例 4：
 * 输入：nums1 = [], nums2 = [1]
 * 输出：1.00000
 *
 * 示例 5：
 * 输入：nums1 = [2], nums2 = []
 * 输出：2.00000
 *  
 * 提示：
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution4 {
    /**
     * TODO 初步判断，使用归并排序融合两个正序数组
     *      然后取中位值即可
     *      此方法需要的空间复杂度为O(n)
     *      稍加思索，优化思路，仅遍历一半数据，取得中位数值即可
     *      耗时 1ms，超越100%，空间消耗42.1MB，超越7.66%
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int mid = nums1.length + nums2.length;
        boolean flag = mid % 2 == 0;
        int max = flag ? mid / 2 + 1 : (mid + 1) / 2;
        int pre = 0, tmp = -1;
        int i, j, cur;
        i = j = cur = 0;
        while(cur < max){
            if(tmp != -1){
                pre = tmp;
            }
            if(i < nums1.length && j < nums2.length){
                if(nums1[i] < nums2[j]){
                    tmp = nums1[i ++];
                }else{
                    tmp = nums2[j ++];
                }
            }else{
                if(i < nums1.length){
                    tmp = nums1[i ++];
                }
                if(j < nums2.length){
                    tmp = nums2[j ++];
                }
            }
            cur ++;
        }
        return flag ? (pre + tmp) / 2.0 : tmp;
    }
}
