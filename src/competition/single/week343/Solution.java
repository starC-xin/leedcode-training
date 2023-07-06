package competition.single.week343;

import java.util.HashMap;
import java.util.Map;

/**
 * 2023/4/30
 *
 * @author x.z
 */
public class Solution {

    /**
     * 6341. 保龄球游戏的获胜者
     * 给你两个下标从 0 开始的整数数组 player1 和 player2 ，分别表示玩家 1 和玩家 2 击中的瓶数。
     * 保龄球比赛由 n 轮组成，每轮的瓶数恰好为 10 。
     * 假设玩家在第 i 轮中击中 xi 个瓶子。玩家第 i 轮的价值为：
     * 如果玩家在前两轮中击中了 10 个瓶子，则为 2xi 。
     * 否则，为 xi 。
     * 玩家的得分是其 n 轮价值的总和。
     * 返回
     * 如果玩家 1 的得分高于玩家 2 的得分，则为 1 ；
     * 如果玩家 2 的得分高于玩家 1 的得分，则为 2 ；
     * 如果平局，则为 0 。
     */
    public int isWinner(int[] player1, int[] player2) {
        int re1 = calcResult(player1);
        int re2 = calcResult(player2);
        if(re1 > re2){
            return 1;
        }else if(re1 < re2){
            return 2;
        }
        return 0;
    }

    private int calcResult(int[] player) {
        int result = 0;
        Integer pre2;
        Integer pre1;
        pre1 = pre2 = null;
        for (int j : player) {
            if ((pre1 != null && pre1 == 10) || (pre2 != null && pre2 == 10)) {
                result += 2 * j;
            } else {
                result += j;
            }
            if (pre1 != null) {
                pre2 = pre1;
            }
            pre1 = j;
        }
        return result;
    }

    /**
     * 6342. 找出叠涂元素
     * 给你一个下标从 0 开始的整数数组 arr 和一个 m x n 的整数 矩阵 mat 。arr 和 mat 都包含范围 [1，m * n] 内的 所有 整数。
     * 从下标 0 开始遍历 arr 中的每个下标 i ，并将包含整数 arr[i] 的 mat 单元格涂色。
     * 请你找出 arr 中在 mat 的某一行或某一列上都被涂色且下标最小的元素，并返回其下标 i 。
     */
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int rowCount = mat.length;
        int collCount = mat[0].length;
        if(1 == rowCount || 1 == collCount){
            return 0;
        }

        int[] row = new int[rowCount];
        int[] coll = new int[collCount];

//        TODO 因为 arr 绝对无重复，所以可以使用 int 数组的方式来替代 Map
//             速度上还会有提升
        Map<Integer, Integer> rowIndex = new HashMap<>();
        Map<Integer, Integer> collIndex = new HashMap<>();

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < collCount; j++) {
                rowIndex.put(mat[i][j], i);
                collIndex.put(mat[i][j], j);
            }
        }

        int minSearch = Math.min(collCount, rowCount);

        for(int i = 0; i < arr.length; i++){
            int tmpRow = rowIndex.get(arr[i]);
            int tmpColl = collIndex.get(arr[i]);
//            search
            row[tmpRow] += 1;
            coll[tmpColl] += 1;

            if(i < minSearch - 1){
                continue;
            }

            if(row[tmpRow] >= collCount || coll[tmpColl] >= rowCount){
                return i;
            }
        }

        return 0;
    }

    /**
     * 6343. 前往目标的最小代价
     * 给你一个数组 start ，其中 start = [startX, startY] 表示你的初始位置位于二维空间上的 (startX, startY) 。另给你一个数组 target ，其中 target = [targetX, targetY] 表示你的目标位置 (targetX, targetY) 。
     * 从位置 (x1, y1) 到空间中任一其他位置 (x2, y2) 的代价是 |x2 - x1| + |y2 - y1| 。
     * 给你一个二维数组 specialRoads ，表示空间中存在的一些特殊路径。其中 specialRoads[i] = [x1i, y1i, x2i, y2i, costi] 表示第 i 条特殊路径可以从 (x1i, y1i) 到 (x2i, y2i) ，但成本等于 costi 。你可以使用每条特殊路径任意次数。
     * 返回从 (startX, startY) 到 (targetX, targetY) 所需的最小代价。
     * TODO 未解决
     */
    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        return 0;
    }

    /**
     * 6344. 字典序最小的美丽字符串
     * 如果一个字符串满足以下条件，则称其为 美丽字符串 ：
     *
     * 它由英语小写字母表的前 k 个字母组成。
     * 它不包含任何长度为 2 或更长的回文子字符串。
     * 给你一个长度为 n 的美丽字符串 s 和一个正整数 k 。
     *
     * 请你找出并返回一个长度为 n 的美丽字符串，该字符串还满足：在字典序大于 s 的所有美丽字符串中字典序最小。如果不存在这样的字符串，则返回一个空字符串。
     *
     * 对于长度相同的两个字符串 a 和 b ，如果字符串 a 在与字符串 b 不同的第一个位置上的字符字典序更大，则字符串 a 的字典序大于字符串 b 。
     *
     * 例如，"abcd" 的字典序比 "abcc" 更大，因为在不同的第一个位置（第四个字符）上 d 的字典序大于 c 。
     *
     * TODO 未通过
     */
    public String smallestBeautifulString(String s, int k) {
        char maxCh = ((char) ('a' + k - 1));
        final char[] source = s.toCharArray();

        for(int i = source.length - 1; i >= 0; i--){
            if(source[i] < maxCh){
                source[i] = ((char) (source[i] + 1));
                final String tmp = new String(source);
                if(! isRevrse(tmp)){
                    return tmp;
                }
                final String tmpS = subSmall(s, i, i);
                if(! tmpS.equals("")){
                    return tmpS;
                }
            }
            source[i] = 'a';
        }
        return "";
    }

    private String subSmall(String s, int k, int min){
        final char[] source = s.toCharArray();

        for(int i = source.length - 1; i > min; i--){
            if(source[i] - 'a' < k){
                source[i] = ((char) (source[i] + 1));
                final String tmp = new String(source);
                if(! isRevrse(tmp)){
                    return tmp;
                }
            }
        }
        return "";
    }

//    判回文
    private boolean isRevrse(String s){
        final char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length / 2; i++){
            if(! (chars[i] == chars[chars.length - 1 - i])){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        final Solution solution = new Solution();
//        int[] play1 = {9, 7, 10};
//        int[] play2 = {5, 9, 0};
//
//        System.out.println(solution.isWinner(play1, play2));

//        int[] arr = {1,3,4,2,};
//        int[][] mat = {
//                {1,4},
//                {2,3}
//        };
//        System.out.println(solution.firstCompleteIndex(arr, mat));

        String s = "abcz";
        int k = 26;
        System.out.println(solution.smallestBeautifulString(s, k));

//        System.out.println(((char) ('a' + 26)));
    }
}
