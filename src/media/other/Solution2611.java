package media.other;

import utils.Common;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2023/6/7
 * 2611. 老鼠和奶酪
 * 有两只老鼠和 n 块不同类型的奶酪，每块奶酪都只能被其中一只老鼠吃掉。
 *
 * 下标为 i 处的奶酪被吃掉的得分为：
 *
 * 如果第一只老鼠吃掉，则得分为 reward1[i] 。
 * 如果第二只老鼠吃掉，则得分为 reward2[i] 。
 * 给你一个正整数数组 reward1 ，一个正整数数组 reward2 ，和一个非负整数 k 。
 *
 * 请你返回第一只老鼠恰好吃掉 k 块奶酪的情况下，最大 得分为多少。
 *
 * @author x.z
 */
public class Solution2611 {
    /**
     * TODO 同一块奶酪，谁吃掉后得分高就谁吃，相同得分总是第一只老鼠吃，第二只老鼠每次吃的都压入优先队列
     *      如果所有奶酪吃完，第一只吃的少于k块，则回退第二只老鼠吃的，把第二只老鼠吃的最小的切换给第一只老鼠吃（如果第一只老鼠得分更高的话
     * TODO 直接将第一只老鼠的得分数组入优先队列，按照倒序递推，流程按照前一个todo的流程
     *      隐含条件，所有奶酪必须被吃完
     */
    public int miceAndCheese(int[] reward1, int[] reward2, int k) {
        final PriorityQueue<int[]> one = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < reward1.length; i++) {
            one.offer(new int[]{i, reward1[i] - reward2[i]});
        }

        boolean[] eat1 = new boolean[reward1.length];
        final PriorityQueue<int[]> two = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int sum = 0;
        while(!one.isEmpty() && k > 0){
            final int[] poll = one.poll();
            if(poll[1] >= 0){
                sum += reward1[poll[0]];
                k --;
            }else{
                sum += reward2[poll[0]];
                two.offer(new int[]{poll[0], -1 * poll[1]});
            }
            eat1[poll[0]] = true;
        }

        while(k-- > 0){
            final int[] poll = two.poll();
            sum -= poll[1];
        }

        for (int i = 0; i < eat1.length; i++) {
            if(!eat1[i]){
                sum += reward2[i];
            }
        }

        return sum;
    }

    /**
     * 示例代码
     * 因为存在隐含条件，剩余的奶酪全由第二号老鼠吃光
     * 所以答案就是在二号得分总分的基础上，替换k个比二号大的得分，使得总分最高
     * 即总是在 reward2 总和的基础上，找寻 k 个从 reward1 来的替换值，使得最终得分最高
     */
    public int miceAndCheeseAnswer(int[] reward1, int[] reward2, int k) {
        int res = 0;
        for(int r : reward2){
            res += r;
        }
        int[]num = new int[reward1.length];
        for(int i = 0;i < reward1.length;i++){
            num[i] = -reward2[i] + reward1[i];
        }
        Arrays.sort(num);
        int index = num.length - 1;
        while(k > 0){
            k--;
            res += num[index--];
        }
        return res;
    }

    public static void main(String[] args) {
        final Solution2611 demo = new Solution2611();

//        answer 15
        int[] r1 = Common.string2IntArr("[1,1,3,4]");
        int[] r2 = Common.string2IntArr("[4,4,1,1]");
        int k = 2;

//        [4,1,5,3,3]
//        [3,4,4,5,2]
//        3
//        answer 21
//        int[] r1 = Common.string2IntArr("[4,1,5,3,3]");
//        int[] r2 = Common.string2IntArr("[3,4,4,5,2]");
//        int k = 3;

//        [1,4,4,6,4]
//        [6,5,3,6,1]
//        1
//        answer 24
//        int[] r1 = Common.string2IntArr("[1,4,4,6,4]");
//        int[] r2 = Common.string2IntArr("[6,5,3,6,1]");
//        int k = 1;

        System.out.println(demo.miceAndCheese(r1, r2, k));
    }
}
