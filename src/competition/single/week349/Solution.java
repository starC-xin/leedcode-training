package competition.single.week349;

import utils.Common;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * 2023/6/11
 *
 * @author x.z
 */
public class Solution {

    /**
     * 第一题
     */
    public int findNonMinOrMax(int[] nums) {
        Arrays.sort(nums);
        int min = nums[0];
        int max = nums[nums.length - 1];
        int tmp = -1;
        for (int i = 1; i < nums.length; i++) {
            if (min == nums[i]) {
                continue;
            }
            tmp = nums[i];
            break;
        }

        return tmp == max ? -1 : tmp;
    }

    /**
     * 第二题
     */
    public String smallestString(String s) {
        final char[] chars = s.toCharArray();
        boolean flag = true;
        for (int i = 0; i < chars.length; i++) {
            if (flag) {
                if (chars[i] == 'a') {
                    continue;
                } else {
                    flag = false;
                }
            }
            if (chars[i] != 'a') {
                chars[i] -= 1;
            } else {
                break;
            }
        }

        if (flag) {
            chars[chars.length - 1] = 'z';
        }
        return new String(chars);
    }

    /**
     * 第三题
     */
    public long minCost(int[] nums, int x) {
        return 0;
    }

    /**
     * 虽然没怎么做，但也看看答案
     */
    public long minCostAnswer(int[] nums, int x) {
        long min = IntStream.of(nums).mapToLong(v -> v).sum();
        for (long i = 1; i <= nums.length; i++) {
            int[] next = new int[nums.length];
            for (int j = 0; j < next.length; j++) {
                next[j] = Math.min(nums[j], nums[(j + 1) % nums.length]);
            }
            min = Math.min(min, i * x + IntStream.of(nums = next).mapToLong(v -> v).sum());
        }
        return min;
    }

    /**
     * 第四题
     */
    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int[] max1 = new int[nums1.length];
        int[] max2 = new int[nums2.length];
        for (int i = nums1.length - 1; i >= 0; i--) {
            if (i == nums1.length - 1) {
                max1[nums1.length - 1] = nums1[nums1.length - 1];
                max2[nums1.length - 1] = nums2[nums1.length - 1];
                continue;
            }
            max1[i] = Math.max(nums1[i], max1[i + 1]);
            max2[i] = Math.max(nums2[i], max2[i + 1]);
        }

        int[] result = new int[queries.length];
        Arrays.fill(result, -1);
        int len = nums1.length;
        for (int i = 0; i < queries.length; i++) {
            int[] line = queries[i];
            for (int j = 0; j < len; j++) {
                if(max1[j] < line[0] && max2[j] < line[1]){
                    break;
                }
                if (line[0] <= nums1[j] && line[1] <= nums2[j]) {
                    result[i] = Math.max(result[i], nums1[j] + nums2[j]);
                }
            }
        }
        return result;
    }

    /**
     * 又超时了，看看参考吧
     * TODO 草了看都看不懂
     *      看了下其他人的答案，只有更复杂的没有更简单的
     *      这道题思路很简单，但想要不超时很难，需要大量的剪枝
     */
    public int[] maximumSumQueriesAnswer(int[] nums1, int[] nums2, int[][] queries) {
        int[] nums[] = new int[nums1.length][], result = new int[queries.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = new int[] { nums1[i], nums2[i] };
        }
        Arrays.sort(nums, (o, p) -> p[0] - o[0]);
        Integer[] index = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            index[i] = i;
        }
        Arrays.sort(index, (o, p) -> queries[p][0] - queries[o][0]);
        TreeMap<Integer, Integer> map = new TreeMap<>(Map.of(0, Integer.MAX_VALUE, Integer.MAX_VALUE, -1));
        for (int i = 0, j = 0; i < queries.length; i++) {
            for (; j < nums.length && nums[j][0] >= queries[index[i]][0]; j++) {
                if (map.ceilingEntry(nums[j][1]).getValue() < nums[j][0] + nums[j][1]) {
                    while (map.floorEntry(nums[j][1]).getValue() <= nums[j][0] + nums[j][1]) {
                        map.remove(map.floorKey(nums[j][1]));
                    }
                    map.put(nums[j][1], nums[j][0] + nums[j][1]);
                }
            }
            result[index[i]] = map.ceilingEntry(queries[index[i]][1]).getValue();
        }
        return result;
    }

    public static void main(String[] args) {
        final Solution demo = new Solution();

//        System.out.println(demo.findNonMinOrMax(Common.string2IntArr("[3,2,1,4]")));

//        [4,3,1,2]
//[2,4,9,5]
//[[4,1],[1,3],[2,5]]
        System.out.println(Arrays.toString(demo.maximumSumQueriesAnswer(
                Common.string2IntArr("[4,3,1,2]"),
                Common.string2IntArr("[2,4,9,5]"),
                Common.string2IntIntArr("[[4,1],[1,3],[2,5]]")
        )));

//        [17]
//[42]
//[[9,60],[94,22]]
        System.out.println(Arrays.toString(demo.maximumSumQueries(
                Common.string2IntArr("[17]"),
                Common.string2IntArr("[42]"),
                Common.string2IntIntArr("[[9,60],[94,22]]")
        )));
    }
}
