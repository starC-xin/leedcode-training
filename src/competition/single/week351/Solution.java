package competition.single.week351;

import utils.Common;

import java.util.*;

/**
 * 2023/6/25
 *
 * @author x.z
 */
public class Solution {

    public int countBeautifulPairs(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int n = String.valueOf(nums[i]).charAt(0) - '0';
                int m = String.valueOf(nums[j]).charAt(String.valueOf(nums[j]).length() - 1) - '0';

                int min = Math.min(n, m);
                int max = Math.max(n, m);
                boolean flag = true;
                if(min == 1){
                    sum ++;
                    continue;
                }
                if(max % min != 0){
                    for (int k = 2; k <= min / 2; k++) {
                        if(min % k == 0 && max % k == 0){
                            flag = false;
                            break;
                        }
                    }
                    if(flag){
                        sum ++;
                    }
                }
            }
        }
        return sum;
    }

    /**
     * 第二题
     * 这是个什么操作卧槽
     */
    public int makeTheIntegerZeroAnswer0(int num1, int num2) {
        for (int i = 1; i < 40; i++) {
            if (num1 - i * num2 >= i && Long.bitCount(num1 - i * num2) <= i) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 第二题
     * 说实话这个第一眼看过来也没怎么懂
     */
    public int makeTheIntegerZeroAnswer1(int num1, int num2) {
        for(int i = 1; i <= 100; i++){
            long s = num1;
            s -= 1L*i*num2;
            if(s < 0) continue;
            int cnt = 0;
            for(int j = 0; j < 60; j++){
                if((s&(1L<<j)) > 0){
                    cnt++;
                }
            }
            if(cnt <= i && s >= i){
                return i;
            }
        }
        return -1;
    }


    /**
     * TODO 思路，两个 1 之间，只有 n+1 种划分方式（n 表示间隔几个 0）
     *      然后每两对 1 之间直接使用 n*m 计算划分方式（n代表前一对1的划分方式，m代表后一对
     */
    public int numberOfGoodSubarraySplits(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int pre = nums[0];
        int countZero = 0;
        int hasOne = pre == 1 ? 1 : 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] == 1){
                hasOne ++;
            }
            if(pre == 0){
                pre = nums[i];
            }else{
                if(nums[i] == 1){
                    list.add(countZero);
                    countZero = 0;
                }else{
                    countZero ++;
                }
            }
        }
        if(hasOne == 0 || hasOne == 1){
            return hasOne;
        }
        long sum = 1;
        final long v = (long)Math.pow(10, 9) + 7;
        for (Integer item : list) {
            sum = (sum * (item + 1)) % v;
        }
        return (int)sum;
    }

    /**
     * RL 才能碰撞，其余情况不讨论
     * TODO 可惜了，没能在结束前提交代码，过，确实是过了
     */
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        Integer[] positionsI = new Integer[positions.length];
        for (int i = 0; i < positionsI.length; i++) {
            positionsI[i] = i;
        }
        Arrays.sort(positionsI, Comparator.comparing(item -> positions[item]));
        Stack<Integer> stack = new Stack<>();
        Stack<Character> stackDir = new Stack<>();
        for (int i = 0; i < positionsI.length; i++) {
            final char c = directions.charAt(positionsI[i]);
            if(stack.isEmpty()){
                stack.push(i);
                stackDir.push(c);
                continue;
            }
            if(c == 'L'){
                boolean flag = true;
                while(!stackDir.isEmpty() && stackDir.peek() == 'R'){
                    Integer pop = stack.peek();
                    if(healths[positionsI[pop]] == healths[positionsI[i]]){
                        stack.pop();
                        stackDir.pop();
                        flag = false;
                        break;
                    }else if(healths[positionsI[pop]] > healths[positionsI[i]]){
                        healths[positionsI[pop]] --;
                        flag = false;
                        break;
                    }else{
                        stack.pop();
                        stackDir.pop();
                        healths[positionsI[i]] --;
                    }
                }
                if(flag){
                    stack.push(i);
                    stackDir.push(c);
                }
            }else{
                stack.push(i);
                stackDir.push(c);
            }
        }

        List<Integer> res = new ArrayList<>();
        if(stack.isEmpty()){
            return res;
        }
        int[] tmp = new int[stack.size()];
        int i = 0;
        while(!stack.isEmpty()){
            tmp[i ++] = positionsI[stack.pop()];
        }
        Arrays.sort(tmp);
        for (int i1 : tmp) {
            res.add(healths[i1]);
        }

        return res;
    }

    public static void main(String[] args) {
        final Solution demo = new Solution();

//        System.out.println(demo.countBeautifulPairs(Common.string2IntArr("[31,25,72,79,74]")));
//        System.out.println(demo.countBeautifulPairs(Common.string2IntArr("[2,5,1,4]")));

//        System.out.println(demo.numberOfGoodSubarraySplits(Common.string2IntArr("[0,1,0]")));

        final int[] position = Common.string2IntArr("[11,44,16]");
        final int[] hea = Common.string2IntArr("[1,20,17]");
        final String dir = "RLR";
        System.out.println(demo.survivedRobotsHealths(position, hea, dir));
    }
}
