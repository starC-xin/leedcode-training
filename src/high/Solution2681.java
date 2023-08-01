package high;

import utils.Common;
import utils.TimeUtils;

import java.util.Arrays;

/**
 * 2023/8/1
 *
 * @author x.z
 */
public class Solution2681 {
    /**
     * 感觉这道题应该不算难
     * 按照题目的要求，其本身应该可以使用枚举来解决穷举的问题
     * 每次枚举时都得出最大最小值，然后执行计算累计即可
     * TODO 当数据量超过40时，枚举需要的计算量就太大了，承受不住超时的问题
     *      枚举可以作为 30 位以内的快速解决方案，但超过30位就会有较大的负担，很容易超时
     *      这也基本是 int 数据类型的长度极限
     */
    public int sumOfPower(int[] nums) {
        long res = 0;
        Boolean[] briny = new Boolean[nums.length];
        boolean flag = false;
        final int MAX = (int)Math.pow(10, 9) + 1;
        final double v = Math.pow(10, 9) + 7;
        while(brinyAdd(briny)){
            int max = 0;
            int min = MAX;
            for (int i = 0; i < briny.length; i++) {
                if(null == briny[i]){
                    break;
                }
                if(briny[i]){
                    max = Math.max(max, nums[i]);
                    min = Math.min(min, nums[i]);
                }
            }
            res = ((long) ((res + Math.pow(max, 2) * min) % v));
        }
        return (int)res;
    }

    private boolean brinyAdd(Boolean[] briny){
        boolean bool = false;
        for (int i = 0; i < briny.length; i++) {
            if(briny[i] == null){
                briny[i] = true;
                bool = false;
                break;
            }
            if(bool){
                if(briny[i]){
                    briny[i] = false;
                }else{
                    briny[i] = true;
                    bool = false;
                    break;
                }
            }else{
                if(briny[i]){
                    briny[i] = false;
                    bool = true;
                }else{
                    briny[i] = true;
                    break;
                }
            }
        }
        return !bool;
    }

    /**
     * 解题思路链接
     * https://leetcode.cn/problems/power-of-heroes/solutions/2268792/gong-xian-fa-pythonjavacgo-by-endlessche-d4jx/
     *
     * 以数学的思想来实现的一套解题算法，直接
     *
     * 鑫飘零半生，未逢明师，汝若不弃，鑫愿拜为恩师
     */
    static class Solution {
        public int sumOfPower(int[] nums) {
            final long MOD = (long) 1e9 + 7;
            Arrays.sort(nums);
            long ans = 0, s = 0;
            for (long x : nums) { // x 作为最大值
                ans = (ans + x * x % MOD * (x + s)) % MOD; // 中间模一次防止溢出
                s = (s * 2 + x) % MOD; // 递推计算下一个 s
            }
            return (int) ans;
        }
    }

    public static void main(String[] args) {
        final Solution2681 demo = new Solution2681();

        TimeUtils.begin();
        System.out.println(demo.sumOfPower(Common.string2IntArr("[658,489,777,2418,1893,130,2448,178,1128,2149,1059,1495,1166,608,2006,713,1906,2108,680,1348,860,1620,146,2447,1895,1083,1465,2351,1359,1187,906,533,1943,1814,1808,2065,1744,254,1988,1889,1206")));
        TimeUtils.end();
    }

}
