package competition.single.week350;

import utils.Common;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2023/6/18
 *
 * @author x.z
 */
public class Solution {
    public int distanceTraveled(int mainTank, int additionalTank) {
        int res = mainTank * 10;
        int count = 0;
        while(additionalTank > 0){
            mainTank -= 5;
            if(mainTank >= 0){
                mainTank ++;
                additionalTank --;
                count ++;
            }else{
                break;
            }
        }
        res += count * 10;
        return res;
    }

    public int findValueOfPartition(int[] nums) {
        Arrays.sort(nums);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 1; i++) {
            final int abs = Math.abs(nums[i] - nums[i + 1]);
            if(abs < res){
                res = abs;
            }
        }
        return res;
    }

    public int specialPerm(int[] nums) {
        return 0;
    }

    /**
     * 第三题说实话看题都没看懂
     * 留一份答案回头看
     */
    public int specialPermAnswer(int[] nums) {
        int dp[][] = new int[1 << nums.length][nums.length], sum = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[1 << i][i] = 1;
        }
        for (int i = 1; i < 1 << nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; (i & 1 << j) == 0 && k < nums.length; k++) {
                    if ((i & 1 << k) > 0 && (nums[j] % nums[k] == 0 || nums[k] % nums[j] == 0)) {
                        dp[i | 1 << j][j] = (dp[i | 1 << j][j] + dp[i][k]) % 1000000007;
                    }
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            sum = (sum + dp[(1 << nums.length) - 1][i]) % 1000000007;
        }
        return sum;
    }


    public int paintWalls(int[] cost, int[] time) {
        Integer[] timeI = new Integer[cost.length];
        for (int i = 0; i < timeI.length; i++) {
            timeI[i] = i;
        }
        Arrays.sort(timeI, (a, b) -> time[b] - time[a]);
//        int res = cost[costI[0]];
//        int free = time[costI[0]];
        int res = 0;
        int free = 0;
        int[][] costTmp = new int[cost.length / 2 + 1][cost.length];
        for (int i = 1; i < costTmp.length; i++) {
            for (int j = 0; j < cost.length; j++) {
                if(costTmp[i - 1][j] > costTmp[i - 1][j]){
                }
            }
        }
        for (int i = 0; i < cost.length; i++) {
//            if(costTmp[i][]){}
            res += cost[timeI[i]];
            free += time[timeI[i]];
        }

//        for (int i = 1; i < timeI.length; i++) {
//            if(free < free - time[timeI[i - 1]] + time[timeI[i]]){
//                free = free - time[timeI[i - 1]] + time[timeI[i]];
//                res = res - cost[timeI[i - 1]] + cost[timeI[i]];
//            }
//            if(free >= 8){
//                break;
//            }
//        }
        return res;
    }

    /**
     * 参考一下
     * 经典背包问题的变形
     */
    public int paintWallsAnswer(int[] cost, int[] time) {
//        dp 数组代表的是刷几面墙的开销，例：dp[4] 就刷四面墙的 cost 开销
//        最终取dp[cost.length] 则是刷完所有墙的开销
        int[] dp = new int[cost.length + 1];
//        初始化开销为最大值方便后续计算
        for (int i = 1; i <= cost.length; i++) {
            dp[i] = 1000000000;
        }
//        计算刷第 i 面墙时的最小开销
        for (int i = 0; i < cost.length; i++) {
            for (int j = cost.length - 1; j >= 0; j--) {
//                自身与最接近刷完墙的开销作比较
                dp[j + 1] = Math.min(
                        dp[j + 1],
//                        j-time(i) 计算在当前时间花费下能够刷完所有墙的最接近时间
                        dp[Math.max(0, j - time[i])] + cost[i]
                );
            }
        }
        return dp[cost.length];
    }

    public static void main(String[] args) {
        final Solution demo = new Solution();

//        [42,8,28,35,21,13,21,35]
//[2,1,1,1,2,1,1,2]
//        我的 8,13,21,21 1+1+2+1
//        实际 21,35 2+2
        int[] cost = Common.string2IntArr("[42,8,28,35,21,13,21,35]");
        int[] time = Common.string2IntArr("[2,1,1,1,2,1,1,2]");
        System.out.println(demo.paintWallsAnswer(cost, time));
    }
}
