package media.other;

import java.util.*;

/**
 * 2023/4/28
 *你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 *
 * 示例 1:
 * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * 输出：6
 * 解释：
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 *
 * 示例 2:
 * 输入: deadends = ["8888"], target = "0009"
 * 输出：1
 * 解释：把最后一位反向旋转一次即可 "0000" -> "0009"。
 *
 * 示例 3:
 * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * 输出：-1
 * 解释：无法旋转到目标数字且不被锁定。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/open-the-lock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author x.z
 */
public class Solution752 {
    /**
     * 思路一：广度优先搜索，图遍历
     *       0000 作为初始态，每次变化能有周围八个节点，逐次遍历这八个节点
     *       最差循环次数也是一个定数
     * 基于以上思路，第一次提交，确认错误点：若死锁中存在 0000 ，则无论如何都不可解开
     *            第二次提交，确认错误点，若目标就是 0000， 则直接返回
     */
    public int openLock(String[] deadends, String target) {
        String begin = "0000";
        if(isDead(begin, deadends)){
            return -1;
        }
        if(begin.equals(target)){
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, Integer> ord = new HashMap<>();
        queue.offer(begin);
        visited.add(begin);
        ord.put(begin, 0);

        List<String> available = new ArrayList<>();
        while(! queue.isEmpty()){
            final String poll = queue.poll();
            final Integer curOrd = ord.get(poll);
            genAroundList(poll, available);
            for (String s : available) {
                if(! visited.contains(s)){
                    visited.add(s);
                    if(isDead(s, deadends)){
                        continue;
                    }
                    if(s.equals(target)){
                        return curOrd + 1;
                    }
                    ord.put(s, curOrd + 1);
                    queue.offer(s);
                }
            }
        }
        return -1;
    }

    private void genAroundList(String source, List<String> list){
        list.clear();
        final char[] chars = source.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int old = chars[i] - '0';

            chars[i] = (char)((old + 9) % 10 + '0');
            list.add(new String(chars));

            chars[i] = (char)((old + 1) % 10 + '0');
            list.add(new String(chars));

            chars[i] = (char)(old + '0');
        }
    }

    private boolean isDead(String source, String[] check){
        for (String s : check) {
            if(source.equals(s)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String  target = "8888";
        String[] check = {"0000"};

        final Solution752 test = new Solution752();
        System.out.println(test.openLock(check, target));
    }
}
