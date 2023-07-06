package easy;

/**
 * 2023/6/27
 *
 * @author x.z
 */
public class Solution409 {
    public int longestPalindrome(String s) {
        int[] chCount = new int[26 * 2];
        int len = 0;
        for (char c : s.toCharArray()) {
            final int i;
            if(c >= 'a' && c <= 'z'){
                i = c - 'a';
            }else{
                i = c - 'A' + 26;
            }
            chCount[i] ++;
            if(chCount[i] % 2 == 0){
                len += 2;
                chCount[i] = 0;
            }
        }
        for (int i : chCount) {
            if(i > 0){
                len ++;
                break;
            }
        }
        return len;
    }
}
