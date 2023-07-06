package easy;

/**
 * 2023/6/26
 *
 * @author x.z
 */
public class Solution392 {
    public boolean isSubsequence(String s, String t) {
        for (int i = 0, j = 0; i < s.length(); i++, j++) {
            final char c = s.charAt(i);
            while(j < t.length() && t.charAt(j) != c){
                j ++;
            }
            if(j >= t.length()){
                return false;
            }
        }
        return true;
    }
}
