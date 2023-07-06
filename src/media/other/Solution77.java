package media.other;

import utils.Common;

import java.util.ArrayList;
import java.util.List;

/**
 * 2023/6/20
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 * @author x.z
 */
public class Solution77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int len = 1 << n;
        for (int i = 0; i < len; i++) {
            List<Integer> list = new ArrayList<>();
            int count = 0;
            for (int j = 0; j < n; j++) {
                if((i >> j & 1) == 1){
                    list.add(j + 1);
                    count ++;
                    if(count > k){
                        break;
                    }
                }
            }
            if(count != k){
                continue;
            }
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        final Solution77 demo = new Solution77();

        System.out.println(demo.combine(4, 2));
    }
}
