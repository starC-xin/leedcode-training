package media.other;

import java.util.Arrays;

/**
 * 2023/8/8
 *
 * @author x.z
 */
public class Solution1749 {
    /**
     * 说实话，动规方便的题，一遇到就拉胯
     * 这是是在想不出来的情况下，看了下灵神的题解后写出来的
     */
    public int maxAbsoluteSum(int[] nums) {
        int ans = 0;
        int minC = 0;
        int maxC = 0;
        for (int num : nums) {
            maxC = Math.max(maxC, 0) + num;
            minC = Math.min(minC, 0) + num;
            ans = Math.max(ans, Math.max(maxC, - minC));
        }
        return ans;
    }
}
