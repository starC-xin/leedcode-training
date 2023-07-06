package media.other;

import utils.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 2023/6/19
 * 1262. 可被三整除的最大和
 * 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
 * @author x.z
 */
public class Solution1262 {
    public int maxSumDivThree(int[] nums) {
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if(sum % 3 == 0){
            return sum;
        }
        int[] tmp = new int[nums.length];


        return 0;
    }

    private int test(int[] nums){
        int sum = 0;
        List<Integer>[] not = new List[3];
        for (int i = 0; i < not.length; i++) {
            not[i] = new ArrayList<>();
        }
        for (int num : nums) {
            not[num % 3].add(num);
        }
        for (Integer integer : not[0]) {
            sum += integer;
        }
        not[1].sort((a, b) -> b - a);
        not[2].sort((a, b) -> b - a);

        int i = 0;
//        while(i < not[1].size() && i < not[2].size()){
//            sum += not[1].get(i) + not[2].get(i);
//            i++;
//        }
        while(not[1].size() - i >= 3){
            sum += not[1].get(i) + not[1].get(i + 1) + not[1].get(i + 2);
            i += 3;
        }
        i = 0;
        while(not[2].size() - i >= 3){
            sum += not[2].get(i) + not[2].get(i + 1) + not[2].get(i + 2);
            i += 3;
        }
        return sum;
    }

    /**
     * 官方题解
     * TODO 只能说似懂非懂
     */
    public int maxSumDivThreeAnswer(int[] nums) {
        // 使用 v[0], v[1], v[2] 分别表示 a, b, c
        List<Integer>[] v = new List[3];
        for (int i = 0; i < 3; ++i) {
            v[i] = new ArrayList<Integer>();
        }
        for (int num : nums) {
            v[num % 3].add(num);
        }
        Collections.sort(v[1], (a, b) -> b - a);
        Collections.sort(v[2], (a, b) -> b - a);

        int ans = 0;
        int lb = v[1].size(), lc = v[2].size();
        for (int cntb = lb - 2; cntb <= lb; ++cntb) {
            if (cntb >= 0) {
                for (int cntc = lc - 2; cntc <= lc; ++cntc) {
                    if (cntc >= 0 && (cntb - cntc) % 3 == 0) {
                        ans = Math.max(ans, getSum(v[1], 0, cntb) + getSum(v[2], 0, cntc));
                    }
                }
            }
        }
        return ans + getSum(v[0], 0, v[0].size());
    }

    public int getSum(List<Integer> list, int start, int end) {
        int sum = 0;
        for (int i = start; i < end; ++i) {
            sum += list.get(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        final Solution1262 demo = new Solution1262();

        System.out.println(demo.test(Common.string2IntArr("[1,2,3,4,4]")));
    }
}
