package easy;

import java.util.Arrays;

/**
 * 2023/6/28
 *
 * @author x.z
 */
public class Solution455 {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int count = 0;
        for (int i = 0, j = 0; i < g.length && j < s.length; i++) {
            while(j < s.length){
                if(g[i] <= s[j ++]){
                    count ++;
                    break;
                }
            }
        }
        return count;
    }
}
