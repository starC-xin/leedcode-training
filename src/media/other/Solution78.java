package media.other;

import utils.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * 2023/6/20
 * 78. 子集
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * TODO 特别注意，使用二进制枚举来解答
 * @author x.z
 */
public class Solution78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = 1 << nums.length;
        for (int i = 0; i < len; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {
                if((i >> j & 1) == 1){
                    list.add(nums[j]);
                }
            }
            result.add(list);
        }
        return result;
    }

    /**
     * 2019年我提交的代码
     * 看起来不像是百度来的，因为有之前解答错误的示例，在错误代码上改进而来
     * 虽然是我自己写的，但我现在看起来依然觉得很神奇
     * 当年我知道什么叫 dfs 吗？
     * 不过思路上还是比较清晰的，看得出来是在使用深度遍历
     * 有没有可能这是当年罗友在我电脑上写的？
     * 如果是这样的话，那我和罗总之间的差距已经是一个无法逾越的鸿沟了
     * 越是回忆就越觉得恐怖
     */
    static class Solution {
        public List<List<Integer>> subsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            dfs(result, new ArrayList<>(), nums, 0);
            return result;
        }

        private void dfs(List<List<Integer>> cache, List<Integer> temp, int[] nums, int index){
            cache.add(new ArrayList<>(temp));
            if(temp.size() == nums.length){
                return;
            }
            for(int i = index; i < nums.length; i ++){
                if(!temp.contains(nums[i])){
                    temp.add(nums[i]);
                    dfs(cache, temp, nums, i + 1);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        final Solution78 demo = new Solution78();

        System.out.println(demo.subsets(Common.string2IntArr("[1,2,3]")));
    }
}
