package day;

import java.util.*;

/**
 * 2022/2/10
 *
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：["1/2"]
 * 解释："1/2" 是唯一一个分母小于等于 2 的最简分数。
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：["1/2","1/3","2/3"]
 *
 * 示例 3：
 * 输入：n = 4
 * 输出：["1/2","1/3","1/4","2/3","3/4"]
 * 解释："2/4" 不是最简分数，因为它可以化简为 "1/2" 。
 *
 * 示例 4：
 * 输入：n = 1
 * 输出：[]
 *  
 * 提示：
 * 1 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/simplified-fractions
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author x.c
 */
public class Solution1447 {
    /**
     * TODO 初步尝试暴力破解
     *      任意顺序返回，意味着无须在意计算顺序
     *      需要找到一个快速判断分数是否为最简分数的算法
     */
    public List<String> simplifiedFractions(int n) {
//        建立 set 集合存储计算得到的分数
//        Set<String> set = new HashSet<>();
//        int i = n;


//        return new LinkedList<>(set);
        return null;
    }
}
