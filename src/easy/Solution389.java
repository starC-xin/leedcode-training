package easy;

/**
 * 2023/6/26
 *
 * @author x.z
 */
public class Solution389 {
    public char findTheDifference(String s, String t) {
        int[] chs = new int[26];
        for (char c : s.toCharArray()) {
            chs[c - 'a'] ++;
        }
        char res = 'a';
        for (char c : t.toCharArray()) {
            chs[c - 'a'] --;
            if(chs[c - 'a'] < 0){
                res = c;
                break;
            }
        }
        return res;
    }
}
