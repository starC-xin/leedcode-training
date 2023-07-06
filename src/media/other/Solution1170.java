package media.other;

import utils.Common;

import java.util.Arrays;

/**
 * 2023/6/10
 * 1170. 比较字符串最小字母出现频次
 * 定义一个函数 f(s)，统计 s  中（按字典序比较）最小字母的出现频次 ，其中 s 是一个非空字符串。
 *
 * 例如，若 s = "dcce"，那么 f(s) = 2，因为字典序最小字母是 "c"，它出现了 2 次。
 *
 * 现在，给你两个字符串数组待查表 queries 和词汇表 words 。对于每次查询 queries[i] ，需统计 words 中满足 f(queries[i]) < f(W) 的 词的数目 ，W 表示词汇表 words 中的每个词。
 *
 * 请你返回一个整数数组 answer 作为答案，其中每个 answer[i] 是第 i 次查询的结果。
 *
 * @author x.z
 */
public class Solution1170 {
    /**
     * TODO 直接暴力计算，并且保留计算结果
     *      时间复杂度略高 O(n*m) n为queries长度，m为words长度
     * TODO 可以利用后缀和思路 O(n+m) n、m同上
     */
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] result = new int[queries.length];
        int[] tmpWords = new int[words.length];

        for (int i = 0; i < queries.length; i++) {
            int count = 0;
            final int iCount = calFrequency(queries[i]);
            for (int j = 0; j < words.length; j++) {
                if(tmpWords[j] == 0){
                    tmpWords[j] = calFrequency(words[j]);
                }
                if(iCount < tmpWords[j]){
                    count ++;
                }
            }
            result[i] = count;
        }
        return result;
    }

    private int calFrequency(String str){
        int count = 0;
        Character minC = null;
        for (char c : str.toCharArray()) {
            if(null == minC){
                minC = c;
                count = 1;
                continue;
            }
            if(minC == c){
                count ++;
            }else if (minC - c > 0){
                count = 1;
                minC = c;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        final Solution1170 demo = new Solution1170();

//        ["bba","abaaaaaa","aaaaaa","bbabbabaab","aba","aa","baab","bbbbbb","aab","bbabbaabb"]
//        ["aaabbb","aab","babbab","babbbb","b","bbbbbbbbab","a","bbbbbbbbbb","baaabbaab","aa"]
        String[] queries = Common.string2StringArr("[\"bba\",\"abaaaaaa\",\"aaaaaa\",\"bbabbabaab\",\"aba\",\"aa\",\"baab\",\"bbbbbb\",\"aab\",\"bbabbaabb\"]");
        String[] words = Common.string2StringArr("[\"aaabbb\",\"aab\",\"babbab\",\"babbbb\",\"b\",\"bbbbbbbbab\",\"a\",\"bbbbbbbbbb\",\"baaabbaab\",\"aa\"]");

        System.out.println(Arrays.toString(demo.numSmallerByFrequency(queries, words)));
    }
}
