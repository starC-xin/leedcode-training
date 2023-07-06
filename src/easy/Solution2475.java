package easy;

import utils.Common;

import java.util.HashSet;
import java.util.Set;

/**
 * 2023/6/13
 * 2475. 数组中不等三元组的数目
 * 给你一个下标从 0 开始的正整数数组 nums 。请你找出并统计满足下述条件的三元组 (i, j, k) 的数目：
 *
 * 0 <= i < j < k < nums.length
 * nums[i]、nums[j] 和 nums[k] 两两不同 。
 * 换句话说：nums[i] != nums[j]、nums[i] != nums[k] 且 nums[j] != nums[k] 。
 * 返回满足上述条件三元组的数目。
 *
 * @author x.z
 */
public class Solution2475 {
    /**
     * 尝试穷举一下
     * TODO 艺术就是爆炸
     */
    public int unequalTriplets(int[] nums) {
        Set<Integer> set = new HashSet<>();

        int sum = 0;
        int outerSum = 0;
        Integer pre = null;
        for (int i = 0; i < nums.length - 2; i++) {
            final int num = nums[i];
            if (null != pre) {
                if (pre == num) {
                    sum += outerSum;
                    continue;
                }
            }
            outerSum = 0;
            pre = num;
            set.add(num);

            Integer last = null;
            int innerSum = 0;
            for (int j = i + 1; j < nums.length - 1; j++) {
                final int num1 = nums[j];
                if(null != last){
                    if(last == num1){
                        sum += innerSum;
                        continue;
                    }
                }
                innerSum = 0;
                last = num1;
                set.add(num1);
                if(set.size() == 2){
                    for (int k = j + 1; k < nums.length; k++) {
                        final int num2 = nums[k];
                        set.add(num2);
                        if(set.size() == 3){
                            sum ++;
                            innerSum ++;
                            outerSum ++;
                            set.remove(num2);
                        }
                    }
                    set.remove(num1);
                }
            }
            set.remove(num);
        }
        return sum;
    }

    /**
     * 示例代码
     */
    public int unequalTripletsAnswer(int[] nums) {
        int res = 0, pairs = 0, i = 1, n = nums.length;
        var cnt = new int[1001];
        cnt[nums[0]] = 1;
        do {
            int count = cnt[nums[i]]++, others = i - count;
            res += pairs - count * others;
            pairs += others;
        } while (++i < n);
        return res;
    }

    public static void main(String[] args) {
        final Solution2475 demo = new Solution2475();

        System.out.println(demo.unequalTriplets(Common.string2IntArr("[1,3,1,2,4]")));
    }
}
