package high;

import java.util.*;

/**
 * 2023/4/28
 *在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。一次 移动 定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sliding-puzzle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author x.z
 */
public class Solution773 {
    /**
     * 思路一：广度优先搜索
     */
    public int slidingPuzzle(int[][] board) {
        String result = "123450";
        final String s = arrToStr(board);
        if (s.equals(result)) {
            return 0;
        }

        Set<String> visited = new HashSet<>();
        Map<String, Integer> ord = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(s);
        ord.put(s, 0);
        queue.offer(s);

        List<String> around = new LinkedList<>();
        while(! queue.isEmpty()){
            final String poll = queue.poll();
            final Integer curOrd = ord.get(poll);
            genAround(poll, around);
            for (String s1 : around) {
                if(visited.contains(s1)){
                    continue;
                }
                if(s1.equals(result)){
                    return curOrd + 1;
                }
                queue.offer(s1);
                visited.add(s1);
                ord.put(s1, curOrd + 1);
            }
        }

        return -1;
    }

    private void genAround(String s, List<String> list){
        list.clear();
        final int i = s.indexOf('0');
        final char[] chars = s.toCharArray();
//        左右移动
        if(i % 3 > 0){
            chars[i] = chars[i - 1];
            chars[i - 1] = '0';
            list.add(new String(chars));
            chars[i - 1] = chars[i];
            chars[i] = '0';
        }
        if(i % 3 < 2){
            chars[i] = chars[i + 1];
            chars[i + 1] = '0';
            list.add(new String(chars));
            chars[i + 1] = chars[i];
            chars[i] = '0';
        }
//        上下移动
        if(i <= 2){
            chars[i] = chars[i + 3];
            chars[i + 3] = '0';
            list.add(new String(chars));
        }else{
            chars[i] = chars[i - 3];
            chars[i - 3] = '0';
            list.add(new String(chars));
        }
    }

    private String arrToStr(int[][] arr){
        StringBuilder sb = new StringBuilder();
        for (int[] ints : arr) {
            for (int anInt : ints) {
                sb.append(anInt);
            }
        }
        return sb.toString();
    }
}
