package media.other;

import utils.Common;

import java.sql.Array;
import java.util.*;

/**
 * 2023/7/15
 *
 * @author x.z
 */
public class Solution18 {
    /**
     * TODO 四数之和，属于两数之和、三数之和的变体，以后多半还有x数之和
     *      这里因为是四数之和，恰巧可以用到两数之和的思路
     *      将四数转化为两数之和的之和问题，即，将四数两两组合，退化为两数之和
     *      然后按照两数之和的思路来处理四数
     *      需要注意的是，要处理好下标问题、相同组合的同分异构体问题
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        Map<Integer, List<int[]>> cache = new HashMap<>();
        boolean allA = true;
        boolean allI = true;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                final int key = nums[i] + nums[j];
                if(key < 0 ){
                    allA = false;
                }
                if(key > 0){
                    allI = false;
                }
                cache.computeIfAbsent(key, var -> new ArrayList<>()).add(new int[]{i, j});
            }
        }
//        case 290 添加，解决溢出问题
        if((target < 0 && allA) || (target > 0 && allI)){
            return new ArrayList<>();
        }

        Set<Integer> vis = new HashSet<>();
        Set<List<Integer>> visI = new HashSet<>();
//        case 288 添加，解决相同元素的问题
        Set<List<Integer>> visT = new HashSet<>();
        for (Map.Entry<Integer, List<int[]>> entry : cache.entrySet()) {
            if(vis.contains(entry.getKey())){
                continue;
            }
            int tmpTarget = target - entry.getKey();
            vis.add(tmpTarget);
            final List<int[]> value = entry.getValue();
            if(tmpTarget == entry.getKey()){
                if(value.size() == 1){
                    continue;
                }
                for (int i = 0; i < value.size() - 1; i++) {
                    final int[] tmpI = value.get(i);
                    final List<Integer> num = List.of(nums[tmpI[0]], nums[tmpI[1]]);
                    if (visT.contains(num)) {
                        continue;
                    }
                    visT.add(num);
                    for (int j = i + 1; j < value.size(); j++) {
                        checkAndAdd(nums, tmpI, value.get(j), visI);
                    }
                }
            }else if(!cache.containsKey(tmpTarget)){
                continue;
            }
            final List<int[]> tmpValue = cache.get(tmpTarget);
            for (final int[] tmpI : value) {
                final List<Integer> num = List.of(nums[tmpI[0]], nums[tmpI[1]]);
                if (visT.contains(num)) {
                    continue;
                }
                visT.add(num);
                for (final int[] tmpJ : tmpValue) {
                    checkAndAdd(nums, tmpI, tmpJ, visI);
                }
            }
        }
        return new ArrayList<>(visI);
    }

    private void checkAndAdd(int[] nums, int[] tmpI, int[] tmpJ, Set<List<Integer>> visI) {
        if(tmpI[0] == tmpJ[0] || tmpI[0] == tmpJ[1] || tmpI[1] == tmpJ[0] || tmpI[1] == tmpJ[1]){
            return;
        }
        final int[] tmpArr = {nums[tmpI[0]], nums[tmpI[1]], nums[tmpJ[0]], nums[tmpJ[1]]};
        Arrays.sort(tmpArr);
        visI.add(List.of(tmpArr[0], tmpArr[1], tmpArr[2], tmpArr[3]));
    }

    public static void main(String[] args) {
        final Solution18 demo = new Solution18();

//        示例
//        System.out.println(demo.fourSum(Common.string2IntArr("[1,0,-1,0,-2,2]"), 0));

//        case 288 超时，原因大量相同元素
//        [2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2]
//        8
//        System.out.println(demo.fourSum(Common.string2IntArr("[2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2]"), 8));

        System.out.println(Integer.MAX_VALUE);
//        case 290 错误，原因：溢出
//        [1000000000,1000000000,1000000000,1000000000]
//        -294967296
        System.out.println(demo.fourSum(Common.string2IntArr("[1000000000,1000000000,1000000000,1000000000]"), -294967296));
    }
}
