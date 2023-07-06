package day;

import java.util.*;

/**
 * 2022/1/21
 *
 * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
 *
 * 每一步，你可以从下标 i 跳到下标：
 *
 * i + 1 满足：i + 1 < arr.length
 * i - 1 满足：i - 1 >= 0
 * j 满足：arr[i] == arr[j] 且 i != j
 * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
 *
 * 注意：任何时候你都不能跳到数组外面。
 *
 * 示例 1：
 * 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
 * 输出：3
 * 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
 *
 * 示例 2：
 * 输入：arr = [7]
 * 输出：0
 * 解释：一开始就在最后一个元素处，所以你不需要跳跃。
 *
 * 示例 3：
 * 输入：arr = [7,6,9,6,9,6,9,7]
 * 输出：1
 * 解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
 *
 * 示例 4：
 * 输入：arr = [6,1,9]
 * 输出：2
 *
 * 示例 5：
 * 输入：arr = [11,22,7,7,7,7,7,7,7,22,13]
 * 输出：3
 *  
 * 提示：
 * 1 <= arr.length <= 5 * 10^4
 * -10^8 <= arr[i] <= 10^8
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jump-game-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author x.c
 */
public class Solution1345 {
    /**
     * 初步思路，从两头同时出发，以第三条为优先策略，执行路径选择
     *          指导两者下标交叉时，再做交叉步数统计
     *          最终得到期望步数
     * 上述思路废弃
     *
     * 新思路使用广度遍历，尝试暴力破解
     * 无果
     *
     * TODO 实在想不出来，最终看了题解，意识到这是一个图
     *      利用图的思路执行广度遍历实现
     *      耗时 75ms
     */
    public int minJumps(int[] arr) {
//        TODO 面向测试用例编程
        if(arr.length == 1){
            return 0;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
//        扫描所有相同值，将其作为待扫描的选项
//        将其视为可到达的图顶点
        for(int i = 0 ; i < arr.length; i++){
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }
//        建立队列存储遍历待访问的图节点
//        TODO 将 ArrayDeque 改为 LinkedList 后耗时减少，
//              源于两者底层实现差异性
//              耗时 68ms
        Queue<int[]> queue = new LinkedList<>();
//        添加起始点位
        queue.offer(new int[]{0, 0});
//        存储已访问的节点，防止重复访问
//        Set<Integer> visitedIndex = new HashSet<>();
//        visitedIndex.add(0);
//        TODO 将原 set 改为 boolean 数组
//              修改后耗时降到 49ms
        boolean[] visitedIndex = new boolean[arr.length];
        visitedIndex[0] = true;
        while(!queue.isEmpty()){
            int[] tempArr = queue.poll();
            int index = tempArr[0], step = tempArr[1];
//            如果已经达到了最后，则返回步数
            if(index == arr.length - 1){
                return step;
            }
            step ++;
//            若有相同值可供跳跃，则逐个遍历，并将其加入备选队列
            if(map.containsKey(arr[index])){
                for(int var : map.get(arr[index])){
//                    若该节点已访问过，则不添加队列
                    if(! visitedIndex[var]){
                        queue.offer(new int[]{var, step});
                        visitedIndex[var] = true;
                    }
                }
//                访问过的键值需要删除，防止多次访问
                map.remove(arr[index]);
            }
//            +1 搜索
            if(index + 1 < arr.length && ! visitedIndex[index + 1]){
                queue.offer(new int[]{index + 1, step});
                visitedIndex[index + 1] = true;
            }
//            -1 搜索
            if(index -1 >= 0 && ! visitedIndex[index - 1]){
                queue.offer(new int[]{index - 1, step});
                visitedIndex[index - 1] = true;
            }
        }
        return -1;
    }

    /**
     * TODO 词条思路废弃
     */
    private int minJumpsSub(int[] arr, int index, int jump, int minIndex){
        if(index == arr.length || index < 0){
            return jump;
        }
        if(index - 1 >= 0 && index > minIndex){
            jump = Math.min(jump, minJumpsSub(arr, index - 1, jump + 1, minIndex));
        }
        if(index + 1 < arr.length){
            jump = Math.min(jump, minJumpsSub(arr, index + 1, jump + 1, index + 1));
        }
        for(int i = index + 1; i < arr.length; i++){
            if(arr[i] == arr[index]){
                jump = Math.min(jump, minJumpsSub(arr, i, jump + 1, index + 1));
            }
        }
        return jump;
    }

    public static void main(String[] args){
        long start = System.currentTimeMillis();
//        System.out.println(new Solution1345().minJumps(new int[]{100,-23,-23,404,100,23,23,23,3,404}));
        System.out.println(new Solution1345().minJumps(new int[]{100,-23,-23,404,100,23,23,23,3,404}));
        System.out.println(System.currentTimeMillis() - start);
    }
}
