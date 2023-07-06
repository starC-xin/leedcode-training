package easy;

/**
 * 2023/6/26
 *
 * @author x.z
 */
public class Solution383 {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] chs = new int[26];
        for (char c : magazine.toCharArray()) {
            chs[c - 'a'] ++;
        }
        for (char c : ransomNote.toCharArray()) {
            chs[c - 'a'] --;
            if(chs[c - 'a'] < 0){
                return false;
            }
        }
        return true;
    }
}
