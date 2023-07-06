package competition.single.week348;

import utils.Common;

import java.util.*;

/**
 * 2023/6/4
 *
 * @author x.z
 */
public class Solution {
    /**
     * 6462. 最小化字符串长度 显示英文描述
     * 通过的用户数0
     * 尝试过的用户数0
     * 用户总通过次数0
     * 用户总提交次数0
     * 题目难度Medium
     * 给你一个下标从 0 开始的字符串 s ，重复执行下述操作 任意 次：
     *
     * 在字符串中选出一个下标 i ，并使 c 为字符串下标 i 处的字符。并在 i 左侧（如果有）和 右侧（如果有）各 删除 一个距离 i 最近 的字符 c 。
     * 请你通过执行上述操作任意次，使 s 的长度 最小化 。
     *
     * 返回一个表示 最小化 字符串的长度的整数。
     */
    public int minimizedStringLength(String s) {
//        简单去重
        boolean[] chs = new boolean[26];
        for (char c : s.toCharArray()) {
            if(!chs[c - 'a']){
                chs[c - 'a'] = true;
            }
        }
        int cns = 0;
        for (boolean ch : chs) {
            if(ch){
                cns ++;
            }
        }
        return cns;
    }


    /**
     * 6424. 半有序排列 显示英文描述
     * 通过的用户数0
     * 尝试过的用户数0
     * 用户总通过次数0
     * 用户总提交次数0
     * 题目难度Easy
     * 给你一个下标从 0 开始、长度为 n 的整数排列 nums 。
     *
     * 如果排列的第一个数字等于 1 且最后一个数字等于 n ，则称其为 半有序排列 。你可以执行多次下述操作，直到将 nums 变成一个 半有序排列 ：
     *
     * 选择 nums 中相邻的两个元素，然后交换它们。
     * 返回使 nums 变成 半有序排列 所需的最小操作次数。
     *
     * 排列 是一个长度为 n 的整数序列，其中包含从 1 到 n 的每个数字恰好一次。
     */
    public int semiOrderedPermutation(int[] nums) {
//        定位最小1和最大n，计算相邻交换次数
        int minI = -1;
        int maxI = -1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 1){
                minI = i;
            }else if(nums[i] == nums.length){
                maxI = i;
            }
            if(-1 != minI && -1 != maxI){
                break;
            }
        }

        int tmp = minI + (nums.length - 1 - maxI);
        if(minI < maxI){
            return tmp;
        }else {
//            maxI < minI
            return tmp - 1;
        }
    }

    /**
     * 6472. 查询后矩阵的和 显示英文描述
     * 通过的用户数177
     * 尝试过的用户数318
     * 用户总通过次数177
     * 用户总提交次数372
     * 题目难度Medium
     * 给你一个整数 n 和一个下标从 0 开始的 二维数组 queries ，其中 queries[i] = [typei, indexi, vali] 。
     *
     * 一开始，给你一个下标从 0 开始的 n x n 矩阵，所有元素均为 0 。每一个查询，你需要执行以下操作之一：
     *
     * 如果 typei == 0 ，将第 indexi 行的元素全部修改为 vali ，覆盖任何之前的值。
     * 如果 typei == 1 ，将第 indexi 列的元素全部修改为 vali ，覆盖任何之前的值。
     * 请你执行完所有查询以后，返回矩阵中所有整数的和。
     */
    public long matrixSumQueries(int n, int[][] queries) {
//        TODO 先暴力试试
//        TODO 暴力行不通，卡在case 2676
//        int[][] ns = new int[n][n];
//        for (int[] query : queries) {
//            boolean flag = query[0] == 0;
//            for (int i = 0; i < n; i++) {
//                if(flag){
//                    ns[query[1]][i] = query[2];
//                }else{
//                    ns[i][query[1]] = query[2];
//                }
//            }
//        }
//
//        long sum = 0;
//        for (int[] line : ns) {
//            for (int i : line) {
//                sum += i;
//            }
//        }
//        return sum;

//        非暴力，依然超时，2678
        Map<Integer, TmpNode> mapRow = new HashMap<>();
        Map<Integer, TmpNode> mapCol = new HashMap<>();

        for (int[] query : queries) {
            final TmpNode tmpNode = new TmpNode();
            tmpNode.val = query[2];
            tmpNode.count = n;
            tmpNode.at = new boolean[n];
            Map<Integer, TmpNode> flagMap = query[0] == 0 ? mapRow : mapCol;
            Map<Integer, TmpNode> unFlagMap = query[0] == 0 ? mapCol : mapRow;
            flagMap.put(query[1], tmpNode);
            unFlagMap.forEach((k, v) -> {
                if(!v.at[query[1]]){
                    v.at[query[1]] = true;
                    v.count --;
                }
            });
        }

        long sum = 0;
        for (Map.Entry<Integer, TmpNode> entry : mapRow.entrySet()) {
            final TmpNode value = entry.getValue();
            if(value.count <= 0){
                continue;
            }
            sum += (long) value.val * value.count;
        }
        for (Map.Entry<Integer, TmpNode> entry : mapCol.entrySet()) {
            final TmpNode value = entry.getValue();
            if(value.count <= 0){
                continue;
            }
            sum += (long) value.val * value.count;
        }
        return sum;
    }
    static class TmpNode{
        private int val;
        private boolean[] at;
        private int count;
    }

    /**
     * 看看别人的ac
     * TODO 从尾部开始往前遍历
     *      最后添加的操作一定是满行满列
     *      往前遍历的过程中，一定是逐一降低，最终变成0
     *      即越早的操作，越为无用操作
     *      主要是逆序思维
     */
    public long matrixSumQueriesAnswer(int n, int[][] queries) {
        HashSet<Integer> row = new HashSet<>(), col = new HashSet<>();
        long sum = 0;
        for (int i = queries.length - 1; i >= 0; i--) {
            sum += !(queries[i][0] == 0 ? row : col).add(queries[i][1]) ? 0
                    : queries[i][2] * (n - (queries[i][0] == 0 ? col : row).size());
        }
        return sum;
    }


    public static void main(String[] args) {
        final Solution demo = new Solution();

//        System.out.println(demo.semiOrderedPermutation(Common.string2IntArr("[2,1,4,3]")));

//        int n = 3;
//        int[][] queries = Common.string2IntIntArr(" [[0,0,1],[1,2,2],[0,2,3],[1,0,4]]");

//        case 错误
//        8
//        [[0,6,30094],[0,7,99382],[1,2,18599],[1,3,49292],[1,0,81549],[1,1,38280],[0,0,19405],[0,4,30065],[1,4,60826],[1,5,9241],[0,5,33729],[0,1,41456],[0,2,62692],[0,3,30807],[1,7,70613],[1,6,9506],[0,5,39344],[1,0,44658],[1,1,56485],[1,2,48112],[0,6,43384]]
        int n = 8;
        int[][] queries = Common.string2IntIntArr("[[0,6,30094],[0,7,99382],[1,2,18599],[1,3,49292],[1,0,81549],[1,1,38280],[0,0,19405],[0,4,30065],[1,4,60826],[1,5,9241],[0,5,33729],[0,1,41456],[0,2,62692],[0,3,30807],[1,7,70613],[1,6,9506],[0,5,39344],[1,0,44658],[1,1,56485],[1,2,48112],[0,6,43384]]");


        System.out.println(demo.matrixSumQueriesAnswer(n, queries));
    }

}
