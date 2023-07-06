package media.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 2023/7/6
 *
 * @author x.z
 */
public class Solution2178 {
    /**
     * TODO 因为是正偶数之和，所以奇数直接排除
     *      要使正偶数最多，则必定包含若干较小的正偶数
     *      则思路上则是从 2 开始尝试探索
     *      又因所有加数不可相同，所以可直接在原数上除以 2 ，即可得到原数是有 x 个 2 相加得到
     *      按照 1,2,3...n 个 2 ，来选取最多的加数组成结果数组，且满足 sum(1+2+3+..+n) = x
     *      若最终无法被逐个减去，则将剩余的数值直接加在最后一个数上，保证所有数除以 2 的商的和为 x
     *      此思路上贪心应该是走不通的，选择动规可能比较好，不过可以先使用贪心探索一下力扣的case
     * TODO 贪心可以直接过
     *      这里的数据结构可以直接使用 链表 而非 数组（或者使用数组时提前初始化大小防止频繁扩容导致的耗时
     *
     */
    public List<Long> maximumEvenSplit(long finalSum) {
        List<Long> res = new ArrayList<>();
        if(finalSum % 2 != 0){
            return res;
        }
        long n = 2;
        while(finalSum > 0){
            if(finalSum >= n){
                finalSum -= n;
                res.add(n);
            }else{
//                res 必不为空
                res.add(res.remove(res.size() - 1) + finalSum);
                break;
            }
            n += 2;
        }
        return res;
    }

    public static void main(String[] args) {
        final Solution2178 demo = new Solution2178();

//        test
        System.out.println(demo.maximumEvenSplit(28));
    }
}
