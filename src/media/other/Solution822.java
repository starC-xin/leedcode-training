package media.other;

import utils.Common;

import java.util.*;

/**
 * 2023/8/2
 *
 * @author x.z
 */
public class Solution822 {

    /**
     * 首先，正面背面一致的卡片没有翻转的价值
     * 在有翻转价值的卡片中选择较小的数值作为备选项
     * 在前一步的结果中找到想要的数值
     * 在前一步的结果中找到最小的数值
     * TODO 实际操作中呢，在上述的思路中有所调整
     *      要分为翻转卡片与不翻转卡片两种情况
     *      不翻转卡片则是直接判断背面是否存在一张正面没有的卡片，最小值
     *      翻转卡片后也尽量得到一张正面没有的卡片，也是最小值
     *      两个值取其中最小值，然后返回
     */
    public int flipgame(int[] fronts, int[] backs) {
        Map<Integer, List<Integer>> mapF = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.naturalOrder());
        for (int i = 0; i < fronts.length; i++) {
            mapF.computeIfAbsent(fronts[i], item -> new ArrayList<>()).add(i);

            if (!pq.contains(fronts[i])) {
                pq.offer(fronts[i]);
            }
        }
        int res = Integer.MAX_VALUE;
        while(!pq.isEmpty()){
            final Integer poll = pq.poll();
            final List<Integer> items = mapF.get(poll);
            boolean wanna = false;
            List<Integer> list = new ArrayList<>();
            for (Integer item : items) {
                if(fronts[item] == backs[item]){
                    wanna = true;
                    continue;
                }
                list.add(item);
            }
            if(list.isEmpty()){
                continue;
            }
            for (Integer index : list) {
                if(mapF.containsKey(backs[index])){
                    continue;
                }
                res = Math.min(res, backs[index]);
            }
            if(wanna){
                continue;
            }
            res = Math.min(res, poll);
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }

    public static void main(String[] args) {
        final Solution822 demo = new Solution822();

//        示例 case
        System.out.println(demo.flipgame(Common.string2IntArr("1"), Common.string2IntArr("1")));

//        case 134
//        1,1  1,2
        System.out.println(demo.flipgame(Common.string2IntArr("1,1"), Common.string2IntArr("1,2")));
    }
}
