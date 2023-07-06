package easy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 2023/6/17
 *
 * @author x.z
 */
public class Solution228 {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new LinkedList<>();
        if(0 == nums.length){
            return result;
        }
        if(1 == nums.length){
            result.add(nums[0] + "");
            return result;
        }
        int pre = nums[0];
        int begin = nums[0];
        int cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = nums[i];

            if(pre + 1 == cur){
                pre = cur;
                continue;
            }
            if(pre == begin){
                result.add(begin + "");
            }else{
                result.add(begin + "->" + pre);
            }
            begin = cur;
            pre = begin;
        }

        if(cur == begin){
            result.add(begin + "");
        }else{
            result.add(begin + "->" + pre);
        }
        return result;
    }

    /**
     * 示例代码
     * 思路清晰，双指针，以下标替代实际值确实很好处理
     * 而且很快
     */
    public List<String> summaryRangesAnswer(int[] nums) {
        List<String> res = new ArrayList<>();
        // i 初始指向第 1 个区间的起始位置
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            // j 向后遍历，直到不满足连续递增(即 nums[j] + 1 != nums[j + 1])
            // 或者 j 达到数组边界，则当前连续递增区间 [i, j] 遍历完毕，将其写入结果列表。
            if (j + 1 == nums.length || nums[j] + 1 != nums[j + 1]) {
                // 将当前区间 [i, j] 写入结果列表
                StringBuilder sb = new StringBuilder();
                sb.append(nums[i]);
                if (i != j) {
                    sb.append("->").append(nums[j]);
                }
                res.add(sb.toString());
                // 将 i 指向更新为 j + 1，作为下一个区间的起始位置
                i = j + 1;
            }
        }
        return res;
    }
}
