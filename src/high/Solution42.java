package high;

import utils.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * 2024/01/26
 * 42. 接雨水
 * 困难
 * 相关标签
 * 相关企业
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * @author x.z
 */
public class Solution42 {

    /**
     * 此处整理思路：
     *   首先预期形状均为规则矩形围起来的空间，所以不考虑积水会有上下层的关系
     *   1、然后考虑接雨水的多少，是取决于左右两边矩形的高低差，以及左右两边的距离差
     *   在 1 的条件下，减去中间的矩形所占体积，即为一个围起来的空间可以容纳的雨水体积
     *
     * 那么做题思路就是：找寻有高低差的矩形 -> 计算矩形之间体积 -> 减去中间因矩形被占用的体积 -> 累计前述所有体积
     * 关于边界的搜寻思路的话：
     *   第一个边界可以按照贪心来搜，即一直搜索，直到找到第一个后面存在比它矮的矩形
     *   第二个边界则是按照能形成一个完整积水区间的原则来搜寻
     */
    public int trap(int[] height) {
//        越过起初无用区间
        int begin = 0;
        for (int i = 0; i < height.length; i++) {
            if(0 != height[i]){
                begin = i;
                break;
            }
        }

//        存储连续递减区间的顶点
        List<Integer> begins = new ArrayList<>();
        int bottom = -1;
        for (int i = begin + 1; i < height.length; i++) {
            if(height[i] < height[begin]){
                begins.add(begin);
//                判断严格连续递减区间
                do{
                    begin = i;
                    i ++;
                }while(i < height.length && height[i] < height[begin]);
                bottom = begin;
            }
            begin = i;
        }

//        尾部判断
        if(bottom < height.length - 1){
            begins.add(height.length - 1);
        }

//        如果是一个连续递减序列，那说明本序列根本没有凹槽
        if(begins.size() <= 1){
            return 0;
        }

//        开始累计
        int res = 0;
        begin = begins.get(0);
        for (int i = 1; i < begins.size(); i++) {
            int end = begins.get(i);
            if(height[end] < height[begin] && i + 1 < begins.size()){
                int ii = i + 1;
                int tmpI = begins.get(ii);
//                搜寻第二个边界
//                尝试寻找一个不低于begin的边界，若没有则选择一个不低于end的边界
                while(height[tmpI] < height[begin]){
                    if(height[tmpI] >= height[end]){
                        end = tmpI;
                        i = ii;
                    }
                    ii ++;
                    if(ii >= begins.size()){
                        break;
                    }
                    tmpI = begins.get(ii);
                }
                if(height[tmpI] >= height[begin]){
                    end = tmpI;
                    i = ii;
                }
            }

//            累计凹槽体积
            int min = Math.min(height[end], height[begin]);
            for (int j = begin + 1; j < end; j++) {
                if(height[j] < min){
                    res += min - height[j];
                }
            }

            begin = end;
        }

        return res;
    }

    /**
     * 官方题解，留于此处一共参考
     * 采用动态规划，朴素的对整个数组扫描两次
     * 两次分别是从左扫描和从右扫描，分别记录下标 i 所在位置的临近最大高度
     * 累计时，对与每一个下标 i ，取上述的两次结果中的较小值，减去当前 i 位置的高度，即为累计值
     *
     * 这里看图会非常明显，“取较小值减去当前位置的高度”，其实就是在取两次结果在数轴上的叠加部分
     * 官解链接：https://leetcode.cn/problems/trapping-rain-water/solutions/692342/jie-yu-shui-by-leetcode-solution-tuvc/
     */
    public int trap1(int[] height){
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; ++i) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        final Solution42 demo = new Solution42();

        String[] cases = {
                "[0,1,0,2,1,0,1,3,2,1,2,1]",
                "[4,2,0,3,2,5]",
                "[6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3]",
                "[9,6,8,8,5,6,3]",
                "[8,8,1,5,6,2,5,3,3,9]",
                "[9,6,1,4,3,8,3,0,8,3]"
        };

        int[] answers = {
                6,
                9,
                83,
                3,
                31,
                31,
        };

        for (int i = 0; i < cases.length; i++) {
            System.out.print(demo.trap(Common.string2IntArr(cases[i])));
            System.out.println(", answer: " + answers[i]);
        }

    }
}
