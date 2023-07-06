package day;

/**
 * 2021/10/20
 *
 * 给你一个长度为 n 的整数数组，每次操作将会使 n - 1 个元素增加 1 。返回让数组所有元素相等的最小操作次数。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：3
 * 解释：
 * 只需要3次操作（注意每次操作会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 *
 * 示例 2：
 * 输入：nums = [1,1,1]
 * 输出：0
 *  
 *
 * 提示：
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * 答案保证符合 32-bit 整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-moves-to-equal-array-elements
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * TODO ---- 解析
 *
 * JAVA版——提供完整思路
 *
 * --------------------------------------------
 *
 * 【变量说明】
 *
 * 数组长度为 n ，最小操作次数为 k ，操作前数组的最小值为 min ，操作前数组的最大值为 max ，操作前数组的第二大值为 max2 ，操作前数组所有数和为 sum 。
 *
 * --------------------------------------------
 *
 * 【思路分析】
 *
 * 假设你可以无误执行完整个流程，这样可以搭建流程执行前后的桥梁。
 *
 * 将操作流程前后的量进行分析，找到一个不变量，构成等式，进行求解。
 *
 * 根据题意，每次操作将会使 n - 1 个元素增加 1 。由于你执行了 k 次，所以一共会使 sum 增加 k * (n - 1) 。即操作结束后数组的和为 sum + k * (n - 1) 。
 *
 * 贪心部分：在整个流程的每次操作中，最小的那个值都会增加 1 。（贪心的证明在下个板块，建议最后看）
 *
 * 由贪心可知，经过k 步后， min 变为了 min + k ，也意味着此时数组的每一项都变为了 min + k ，所以操作结束后数组的和为 n * (min + k) 。
 *
 * 根据等量代换：
 *
 * sum + k * (n - 1) = n * (min + k)
 * sum + k * n - k = n * min + n * k
 * sum - k = n * min
 * k = sum - n * min
 * 所以，最短操作次数为 k = sum - n * min 。
 *
 * 先求和再减去长度n个最小值，不是相当于每一项减去最小值再求和吗？
 *
 * 数学证明：（证明公式为latex代码，课根据需要转为公式查看）
 *
 * \sum_{i=0}^{i=n-1}{nums\left[ i \right]}-n\times \min =\sum_{i=0}^{i=n-1}{nums\left[ i \right]}-\sum_{i=0}^{i=n-1}{\min}=\sum_{i=0}^{i=n-1}{nums\left[ i \right] -\min}
 * 最终结论：k = SUM(nums[i] - min)
 *
 * --------------------------------------------
 *
 * 【贪心证明】
 *
 * 结论：在整个流程的每次操作中，最小的那个值都会增加 1 。
 *
 * 证明过程：
 *
 * 这个贪心可能不好理解，这里我们可以分成两个部分来理解：
 *
 * 第一部分就是 max2 在追赶 max 的过程，设这个过程执行了 k' 次，在这 k' 次中，每次必然会使 max 的以外的元素增加 1 ，直到产生一个新的元素大于 max 为止（这个元素就是 max2 位置的元素，记为 max'），这个阶段 min 每次都增加了 1 ，并且这一步的前一步，max' 是等于max 的，之后的每一步都是 max' 和 max 争夺最大值的阶段， max' 和 max 轮流增加 1 ，其他元素每次的增加 1。由于其他元素的增速是大于 max' 和 max 的，所以很快会有个数大于 max' 和 max （也就是原数组的第三大的数，记为 max''），之后就是 max 和 max' 和 max'' 轮流作为最大值的过程。……
 *
 * 第二部分就是第一个部分的临界阶段，也就是由于增速差除了原来最小的数 min 其他数轮流作为最大值时，这个时候 min 所在位置的数会以一种很快的速度增长直到他比最大值的数小 1为止（也就是支出现两种数为止），然后除了最大值的数，其他都增加 1 ，直到弥补和最小值之间的差为止。这个部分 min 也一直在增加。
 *
 * 例题：这里以[1,2,3,4] 为例
 *
 * 第一个部分：
 *
 * [2,3,4,4]：其余数都在追赶你索引为3的数，最小数从1增加为了2。
 *
 * [3,4,5,4]：索引为2的数超过了索引为3的数，最小数从2增加为了3。
 *
 * [4,5,5,5]：其余数都在追赶你索引为2的数，最小数从3增加为了4。（此时只有两种类型的数 4 和 5 ，且差值为 1 ，进入第二部分）
 *
 * 第二部分
 *
 * [5,6,5,6]：索引为1的数超过了索引为2的数，最小数从4增加为了5。
 *
 * [6,6,6,7]：其余数都在追赶你索引为1的数，索引为3的数超过了索引为1的数，最小数从5增加为了6。
 *
 * [7,7,7,7]：其余数都在追赶你索引为3的数，最小数从6增加为了7。
 *
 * @author x.c
 */
public class Solution453 {

    public int minMoves(int[] nums) {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(int num : nums){
            sum += num;
            min = Math.min(min, num);
        }
        return sum - nums.length * min;
    }
}
