package competition.doubl.week108;

import utils.Common;

import java.util.*;

/**
 * 2023/7/8
 * 排名 1280
 * @author x.z
 */
public class Solution {
    public int alternatingSubarray(int[] nums) {
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;
            int tmpMax = 0;
            for (int j = i; j < nums.length - 1; j++) {
                final int tmp = (nums[j + 1] - nums[j]) * (flag ? 1 : -1);
                flag = !flag;
                if(1 == tmp){
                    tmpMax ++;
                }else{
                    break;
                }
            }
            if(0 != tmpMax){
                res = Math.max(res, tmpMax + 1);
            }
            if(nums.length - i - 1 <= res){
                break;
            }
        }
        return res;
    }

    public List<Integer> relocateMarbles(int[] nums, int[] moveFrom, int[] moveTo) {
        Set<Integer> map = new HashSet<>();
        for (int num : nums) {
            map.add(num);
        }

        boolean[] vis = new boolean[moveFrom.length];
        for (int i = 0; i < moveFrom.length; i++) {
            map.remove(moveFrom[i]);
            map.add(moveTo[i]);
        }
        List<Integer> res = new ArrayList<>(map);
        res.sort(Comparator.naturalOrder());
        return res;
    }

    /**
     * TODO 这道题估计应该是和数学有关
     *      暂时没想懂 5 的幂有什么特别的规律
     *      硬算绝对超时，不靠谱的
     *
     */
    public int minimumBeautifulSubstrings(String s) {
        return 0;
    }

    /**
     * 是我最喜欢的 a神 的提交
     * 留作第三题的参考
     */
    static class Solution3 {

        public int minimumBeautifulSubstrings(String s) {
            return minimumBeautifulSubstrings(0, s);
        }

        private int minimumBeautifulSubstrings(int index, String s) {
            if (index == s.length()) {
                return 0;
            }
            int min = Integer.MAX_VALUE;
            for (int i = index + 1, j, k; i <= s.length(); i++) {
                if (s.charAt(index) == '1' && (j = Integer.parseInt(s.substring(index, i), 2)) > 0 && 15625 % j == 0
                        && (k = minimumBeautifulSubstrings(i, s)) >= 0) {
                    min = Math.min(min, 1 + k);
                }
            }
            return min == Integer.MAX_VALUE ? -1 : min;
        }
    }

    /**
     * TODO 可惜了，最后这题很简单
     *      但是没有考虑到内存优化，导致内存超出限制
     *      改写为稀疏图后，没能在结束前提交
     *      结束后十来分钟提交，一次通过
     *
     */
    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
        long[] res = new long[5];
        Map<Integer, Map<Integer, Boolean>> coor = new HashMap<>();
        Map<Integer, Map<Integer, Boolean>> vis = new HashMap<>();
        for (int[] coordinate : coordinates) {
            coor.computeIfAbsent(coordinate[0], var -> new HashMap<>()).put(coordinate[1], true);
        }
        for (final int[] coordinate : coordinates) {
            process(coordinate, coor, m, n, res, vis);
        }
        res[0] = (long) (m - 1) * (n - 1);
        for (int i = 1; i < res.length; i++) {
            res[0] -= res[i];
        }
        return res;
    }

    int[][] sw = {
            {0, 0}, {-1, -1}, {-1, 0}, {0, -1}
    };
    private void process(int[] coordinate, Map<Integer, Map<Integer, Boolean>> coor, int m, int n, long[] res, Map<Integer, Map<Integer, Boolean>> vis){
        for (int[] ints : sw) {
            final int count = count(coordinate[0] + ints[0], coordinate[1] + ints[1], m, n, coor, vis);
            if(count == -1){
                continue;
            }
            res[count]++;
        }
    }

    int[][] dir = {
            {0, 0}, {1, 0}, {0, 1}, {1, 1}
    };
    private int count(int x, int y, int m, int n, Map<Integer, Map<Integer, Boolean>> coor, Map<Integer, Map<Integer, Boolean>> vis){
        if(x < 0 || x >= m - 1 || y < 0 || y >= n - 1){
            return -1;
        }
        if(vis.get(x) != null){
            final Boolean tmp = vis.get(x).get(y);
            if(tmp != null && tmp){
                return -1;
            }
        }
        vis.computeIfAbsent(x, var -> new HashMap<>()).put(y, true);
        int count = 0;
        for (int[] ints : dir) {
            final int tmpX = x + ints[0];
            final int tmpY = y + ints[1];
            final Map<Integer, Boolean> row = coor.get(tmpX);
            if(row != null){
                final Boolean box = row.get(tmpY);
                if(box != null && box){
                    count ++;
                }
            }
        }
        return count;
    }

    /**
     * 第四题参考
     */
    static class Solution4 {

        public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
            HashMap<List<Integer>, Integer> map = new HashMap<>();
            long[] result = { (m - 1L) * (n - 1), 0, 0, 0, 0 };
            for (int[] c : coordinates) {
                for (int[] i : new int[][] { { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } }) {
                    if (c[0] + i[0] >= 0 && c[0] + i[0] < m && c[1] + i[1] >= 0 && c[1] + i[1] < n) {
                        int count = map.getOrDefault(List.of(c[0], c[1] + i[1]), 0)
                                + map.getOrDefault(List.of(c[0] + i[0], c[1]), 0)
                                + map.getOrDefault(List.of(c[0] + i[0], c[1] + i[1]), 0);
                        result[count]--;
                        result[count + 1]++;
                    }
                }
                map.put(List.of(c[0], c[1]), 1);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        final Solution demo = new Solution();

//        System.out.println(demo.alternatingSubarray(Common.string2IntArr("[2,3,4,3,4]")));

//        System.out.println(demo.relocateMarbles(
//                Common.string2IntArr("[1,1,3,3]"),
//                Common.string2IntArr("[1,3]"),
//                Common.string2IntArr("[2,2]")
//        ));

        System.out.println(Arrays.toString(demo.countBlackBlocks(3, 3, Common.string2IntIntArr("[[0,0],[1,1],[0,2]]"))));
    }
}
