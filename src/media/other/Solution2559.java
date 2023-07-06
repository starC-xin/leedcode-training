package media.other;

import utils.Common;

import java.util.Arrays;

/**
 * 2023/6/2
 * 2559. 统计范围内的元音字符串数
 * 给你一个下标从 0 开始的字符串数组 words 以及一个二维整数数组 queries 。
 *
 * 每个查询 queries[i] = [li, ri] 会要求我们统计在 words 中下标在 li 到 ri 范围内（包含 这两个值）并且以元音开头和结尾的字符串的数目。
 *
 * 返回一个整数数组，其中数组的第 i 个元素对应第 i 个查询的答案。
 *
 * 注意：元音字母是 'a'、'e'、'i'、'o' 和 'u' 。
 *
 * @author x.z
 */
public class Solution2559 {

    /**
     * TODO 首先，思路上没有任何问题
     *      那么超时因素只能是压力case的数据量太大
     *      如果降低时间复杂度，是一个问题
     *      目前的时间复杂度为 O(n * m) n为区间个数，m为每一个区间包含的数量
     */
    public int[] vowelStrings(String[] words, int[][] queries) {
//        TODO 思路一，抵不过最后的压力case
//        Boolean[] visited = new Boolean[words.length];
//        int[] counts = new int[queries.length];
//        for (int i = 0; i < queries.length; i++) {
//            final int[] dul = queries[i];
//            for (int j = dul[0]; j <= dul[1]; j++) {
//                if(visited[j] == null){
//                    visited[j] = check(words[j]);
//                }
//                if(visited[j]){
//                    counts[i] ++;
//                }
//            }
//        }
//        return counts;

//        TODO 思路二，尝试解决超时问题
        int[] counts = new int[queries.length];
        int[] dp = new int[words.length];
        boolean[] checks = new boolean[words.length];
        if(check(words[0])){
            dp[0] = 1;
            checks[0] = true;
        }
        for (int i = 1; i < words.length; i++) {
            dp[i] = dp[i - 1];
            if(check(words[i])){
                dp[i] += 1;
                checks[i] = true;
            }
        }
        for (int i = 0; i < queries.length; i++) {
            counts[i] = dp[queries[i][1]] - dp[queries[i][0]];
            if(checks[queries[i][0]]){
                counts[i] ++;
            }
        }

        return counts;
    }

    /**
     * 首先，这里判定没有任何问题
     */
    private boolean check(String word){
        final char pre = word.charAt(0);
        final char suf = word.charAt(word.length() - 1);
        return ('a' == pre || 'e' == pre || 'i' == pre || 'o' == pre || 'u' == pre) &&
                ('a' == suf || 'e' == suf || 'i' == suf || 'o' == suf || 'u' == suf);
    }

    public static void main(String[] args) {
        final Solution2559 demo = new Solution2559();

//        一个暴力case 超时，这里放不下了
//        示例 case
//        ["aba","bcb","ece","aa","e"]
//        [[0,2],[1,4],[1,1]]
        System.out.println(Arrays.toString(demo.vowelStrings(
                Common.string2StringArr("[\"aba\",\"bcb\",\"ece\",\"aa\",\"e\"]"),
                Common.string2IntIntArr("[[0,2],[1,4],[1,1]]")
        )));
    }
}
