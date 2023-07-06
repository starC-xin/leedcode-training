package competition.single.week352;

import utils.Common;

import java.sql.Array;
import java.util.*;

/**
 * 2023/7/2
 *
 * @author x.z
 */
public class Solution {

    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int pre = nums[i] % 2;
            int tmpMax = 0;
            if(pre != 0){
                continue;
            }
            while(nums[i] <= threshold){
                tmpMax ++;
                if(i + 1 >= nums.length || nums[i + 1] % 2 == pre){
                    break;
                }
                i++;
                pre = nums[i] % 2;
            }
            maxLen = Math.max(tmpMax, maxLen);
        }
        return maxLen;
    }

    /**
     * TODO 先求所有质数，然后求和，超时，case 499
     *      求质数的同时检索数据对，超时，case 529，还需优化
     *      加入筛选，提前去除合数，超时，case 532，还需优化
     *      直接替换原始质数求解，采用新的求解质数方式，通过
     * TODO 求解质数新方式，筛法求质数，效率最高，但会比较浪费内存
     *      首先建立一个boolean类型的数组，用来存储你要判断某个范围内自然数中的质数，例如，你要输出小于200的质数，
     *      你需要建立一个大小为201（建立201个存储位置是为了让数组位置与其大小相同）的boolean数组，初始化为true。
     *      其次用第二种方法求的第一个质数（在此是2），然后将是2的倍数的数全置为false（2除外），即2、4、6、8……位置
     *      上置为false。然后是3的倍数的全置为false（3除外），一直到14（14是200的开平方），这样的话把不是质数的位
     *      置上置为false了，剩下的全是质数了，挑着是true的打印出来就行了。
     */
    public List<List<Integer>> findPrimePairs(int n) {
        List<List<Integer>> res = new LinkedList<>();
        boolean[] vis = new boolean[n + 1];
//        筛法求质数
        for (int i = 2; i <= Math.sqrt(n); i++) {
            for (int j = i; j * i <= n; j++) {
                vis[j * i] = true;
            }
        }
//        然后加入
        for (int i = 2; i < vis.length; i++) {
            if(vis[i]){
                continue;
            }
            if(n - i < i || vis[n - i]){
                continue;
            }
            res.add(List.of(i, n - i));
        }
        return res;
    }

    /**
     * 那位佬的答案
     */
    static class Solution20 {
//        额，很暴力的预计算，这怎么能过得
        private static HashSet<Integer> set = new HashSet<>() {
            {
                boolean[] flag = new boolean[1000000];
                for (int i = 2; i < flag.length; i++) {
                    if (!flag[i]) {
                        add(i);
                        for (int j = i; j < flag.length; j += i) {
                            flag[j] = true;
                        }
                    }
                }
            }
        };

        public List<List<Integer>> findPrimePairs(int n) {
            ArrayList<List<Integer>> list = new ArrayList<>();
            for (int i = 2; i <= n / 2; i++) {
                if (set.contains(i) && set.contains(n - i)) {
                    list.add(List.of(i, n - i));
                }
            }
            return list;
        }
    }

    public long continuousSubarrays(int[] nums) {
        int res = nums.length;
        List<List<Integer>> tmp = new ArrayList<>();
        for (int i = 0; i < nums.length - 1; i++) {
            List<Integer> tmpSub = new ArrayList<>();
            tmpSub.add(nums[i]);
            int abs = Math.abs(nums[i] - nums[i + 1]);
            while(abs <= 2){
                i ++;
                tmpSub.add(nums[i]);
                if(i < nums.length - 1){
                    abs = Math.abs(nums[i] - nums[i + 1]);
                }
            }
            tmp.add(tmpSub);
        }
        for (final List<Integer> sub : tmp) {
            if (sub.size() > 2) {
                res += sub.size() - 1;
            }
            for (int k = 3; k <= sub.size(); k++) {
                for (int i = 0; i < sub.size(); i++) {
                    int count = 0;
                    for (int j =  i + 1; j < sub.size() - 1; j++) {
                        if(count == k){
                            res ++;
                            break;
                        }
//                        if(Math.abs(sub.get(i),)){}
                    }
                }
            }
        }
        return res;
    }

    /**
     * 那位佬的参考
     */
    static class Solution30 {
        /**
         * TreeMap 出现的频率很高啊
         */
        public long continuousSubarrays(int[] nums) {
            long count = 0;
            TreeMap<Integer, Integer> map = new TreeMap<>();
            for (int i = 0, j = 0; j < nums.length; j++) {
                map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);
                for (; map.lastKey() - map.firstKey() > 2; i++) {
                    map.put(nums[i], map.get(nums[i]) - 1);
                    if (map.get(nums[i]) == 0) {
                        map.remove(nums[i]);
                    }
                }
                count += j - i + 1;
            }
            return count;
        }
    }


    public static void main(String[] args) {
        final Solution demo = new Solution();

//        System.out.println(demo.longestAlternatingSubarray(Common.string2IntArr("[3,2,5,4]"), 5));

        final long begin = System.nanoTime();
        System.out.println(demo.findPrimePairs(1000000));
        System.out.println(System.nanoTime() - begin);
    }
}
