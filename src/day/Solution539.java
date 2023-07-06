package day;

import java.util.Arrays;
import java.util.List;

/**
 * 2022/1/18
 *
 * 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 *
 *  
 *
 * 示例 1：
 * 输入：timePoints = ["23:59","00:00"]
 * 输出：1
 *
 * 示例 2：
 * 输入：timePoints = ["00:00","23:59","00:00"]
 * 输出：0
 *  
 * 提示：
 * 2 <= timePoints.length <= 2 * 104
 * timePoints[i] 格式为 "HH:MM"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-time-difference
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution539 {

    /**
     * 初步思路：10^4以内，可以尝试暴力破解，若是无法破解再使用dp破
     *
     * 74ms
     * 后续优化方向：先预遍历一遍，将所有String转为int分钟，然后执行比较，可以直接避免重复转化
     *
     * 在优化：转换完成后可以快排一次，直接计算相邻的最小值，若遇到 0，则直接退出，无需再做计算
     */
    public int findMinDifference(List<String> timePoints) {
        int min = 1440;
//        若超过这个数量，则说明必定存在相同的时刻，此时存在最小时间0
        if(timePoints.size() > 1440){
            return 0;
        }
        for(int i = 0; i < timePoints.size(); i ++){
            String time1 = timePoints.get(i);
            for(int j = i + 1; j < timePoints.size(); j ++){
                int temp = calc(time1, timePoints.get(j));
                if(temp < min){
                    min = temp;
                }
            }
        }
        return min;
    }

    /**
     * 此方法未完成，有bug，暂未提交
     */
    public int findMinDifference1(List<String> timePoints) {
//        若超过这个数量，则说明必定存在相同的时刻，此时存在最小时间0
        if(timePoints.size() > 1440){
            return 0;
        }
        int min = 1440;
        int[] timePointsInt = new int[timePoints.size() + 1];
        for(int i = 0; i < timePoints.size(); i++){
            String var = timePoints.get(i);
            timePointsInt[i] = Integer.parseInt(var.substring(0, 2)) * 60 + Integer.parseInt(var.substring(3));
        }
        timePointsInt[timePointsInt.length - 1] = timePointsInt[0] + 24 * 60;
        Arrays.sort(timePointsInt);
        for(int i = 0; i < timePointsInt.length - 1; i++){
            int temp = timePointsInt[i + 1] - timePointsInt[i];
            if(temp > 60 * 12){
                temp = 24 * 60 - temp;
            }
            min = Math.min(min, temp);
            if(min == 0){
                return 0;
            }
        }
        return min;
    }

    /**
     * 两个时间的差值，最大为12h，即 60*12 分钟
     * 超过该数值，则需要根据24小时取较小的那部分
     * @return 分钟数
     */
    private int calc(String time1, String time2){
        int min1 = Integer.parseInt(time1.substring(0, 2)) * 60 + Integer.parseInt(time1.substring(3));
        int min2 = Integer.parseInt(time2.substring(0, 2)) * 60 + Integer.parseInt(time2.substring(3));
        int result = Math.abs(min1 - min2);
        if(result > 60 * 12){
            result = 24 * 60 - result;
        }
        return result;
    }
}
