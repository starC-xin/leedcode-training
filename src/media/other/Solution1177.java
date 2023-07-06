package media.other;

import utils.Common;

import java.util.*;

/**
 * 2023/6/15
 * 1177. 构建回文串检测
 * 给你一个字符串 s，请你对 s 的子串进行检测。
 *
 * 每次检测，待检子串都可以表示为 queries[i] = [left, right, k]。我们可以 重新排列 子串 s[left], ..., s[right]，并从中选择 最多 k 项替换成任何小写英文字母。
 *
 * 如果在上述检测过程中，子串可以变成回文形式的字符串，那么检测结果为 true，否则结果为 false。
 *
 * 返回答案数组 answer[]，其中 answer[i] 是第 i 个待检子串 queries[i] 的检测结果。
 *
 * 注意：在替换时，子串中的每个字母都必须作为 独立的 项进行计数，也就是说，如果 s[left..right] = "aaa" 且 k = 2，我们只能替换其中的两个字母。（另外，任何检测都不会修改原始字符串 s，可以认为每次检测都是独立的）
 * @author x.z
 */
public class Solution1177 {
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> result = new LinkedList<>();
        Map<String, Integer> cache = new HashMap<>();
        for (int[] query : queries) {
            result.add(checkS(s.substring(query[0], query[1] + 1).intern(), query[2], cache));
        }
        return result;
    }

    /**
     * 要求为可以重新排列，所以此处算法应该改变一下
     */
    private Boolean checkS(String substring, int k, Map<String, Integer> cache) {
        if(substring.length() == 1){
            return true;
        }
        if(cache.containsKey(substring)){
            return cache.get(substring) <= k;
        }
        final char[] chs = substring.toCharArray();
        int[] chCount = new int[26];
        int tmpK = 0;
        for (char ch : chs) {
            final int div = ch - 'a';
            chCount[div] ++;
            if(chCount[div] % 2 == 0){
                tmpK --;
            }else{
                tmpK ++;
            }
        }
        tmpK /= 2;
        cache.put(substring, tmpK);
        return tmpK <= k;
    }

    /**
     * TODO 官方题解
     *      利用了前缀和和异或的原理加速运算
     * @param s
     * @param queries
     * @return
     */
    public List<Boolean> canMakePaliQueriesAnswer(String s, int[][] queries) {
        int n = s.length();
        int[] count = new int[n + 1];
        for (int i = 0; i < n; i++) {
            count[i + 1] = count[i] ^ (1 << (s.charAt(i) - 'a'));
        }
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1], k = queries[i][2];
            int bits = 0, x = count[r + 1] ^ count[l];
            while (x > 0) {
                x &= x - 1;
                bits++;
            }
            res.add(bits <= k * 2 + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        final Solution1177 demo = new Solution1177();

//        "hunu"
//[[1,1,1],[2,3,0],[3,3,1],[0,3,2],[1,3,3],[2,3,1],[3,3,1],[0,3,0],[1,1,1],[2,3,0],[3,3,1],[0,3,1],[1,1,1]]
//        [true, false, true, true, true, true, true, false, true, false, true, true, true]
//        [true, false, true, true, true, true, true, false, true, false, true, true, true]
        System.out.println(demo.canMakePaliQueries("hunu", Common.string2IntIntArr("[[1,1,1],[2,3,0],[3,3,1],[0,3,2],[1,3,3],[2,3,1],[3,3,1],[0,3,0],[1,1,1],[2,3,0],[3,3,1],[0,3,1],[1,1,1]]")));
    }
}
