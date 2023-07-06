package competition.doubl.week105;

import utils.Common;

import java.util.*;

/**
 * 2023/5/27
 *
 * @author x.z
 */
public class Solution {
    /**
     * 6395. 购买两块巧克力 显示英文描述
     * 通过的用户数0
     * 尝试过的用户数0
     * 用户总通过次数0
     * 用户总提交次数0
     * 题目难度Easy
     * 给你一个整数数组 prices ，它表示一个商店里若干巧克力的价格。同时给你一个整数 money ，表示你一开始拥有的钱数。
     *
     * 你必须购买 恰好 两块巧克力，而且剩余的钱数必须是 非负数 。同时你想最小化购买两块巧克力的总花费。
     *
     * 请你返回在购买两块巧克力后，最多能剩下多少钱。如果购买任意两块巧克力都超过了你拥有的钱，请你返回 money 。注意剩余钱数必须是非负数。
     */
    public int buyChoco(int[] prices, int money) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int price : prices) {
            pq.offer(price);
        }
        final Integer one = pq.poll();
        final Integer two = pq.poll();
        if(one + two > money){
            return money;
        }else{
            return money - one - two;
        }
    }

    /**
     * 6394. 字符串中的额外字符 显示英文描述
     * 通过的用户数0
     * 尝试过的用户数0
     * 用户总通过次数0
     * 用户总提交次数0
     * 题目难度Medium
     * 给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary 。你需要将 s 分割成若干个 互不重叠 的子字符串，每个子字符串都在 dictionary 中出现过。s 中可能会有一些 额外的字符 不在任何子字符串中。
     *
     * 请你采取最优策略分割 s ，使剩下的字符 最少 。
     *
     * TODO 没有思路
     */
    public int minExtraChar(String s, String[] dictionary) {
        int min = Integer.MAX_VALUE;
        for (String s1 : dictionary) {
            if(s1.length() < min){
                min = s1.length();
            }
        }
        return dfs(new String[]{s}, dictionary, s.length(), s.length(), s.length(), min);
    }

    public int dfs(String[] curStr, String[] dic, int curLen, int min, int curMax, int hasMin){
        if(curMax < hasMin){
            return Math.min(curLen, min);
        }
        for (String d : dic) {
            for (String s : curStr) {
                if(d.length() > s.length()){
                    continue;
                }
                if(s.contains(d)){
                    final String[] ds = s.split("d");
                }
            }
        }
        return 0;
    }

    /**
     * TODO 抄的第二题，看看dp动规
     *      说实话，动规到现在都是似懂非懂
     */
    public int minExtraChar1(String s, String[] dictionary) {
        HashSet<String> set = new HashSet<>(Arrays.asList(dictionary));
        int[] dp = new int[s.length() + 1];
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < i; j++) {
                if (set.contains(s.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * 6393. 一个小组的最大实力值 显示英文描述
     * 给你一个下标从 0 开始的整数数组 nums ，它表示一个班级中所有学生在一次考试中的成绩。老师想选出一部分同学组成一个 非空 小组，且这个小组的 实力值 最大，如果这个小组里的学生下标为 i0, i1, i2, ... , ik ，那么这个小组的实力值定义为 nums[i0] * nums[i1] * nums[i2] * ... * nums[ik​] 。
     *
     * 请你返回老师创建的小组能得到的最大实力值为多少。
     * @param nums
     * @return
     */
    public long maxStrength(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        long sum = 0;
        int absMin = Integer.MAX_VALUE;
        List<Integer> n = new ArrayList<>();
        for (int num : nums) {
            if(0 == num){
                continue;
            }
            if(num < 0){
                absMin = Math.min(absMin, num * -1);
                n.add(num);
                continue;
            }
            if(0 == sum){
                sum = num;
                continue;
            }
            sum *= num;
        }
        if(n.size() > 0){
            if(n.size() % 2 == 0){
                if(sum == 0){
                    sum = 1;
                }
                for (Integer num : n) {
                    sum *= num;
                }
            }else if(n.size() > 1){
                if(sum == 0){
                    sum = 1;
                }
                boolean flag = true;
                for (int num : n) {
                    if(flag && num * -1 == absMin){
                        flag = false;
                        continue;
                    }
                    sum *= num;
                }
            }
        }
        return sum;
    }

    /**
     * 关于第三题，一个思路相当清晰的解法
     */
    public long maxStrength1(int[] nums) {
        long res = 1;
        Arrays.sort(nums);
        boolean good = false;
        for (int i = 0; i + 1 < nums.length && nums[i + 1] < 0; i++) {
            res *= (long)nums[i] * nums[i + 1];
            i++;
            good = true;
        }
        for (int i = nums.length - 1; i >= 0 && nums[i] > 0; i--) {
            res *= nums[i];
            good = true;
        }
//        常规情况
        if (good) return res;
//        全零选手走这里
        for (int i : nums) {
            if (i == 0) {
                return 0;
            }
        }
//        当且仅当只有一个负数时，走这里
        return nums[0];
    }

    /**
     * 6464. 最大公约数遍历 显示英文描述
     * 通过的用户数417
     * 尝试过的用户数903
     * 用户总通过次数520
     * 用户总提交次数3619
     * 题目难度Hard
     * 给你一个下标从 0 开始的整数数组 nums ，你可以在一些下标之间遍历。对于两个下标 i 和 j（i != j），当且仅当 gcd(nums[i], nums[j]) > 1 时，我们可以在两个下标之间通行，其中 gcd 是两个数的 最大公约数 。
     *
     * 你需要判断 nums 数组中 任意 两个满足 i < j 的下标 i 和 j ，是否存在若干次通行可以从 i 遍历到 j 。
     *
     * 如果任意满足条件的下标对都可以遍历，那么返回 true ，否则返回 false 。
     *
     * TODO 全员的公约数是理解错了
     *      整体思路应该为满足 i<j 这样的下标是否可达
     *      虽然 i<j ，但并不意味着遍历路径一定在 [i, j] 区间
     *      该题考察方向更像是并查集的概念
     *      但我没有具体执行思路
     */
    public boolean canTraverseAllPairs(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if(num < min){
                min = num;
            }
        }
        for (int i = 2; i <= min; i++) {
            boolean flag = true;
            for (int num : nums) {
                if(num % i != 0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final Solution demo = new Solution();

//        System.out.println(demo.maxStrength(Common.string2IntArr("[-4,-5,-4]")));
        System.out.println(demo.minExtraChar1("leetscode", new String[]{"leet", "code", "leetcode"}));
    }
}
