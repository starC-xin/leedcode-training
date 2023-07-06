package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 2023/6/10
 * 205. 同构字符串
 * 给定两个字符串 s 和 t ，判断它们是否是同构的。
 *
 * 如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。
 *
 * 每个出现的字符都应当映射到另一个字符，同时不改变字符的顺序。不同字符不能映射到同一个字符上，相同字符只能映射到同一个字符上，字符可以映射到自己本身。
 * @author x.z
 */
public class Solution205 {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Integer> mapS = new HashMap<>();
        Map<Character, Integer> mapT = new HashMap<>();
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            boolean flag = false;
            final char cS = s.charAt(i);
            final char cT = t.charAt(i);
            Integer numS = mapS.get(cS);
            if(null == numS){
                numS = num;
                mapS.put(cS, numS);
                flag = true;
            }
            Integer numT = mapT.get(cT);
            if(null == numT){
                numT = num;
                mapT.put(cT, numT);
                flag = true;
            }
            if(!numS.equals(numT)){
                return false;
            }
            if(flag){
                num ++;
            }
        }
        return true;
    }

    /**
     * 虽然我已经秒杀了，但官方题解也是另一种思路
     */
    public boolean isIsomorphicAnswer(String s, String t) {
        Map<Character, Character> s2t = new HashMap<>();
        Map<Character, Character> t2s = new HashMap<>();
        int len = s.length();
        for (int i = 0; i < len; ++i) {
            char x = s.charAt(i), y = t.charAt(i);
            if ((s2t.containsKey(x) && s2t.get(x) != y) || (t2s.containsKey(y) && t2s.get(y) != x)) {
                return false;
            }
            s2t.put(x, y);
            t2s.put(y, x);
        }
        return true;
    }
}
