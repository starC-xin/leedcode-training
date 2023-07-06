package easy;

/**
 * 2023/5/25
 * 2451. 差值数组不同的字符串
 * 给你一个字符串数组 words ，每一个字符串长度都相同，令所有字符串的长度都为 n 。
 *
 * 每个字符串 words[i] 可以被转化为一个长度为 n - 1 的 差值整数数组 difference[i] ，其中对于 0 <= j <= n - 2 有 difference[i][j] = words[i][j+1] - words[i][j] 。注意两个字母的差值定义为它们在字母表中 位置 之差，也就是说 'a' 的位置是 0 ，'b' 的位置是 1 ，'z' 的位置是 25 。
 *
 * 比方说，字符串 "acb" 的差值整数数组是 [2 - 0, 1 - 2] = [2, -1] 。
 * words 中所有字符串 除了一个字符串以外 ，其他字符串的差值整数数组都相同。你需要找到那个不同的字符串。
 *
 * 请你返回 words中 差值整数数组 不同的字符串。
 * @author x.z
 */
public class Solution2451 {
    public String oddString(String[] words) {
        char[][] chss = new char[words.length][];
        for (int i = 0; i < words.length; i++) {
            chss[i] = words[i].toCharArray();
        }

        boolean lastOnce = false;
        for (int i = 0; i < chss[0].length - 1; i++) {
            Integer pre = null;
            for (int j = 0; j < chss.length; j++) {
                final int tmp = chss[j][i + 1] - chss[j][i];
                if(null == pre){
                    pre = tmp;
                    continue;
                }
                if(lastOnce){
                    if(tmp != pre){
                        return words[1];
                    }else{
                        return words[0];
                    }
                }
                if(tmp != pre){
                    if(j < 2){
                        lastOnce = true;
                        pre = tmp;
                    }else{
                        return words[j];
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        final Solution2451 demo = new Solution2451();

//        System.out.println(demo.oddString(new String[]{"aba", "bab", "bab"}));
//        System.out.println(demo.oddString(new String[]{"adc","wzy","abc"}));
//        System.out.println(demo.oddString(new String[]{"aaaba","sssts","vvvwv","sssts","ooopo","rrrsr","iiiji","pppqp","aabbb","xxxyx","nnnon","bbbcb","hhhih","jjjkj","hhhih","kkklk","yyyzy","jjjkj","nnnon","eeefe","eeefe","ggghg","sssts","cccdc","rrrsr"}));
        System.out.println(demo.oddString(new String[]{"klklklkklk","opopopoopo","ijijijiiji","vwvwvwvvwv","vwvwvwvvwv","hihihihhih","opopopoopo","uvuvuvuuvu","ghghghgghg","fgfgfgffgf","tutututtut","cdcdcdccdc","rsrsrsrrsr","qrqrqrqqrq","xyxyxyxxyx","ijijijiiji","mnmnmnmmnm","yzyzyzyyzy","klklklkklk","bcbcbcbbcb","rsrsrsrrsr","abababaaba","wxwxwxwwxw","vwvwvwvvwv","hihihihhih","stststssts","ijijijiiji","uvuvuvuuvu","ijijijiiji","dedededded","pqpqpqppqp","wxwxwxwwxw","hihihihhih","efefefeefe","lmlmlmllml","abababaaba","pqpqpqppqp","xyxyxyxxyx","cdcdcdccdc","qrqrqrqqrq","abbbabbbba","stststssts","fgfgfgffgf","tutututtut","wxwxwxwwxw","qrqrqrqqrq","fgfgfgffgf","cdcdcdccdc","nonononnon","vwvwvwvvwv"}));
    }
}
