package easy;

import java.util.Arrays;

/**
 * 2023/6/28
 *
 * @author x.z
 */
public class Solution459 {
    public boolean repeatedSubstringPattern(String s) {
        if (s.length() < 2) {
            return false;
        }
        String sub = "";
        final char[] chs = s.toCharArray();
        for (int i = 0; i < chs.length / 2; i++) {
            char c = chs[i];
            sub += c;
            if(s.length() % sub.length() == 0){
                if(s.split(sub).length == 0){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 官解思路和我是一致的，只是解法不同
     */
    public boolean repeatedSubstringPatternAnswer(String s) {
        int n = s.length();
        for (int i = 1; i * 2 <= n; ++i) {
            if (n % i == 0) {
                boolean match = true;
                for (int j = i; j < n; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("abdabdabd".split("abd").length);
    }
}
