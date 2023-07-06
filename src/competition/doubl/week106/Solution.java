package competition.doubl.week106;

import java.util.Arrays;
import java.util.List;

/**
 * 2023/6/10
 *
 * @author x.z
 */
public class Solution {
    /**
     * 6461. 判断一个数是否迷人 显示英文描述
     * 通过的用户数0
     * 尝试过的用户数0
     * 用户总通过次数0
     * 用户总提交次数0
     * 题目难度Easy
     * 给你一个三位数整数 n 。
     *
     * 如果经过以下修改得到的数字 恰好 包含数字 1 到 9 各一次且不包含任何 0 ，那么我们称数字 n 是 迷人的 ：
     *
     * 将 n 与数字 2 * n 和 3 * n 连接 。
     * 如果 n 是迷人的，返回 true，否则返回 false 。
     *
     * 连接 两个数字表示把它们首尾相接连在一起。比方说 121 和 371 连接得到 121371 。
     * @param n
     * @return
     */
    public boolean isFascinating(int n) {
        int[] count = new int[10];
        String str = n + "" + 2 * n + "" + 3 * n;
        for (char c : str.toCharArray()) {
            final int i = c - '0';
            if(i == 0){
                return false;
            }
            count[i]++;
            if(count[i] > 1){
                return false;
            }
        }
        for (int i = 1; i < count.length; i++) {
            if(count[i] != 1){
                return false;
            }
        }
        return true;
    }

    /**
     * 6425. 找到最长的半重复子字符串 显示英文描述
     * 通过的用户数0
     * 尝试过的用户数0
     * 用户总通过次数0
     * 用户总提交次数0
     * 题目难度Medium
     * 给你一个下标从 0 开始的字符串 s ，这个字符串只包含 0 到 9 的数字字符。
     *
     * 如果一个字符串 t 中至多有一对相邻字符是相等的，那么称这个字符串是 半重复的 。
     *
     * 请你返回 s 中最长 半重复 子字符串的长度。
     *
     * 一个 子字符串 是一个字符串中一段连续 非空 的字符。
     * @param s
     * @return
     */
    public int longestSemiRepetitiveSubstring(String s) {
        Character pre = null;
        int max = 0;
        int curC = 0;
        int tmpPre = 0;
        boolean flag = true;
        for (char c : s.toCharArray()) {
            if(null == pre){
                pre = c;
                curC = 1;
                continue;
            }
            if (c == pre) {
                if (flag) {
                    flag = false;
                } else if (max < curC) {
                    max = curC;
                }
                curC -= tmpPre;
                tmpPre = curC;
            }
            curC ++;
            pre = c;
        }
        return max == 0 ? curC : Math.max(max, curC);
    }

    /**
     * 6426. 移动机器人 显示英文描述
     * 通过的用户数0
     * 尝试过的用户数0
     * 用户总通过次数0
     * 用户总提交次数0
     * 题目难度Medium
     * 有一些机器人分布在一条无限长的数轴上，他们初始坐标用一个下标从 0 开始的整数数组 nums 表示。当你给机器人下达命令时，它们以每秒钟一单位的速度开始移动。
     *
     * 给你一个字符串 s ，每个字符按顺序分别表示每个机器人移动的方向。'L' 表示机器人往左或者数轴的负方向移动，'R' 表示机器人往右或者数轴的正方向移动。
     *
     * 当两个机器人相撞时，它们开始沿着原本相反的方向移动。
     *
     * 请你返回指令重复执行 d 秒后，所有机器人之间两两距离之和。由于答案可能很大，请你将答案对 109 + 7 取余后返回。
     *
     * 注意：
     *
     * 对于坐标在 i 和 j 的两个机器人，(i,j) 和 (j,i) 视为相同的坐标对。也就是说，机器人视为无差别的。
     * 当机器人相撞时，它们 立即改变 它们的前进时间，这个过程不消耗任何时间。
     * 当两个机器人在同一时刻占据相同的位置时，就会相撞。
     *
     * 例如，如果一个机器人位于位置 0 并往右移动，另一个机器人位于位置 2 并往左移动，下一秒，它们都将占据位置 1，并改变方向。再下一秒钟后，第一个机器人位于位置 0 并往左移动，而另一个机器人位于位置 2 并往右移动。
     *
     * 例如，如果一个机器人位于位置 0 并往右移动，另一个机器人位于位置 1 并往左移动，下一秒，第一个机器人位于位置 0 并往左行驶，而另一个机器人位于位置 1 并往右移动。
     * TODO 说实话，搞不定，按照我得思路，一定是超时的，时间复杂度太大
     * TODO 重新想了一下，碰撞后相反其实可以理解为机器人互相穿透行驶（因为机器人视为无差别的
     *      但是超时了，得琢磨一下，思路肯定是没有问题的
     *      时间复杂度 O(n^2) 这怎么能超时的呢？两两数对求和要求严格遍历，这没法优化吧，最终落点理论上也不可能存在相同值
     *      好像最终落点可能存在相同，但这应该不会是普遍情况，目前卡在2557，距离2271还有14个case，不可能在检测这玩意吧？
     *      等着看答案吧
     */
    public int sumDistance(int[] nums, String s, int d) {
        long sum = 0;
        final long v = ((long) (Math.pow(10, 9) + 7));
        for (int i = 0; i < s.toCharArray().length; i++) {
            if (s.charAt(i) == 'L') {
                nums[i] -= d;
            }else{
                nums[i] += d;
            }
            if(i > 0){
                for (int j = i - 1; j >= 0; j--) {
                    sum += Math.abs(nums[i] - nums[j]);
                    sum %= v;
                }
            }
        }
        return ((int) sum);
    }

    /**
     * TODO 啊？
     */
    public int sumDistanceAnswer(int[] nums, String s, int d) {
        long after[] = new long[nums.length], sum = 0, k = 0;
        for (int i = 0; i < nums.length; i++) {
            after[i] = nums[i] + (s.charAt(i) == 'R' ? 1L : -1L) * d;
        }
//        TODO 这一步两两数对求和很惊艳说实话，学到了
//             直接把o(n^2)强行压缩到o(n)
//             此处的压缩思路为，将每个数对拆分，分为 + 与 -
//             + ：对于下标位于 i 的数，需要累加 i 次，作为加数
//             - ：对于下标位于 i 的数，总是需要累计 [0,i] 的和，然后作为被减数
//        排序保证后续计算总是大数减去小数
        Arrays.sort(after);
        for (int i = 0; i < nums.length; k += after[i++]) {
//            这一步是怎么回事哦？
//            这一步数数学推导，两两数对求和公式
            sum = (sum + i * after[i] - k) % 1000000007;
        }
        return (int) sum;
    }

    public List<Integer> goodSubsetofBinaryMatrix(int[][] grid) {
        return null;
    }

    public static void main(String[] args) {
        final Solution demo = new Solution();

        System.out.println(demo.longestSemiRepetitiveSubstring("52233133123412344"));
        System.out.println(demo.longestSemiRepetitiveSubstring("0001"));
    }
}
