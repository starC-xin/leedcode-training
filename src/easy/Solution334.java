package easy;

/**
 * 2023/8/7
 *
 * @author x.z
 */
public class Solution334 {
    /**
     * 水题
     */
    public void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char c = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = c;
        }
    }
}
