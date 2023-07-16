package competition.single.week354;

import utils.Common;

import java.util.*;

/**
 * 2023/7/16
 *
 * @author x.z
 */
public class Solution {
    public int sumOfSquares(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums.length % (i + 1) == 0){
                res += nums[i] * nums[i];
            }
        }
        return res;
    }

    /**
     * 统计出现频率最高的数，以此为基数执行替换
     * TODO 替换思路写错了，这题先放一下
     */
    public int maximumBeauty(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxN = -1;
        int maxCount = 0;
        for (int num : nums) {
            final int count = map.getOrDefault(num, 0) + 1;
            if (count > maxCount) {
                maxCount = count;
                maxN = num;
            }
            map.put(num, count);
        }

        boolean[] vis = new boolean[nums.length];

        return maxCount;
    }

    /**
     * TODO 找出频率最高的元素
     *      此题要结合前缀和思路
     *      卡在 case 912 超时了，一共 915 个 case
     *      重构，这个思路有问题，易超时，重构见{@link #minimumIndex0(List)}
     */
    public int minimumIndex(List<Integer> nums) {
        int preMax = -1, sufMax = -1;
        int preMaxCount = 0, sufMaxCount = 0;
        Map<Integer, Integer> preMap = new HashMap<>(), sufMap = new HashMap<>();
        for (Integer num : nums) {
            final int count = sufMap.getOrDefault(num, 0) + 1;
            sufMap.put(num, count);
        }

        for (int i = 0; i < nums.size(); i++) {
            int count = preMap.getOrDefault(nums.get(i), 0) + 1;
            preMap.put(nums.get(i), count);
            if(count > preMaxCount){
                preMaxCount = count;
                preMax = nums.get(i);
            }

            count = sufMap.get(nums.get(i)) - 1;
            if(count <= 0){
                sufMap.remove(nums.get(i));
            }else{
                sufMap.put(nums.get(i), count);
            }
            if(nums.get(i) == sufMax){
                final Map.Entry<Integer, Integer> entry = sufMap.entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .orElseGet(() -> Map.entry(-1, 0));
                sufMax = entry.getKey();
                sufMaxCount = entry.getValue();
            }

            if((preMaxCount * 2 > i + 1 && sufMaxCount * 2 > nums.size() - i - 1) && preMax == sufMax){
                return i;
            }
        }
        return -1;
    }

    /**
     * TODO 一次过
     *      此处思路主要是抓住了数组中只能最多存在一个支配元素
     *      那么其子数组的支配元素一定是同一个元素
     *      再结合前缀和思想优化，即可
     */
    public int minimumIndex0(List<Integer> nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        final Map.Entry<Integer, Integer> max = map.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElseGet(() -> Map.entry(-1, 0));
        int maxNum = max.getKey();
        if(maxNum == -1){
            return -1;
        }
        int pre = 0;
        int suf = max.getValue();
        for (int i = 0; i < nums.size(); i++) {
            if(nums.get(i) != maxNum){
                continue;
            }
            pre ++;
            suf --;
            if(pre * 2 > i + 1 && suf * 2 > nums.size() - 1 - i){
                return i;
            }
        }
        return -1;
    }

    /**
     * TODO 依然是前缀和思想
     *      但需要结合双指针执行子字符串的跳跃截取
     *      有点类似优化后的 KMP 算法
     *      不过既然是 HARD 难度，普通的 kmp 必然是超时的
     *      我这里已经没时间写了，整理一下思路结束吧
     *      问题点在于子字符串的滑动窗口如何设计，以及快速判定 forbidden 中的非法字符串
     *      forbidden 的长度可以达到 10^5，逐一 contain 必然超时
     *      如果统计 forbidden 的字母词频，也许会出现占据所有字母的词频，直接导致词频失效
     *      看看结束后的A佬怎么写的
     */
    public int longestValidSubstring(String word, List<String> forbidden) {
        return 0;
    }

    public static void main(String[] args) {
        final Solution demo = new Solution();


        System.out.println(demo.minimumIndex(Common.string2IntList("[1,2,2,2]")));
//        System.out.println(demo.minimumIndex(Common.string2IntList("[2,1,3,1,1,1,7,1,2,1]")));
//        System.out.println(demo.minimumIndex(Common.string2IntList("[3,3,3,3,7,2,2]")));
    }
}
