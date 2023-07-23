package competition.single.week355;

import utils.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 2023/7/23
 *
 * @author x.z
 */
public class Solution {
    public List<String> splitWordsBySeparator(List<String> words, char separator) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            StringBuilder tmp = new StringBuilder();
            for (char c : word.toCharArray()) {
                if(c == separator){
                    if(!tmp.toString().equals("")){
                        res.add(tmp.toString());
                        tmp = new StringBuilder();
                    }
                }else{
                    tmp.append(c);
                }
            }
            if(!tmp.toString().equals("")){
                res.add(tmp.toString());
            }
        }
        return res;
    }

    /**
     * 从尾部开始遍历，若满足条件则执行融合
     * TODO 卡在了 case 1012
     *      可能是存在未被融合的数值
     *      可能是融合后的数值依然小于数组中未参与融合的数值
     *      依然不对，那就只能是存在未被融合的数值
     *      想不明白是哪个情况遗漏了
     *      应该是 int 溢出了
     *      果然 nmd 是 int 溢出，我受不了了
     */
    public long maxArrayValue(int[] nums) {
        long res = 0;
        long[] tmpNums = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            tmpNums[i] = nums[i];
        }
        for (int i = nums.length - 1; i > 0; i--) {
            if(tmpNums[i] >= tmpNums[i - 1]){
                tmpNums[i - 1] += tmpNums[i];
            }
            res = Math.max(res, tmpNums[i]);
        }
        return Math.max(res, tmpNums[0]);
    }

    /**
     * 按照题目要求来看，组合数组一定是从长度为 1 开始，最大到达长度 n
     * 那么直接将 usageLimits 排序处理
     * 数字的出现次数一定是逐一递增的
     * 即有些数字只会出现一次，也就是最长的数组中
     * 有些数字会出现在所有数组里（如果出现次数足够的话
     * 那按照这个思路似乎是可以收集到一个固定的规律
     * 如有数字中途用完了，那就使用别的数字代偿，直到无法组合出更长的组合
     *
     * TODO 还是需要预留才能取得预期答案
     *      按照我得逻辑 [2,2,2] 就只能得到 2，但如果预留，就能得到 3（实际上预期答案也是这个
     *      算了饿了，煮饭去了
     *
     */
    public int maxIncreasingGroups(List<Integer> usageLimits) {
        usageLimits.sort(Comparator.reverseOrder());
        Integer[] arr = usageLimits.toArray(new Integer[0]);
        int res = 0;
        int pre = 0;
        for (int i = 0; i < arr.length; i++) {
            int cur = 0;
            for (int j = i; j >= 0; j--) {
                if(arr[j] > 0){
                    arr[j] --;
                    cur ++;
                }
            }
            if(cur > pre){
                res ++;
                pre = cur;
            }else{
                int ii = i + 1;
                while(cur <= pre && ii < arr.length){
                    if(arr[ii] > 0){
                        arr[ii] --;
                        cur ++;
                    }
                }
                if(cur > pre){
                    res ++;
                    pre = cur;
                }else{
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        final Solution demo = new Solution();

//        System.out.println(demo.maxArrayValue(Common.string2IntArr("[2,3,7,9,3]")));
//        System.out.println(demo.maxArrayValue(Common.string2IntArr("[91,50]")));

        System.out.println(demo.maxIncreasingGroups(Common.string2IntList("[1,1]")));
    }
}
