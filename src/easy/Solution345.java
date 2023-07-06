package easy;

/**
 * 2023/6/24
 *
 * @author x.z
 */
public class Solution345 {
    public String reverseVowels(String s) {
        final char[] chs = s.toCharArray();
        int left = 0;
        int right = chs.length - 1;
        while(left < right){
            while(left < right && !isVow(chs[left])){
                left ++;
            }
            while(right > left && !isVow(chs[right])){
                right --;
            }
            if(left < right){
                final char ch = chs[left];
                chs[left] = chs[right];
                chs[right] = ch;
                left ++;
                right --;
            }
        }
        return new String(chs);
    }

    char[] vows = {
            'a','e','i','o','u',
            'A','E','I','O','U',
    };
    private boolean isVow(char ch){
        for (char vow : vows) {
            if(ch == vow){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final Solution345 demo = new Solution345();

        System.out.println(demo.reverseVowels("hllle"));
    }
}
