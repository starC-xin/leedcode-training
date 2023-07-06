package day;

import java.util.HashMap;

/**
 * 2021/10/26
 *
 * @author ZhaoXin
 */
public class Solution496 {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< nums2.length; i++){
            int ii = i + 1;
            for(; ii < nums2.length; ii++){
                if(nums2[i] < nums2[ii]){
                    map.put(nums2[i], nums2[ii]);
                    break;
                }
            }
            if(ii == nums2.length){
                map.put(nums2[i], -1);
            }
        }

        for(int i = 0; i < nums1.length; i++){
            result[i] = map.get(nums1[i]);
        }
        return result;
    }
}
