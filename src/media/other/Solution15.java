package media.other;

import utils.Common;

import java.util.*;

/**
 * 2023/7/9
 *
 * @author x.z
 */
public class Solution15 {
    /**
     * 最暴力的思路则是直接 三重循环
     * 暴力过不了，会超时
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Set<List<Integer>> vis = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if(nums[i] + nums[j] + nums[k] == 0){
                        final ArrayList<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[k]);
                        tmp.sort(Comparator.naturalOrder());
                        if(vis.contains(tmp)){
                            continue;
                        }
                        vis.add(tmp);
                        res.add(tmp);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 尝试不那么暴力的解法
     * 过了，但是很丑
     * 思路上，排序后获取 0 和第一个正数的分界点
     * 按照 负负正、负零正、负正正 三种规律找寻三元数组
     * 要想结果为零，只能是这三种可能
     */
    public List<List<Integer>> threeSum0(int[] nums) {
        Arrays.sort(nums);
        int seq = -1;
        int seqZero = -1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                seq = i;
            }
            if(seqZero == -1 && nums[i] == 0){
                seqZero = i;
            }
            if(seq != -1){
                break;
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        if(seqZero == 0 && seq < 0){
            res.add(List.of(0, 0, 0));
            return res;
        }
        if(seq <= 0){
            return res;
        }
        if(seqZero > 0 && seq - seqZero >= 3){
            res.add(List.of(0, 0, 0));
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Set<List<Integer>> vis = new HashSet<>();

        final boolean flag = seqZero != -1;
        int seqI = flag ? seqZero : seq;
        for (int i = 0; i < seqI; i++) {
            boolean zero = flag;
            for (int j = seq; j < nums.length; j++) {
                final int tmpSum = -1 * (nums[i] + nums[j]);
                ArrayList<Integer> integers = null;
                if(tmpSum == 0 && zero){
                    integers = new ArrayList<>();
                    integers.add(nums[i]);
                    integers.add(0);
                    integers.add(nums[j]);
                    zero = false;
                }else{
                    final Integer integer = map.get(tmpSum);
                    if(null != integer){
                        boolean tmpFlag = tmpSum == nums[i] || tmpSum == nums[j];
                        if((tmpFlag && integer > 1) || (!tmpFlag && integer > 0)){
                            integers = new ArrayList<>();
                            integers.add(nums[i]);
                            integers.add(tmpSum);
                            integers.add(nums[j]);
                        }
                    }
                }
                if(integers != null){
                    integers.sort(Comparator.naturalOrder());
                    if (vis.contains(integers)){
                        continue;
                    }
                    vis.add(integers);
                    res.add(integers);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        final Solution15 demo = new Solution15();

        System.out.println(demo.threeSum0(Common.string2IntArr("[1,1,-2]")));
    }
}
