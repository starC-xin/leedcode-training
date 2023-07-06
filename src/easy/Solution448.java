package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 2023/6/28
 *
 * @author x.z
 */
public class Solution448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        boolean[] set = new boolean[nums.length + 1];
        for (int num : nums) {
            set[num] = true;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 1; i < set.length; i++) {
            if(!set[i]){
                res.add(i);
            }
        }
        return res;
    }
}
