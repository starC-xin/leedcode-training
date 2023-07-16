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
     * 在 a 佬基础上修改得到的
     */
    public int maximumBeauty0(int[] nums, int k) {
        Arrays.sort(nums);
        int max = 0;
        Integer pre = null;
        for (int i = 0, j = 0; i < nums.length; i++) {
            if(pre != null && pre == nums[i]){
                continue;
            }
            for (; j < nums.length && nums[j] - nums[i] <= 2 * k; j++) {
            }
            max = Math.max(max, j - i);
            pre = nums[i];
        }
        return max;
    }

    /**
     * 啊？
     * 说实话，a佬这一上来就排序，我真没看懂
     * 排序后违背了原数组不可变的要求吧，怎么能在排序后依然得到正确答案的呢
     * 然后内循环应该关键点，虽然看懂了是怎么回事，但看不懂思路
     * TODO 好像明白了
     *      是我读题有问题，要求是取得出现频率最高的
     *      修改要求是 nums[i] 可以修改为 [nums[i] - k, nums[i] + k] 中的任意值
     *      即，修改前后与原数组顺序无关
     *      排序后即可找到词频最高的
     *      并以此为基础，对其前后可修改范围的数值也尝试加载到该基数上
     *      即可得到最大返回值
     *      a佬的代码在此基础上设计了一个类似滚动数组的动规逻辑
     *      转换方程大致可以表示为 f(max)=Max(len(i), len(i + 1))，
     *          其中 len(i)=count(i, i + k)，
     *          且 i+k 满足 nums[i+k]-nums[i]<=k*2
     *      既然如此，那就还有优化余地，直接越过相同元素的计算
     *      因为第一遍历到该元素时，一定就是本元素能取得的最大值，再次遍历到该元素时，一定小于第一次遍历
     *      调整后的代码见 {@link #maximumBeauty0(int[], int)}
     */
    static class Solution2 {
        public int maximumBeauty(int[] nums, int k) {
            Arrays.sort(nums);
            int max = 0;
            for (int i = 0, j = 0; i < nums.length; i++) {
                for (; j < nums.length && nums[j] - nums[i] <= 2 * k; j++) {
                }
                max = Math.max(max, j - i);
            }
            return max;
        }
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

    /**
     * 这个是真没看懂
     */
    static class Solution4 {
        public int longestValidSubstring(String word, List<String> forbidden) {
            int max = 0;
            HashSet<String> set = new HashSet<>(forbidden);
            for (int i = 0, j = 0, k; i < word.length(); max = Math.max(max, j - i++)) {
                for (; j < word.length(); j++) {
                    for (k = Math.max(i, j - 9); k <= j && !set.contains(word.substring(k, j + 1)); k++) {
                    }
                    if (k <= j) {
                        break;
                    }
                }
            }
            return max;
        }
    }

    public static void main(String[] args) {
        final Solution demo = new Solution();


        System.out.println(demo.minimumIndex(Common.string2IntList("[1,2,2,2]")));
//        System.out.println(demo.minimumIndex(Common.string2IntList("[2,1,3,1,1,1,7,1,2,1]")));
//        System.out.println(demo.minimumIndex(Common.string2IntList("[3,3,3,3,7,2,2]")));
    }
}
