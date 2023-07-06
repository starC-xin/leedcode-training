package easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 2023/6/23
 * 给定一种规律 pattern 和一个字符串 s ，判断 s 是否遵循相同的规律。
 *
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 s 中的每个非空单词之间存在着双向连接的对应规律。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/word-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author x.z
 */
public class Solution290 {
    public boolean wordPattern(String pattern, String s) {
        final String[] split = s.split(" ");
        if(split.length != pattern.length()){
            return false;
        }
        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < split.length; i++) {
            final char key = pattern.charAt(i);
            if (!map.containsKey(key)) {
                map.put(key, split[i]);
            }else{
                if (!map.get(key).equals(split[i])) {
                    return false;
                }
                for (Character character : map.keySet()) {
                    if(character.equals(key)){
                        continue;
                    }
                    if(map.get(character).equals(split[i])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 官方的很有意思的一个解法
     */
    static class Solution {
        public boolean wordPattern(String pattern, String s) {
            String[] words = s.split(" ");
            //数量必须相等
            if(words.length != pattern.length()) {
                return false;
            }
            Map<Object , Integer> map = new HashMap<>();
            for(Integer i = 0; i < words.length; i++) {
                if(map.put(pattern.charAt(i),i) != map.put(words[i], i)) {
                    return false;
                }
            }
            return true;

        }
    }
}
