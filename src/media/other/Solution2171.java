package media.other;

import java.util.Arrays;

/**
 * 2024/01/18
 * 2171. 拿出最少数目的魔法豆
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给定一个 正整数 数组 beans ，其中每个整数表示一个袋子里装的魔法豆的数目。
 *
 * 请你从每个袋子中 拿出 一些豆子（也可以 不拿出），使得剩下的 非空 袋子中（即 至少还有一颗 魔法豆的袋子）魔法豆的数目 相等。一旦把魔法豆从袋子中取出，你不能再将它放到任何袋子中。
 *
 * 请返回你需要拿出魔法豆的 最少数目。
 * @author x.z
 */
public class Solution2171 {

    /**
     * 不算难，主要是需要理解到，剩余非空袋子中的豆子数量相等
     * 那么就可以认为，这一批袋子中一定有一袋豆子内部的豆子数是完全不会动的，我们将其认为是 X
     * 比 x 少的袋子全部豆子都拿出来，比 x 袋子多的，拿出 原豆子数量-x 的豆子数量即可
     * 那么只需要找到这代豆子数量为 x 的袋子，即可得到最少拿出的豆子数量
     * 直接寻找显然会超时，所以先执行排序，从任意方向遍历一次即可得到最终结果
     *
     */
    public long minimumRemoval(int[] beans) {
        Arrays.sort(beans);
        long total = 0;
        for (int bean : beans) {
            total += bean;
        }

        long res = total;
        for (int i = 0; i < beans.length; i++) {
            res = Math.min(res, total - (long) beans[i] * (beans.length - i));
        }
        return res;
    }
}
