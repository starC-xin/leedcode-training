package competition.doubl.week107;

import java.util.*;

/**
 * 2023/6/24
 *
 * @author x.z
 */
public class Solution {
    public int maximumNumberOfStringPairs(String[] words) {
        Set<String> cache = new HashSet<>();
        int count = 0;
        for (String word : words) {
            if(cache.isEmpty()){
                cache.add(word);
                continue;
            }
            StringBuilder sb = new StringBuilder();
            final char[] chs = word.toCharArray();
            for (int i = chs.length - 1; i >= 0; i--) {
                sb.append(chs[i]);
            }
            if(cache.contains(sb.toString())){
                cache.remove(sb.toString());
                count ++;
                continue;
            }
            cache.add(word);
        }
        return count;
    }


    public int longestString(int x, int y, int z) {
        int len = 0;
        while(x > 0 && y > 0 && z > 0){
            len += 6;
            x--;
            y--;
            z--;
        }
        if(z == 0){
            while(x > 0 && y > 0){
                len += 4;
                x--;
                y--;
            }
            if(x > 0 || y > 0){
                len += 2;
            }
        }else{
            if(x > 0 || y > 0){
                len += 4;
                z--;
            }
            if(z > 0){
                len += z * 2;
            }
        }
        return len;
    }

    /**
     * 感觉应该是需要DP来枚举所有状态，选出最小的长度字符串
     */
    public int minimizeConcatenatedLength(String[] words) {
        int len = 0;
        char pre = words[0].charAt(0);
        char suf = words[0].charAt(words[0].length() - 1);
        for (int i = 1; i < words.length; i++) {
            char tmpPre = words[i].charAt(0);
            char tmpSuf = words[i].charAt(words[i].length() - 1);
        }
        return len;
    }

    /**
     * 还是那个男人的答案，琢磨下
     * 说实话，大概能明白是在递归枚举所有链接的状态，然后得出最小长度
     * 但是，实在是读不懂递归流程
     */
    public int minimizeConcatenatedLengthAnswer(String[] words) {
        return words[0].length() + minimizeConcatenatedLength(1, words[0].charAt(0) - 'a',
                words[0].charAt(words[0].length() - 1) - 'a', words, new int[words.length][26][26]);
    }

    private int minimizeConcatenatedLength(int index, int first, int last, String[] words, int[][][] dp) {
        if (index == words.length) {
            return 0;
        } else if (dp[index][first][last] == 0) {
            dp[index][first][last] = words[index].length() + Math.min(
                    minimizeConcatenatedLength(
                            index + 1, first, words[index].charAt(words[index].length() - 1) - 'a', words, dp
                    ) - (words[index].charAt(0) - 'a' == last ? 1 : 0),
                    minimizeConcatenatedLength(
                            index + 1, words[index].charAt(0) - 'a', last, words, dp
                    ) - (first == words[index].charAt(words[index].length() - 1) - 'a' ? 1 : 0)
            );
        }
        return dp[index][first][last];
    }

    /**
     * 暴力
     */
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        List<Integer>[] servers = new List[n + 1];
        int nothingC = n;
        for (int[] log : logs) {
            if (servers[log[0]] == null) {
                servers[log[0]] = new ArrayList<>();
                nothingC --;
            }
            servers[log[0]].add(log[1]);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = nothingC;
            int left = queries[i] - x;
            int right = queries[i];
            for (List<Integer> server : servers) {
                if (null == server) {
                    continue;
                }
                boolean flag = true;
                for (Integer time : server) {
                    if (time >= left && time <= right) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    res[i]++;
                }
            }
        }
        return res;
    }

    /**
     * 二分查找也超时的话，我还真没啥办法了
     */
    public int[] countServers0(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, Comparator.comparing(item -> item[1]));
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            res[i] = n;
            Set<Integer> cache = new HashSet<>();
            int right = queries[i];
            int left = right - x;
            int leftI = 0;
            int rightI = logs.length - 1;
            while(leftI < rightI){
                int mid = (leftI + rightI + 1) / 2;
                if(logs[mid][1] > left){
                    rightI = mid - 1;
                }else{
                    leftI = mid;
                }
            }
            while(leftI > 0 && logs[leftI][1] == left){
                leftI --;
            }
            for (int j = leftI; j < logs.length; j++) {
                if(cache.contains(logs[j][0])){
                    continue;
                }
                if(logs[j][1] > right){
                    break;
                }
                if(logs[j][1] >= left){
                    res[i] --;
                    cache.add(logs[j][0]);
                }
            }
        }
        return res;
    }

    /**
     * 那个男人的参考答案
     */
    public int[] countServersAnswer(int n, int[][] logs, int x, int[] queries) {
//        排序，没问题，这里思路一致
        Arrays.sort(logs, Comparator.comparingInt(o -> o[1]));
//        建立 queries 的索引数组
        Integer[] index = new Integer[queries.length];
        for (int i = 0; i < queries.length; i++) {
            index[i] = i;
        }
//        对 queries 的索引数组排序
        Arrays.sort(index, Comparator.comparingInt(o -> queries[o]));

        int result[] = new int[queries.length], map[] = new int[n + 1], count = n;
//        遍历 queries
        for (int i = 0, j = 0, k = 0; i < queries.length; i++) {
//            内层两个循环没看懂
            for (; k < logs.length && logs[k][1] <= queries[index[i]]; k++) {
                count -= map[logs[k][0]]++ == 0 ? 1 : 0;
            }
            for (; j < logs.length && logs[j][1] < queries[index[i]] - x; j++) {
                count += --map[logs[j][0]] == 0 ? 1 : 0;
            }
            result[index[i]] = count;
        }
        return result;
    }

    /**
     * 卧槽，这个解法很巧妙，思路也异常清晰
     * 整体时间复杂度不小，但是最终控制在 O(n+m) 内，只要不是 O(n*m)，就基本不会超时
     */
    static class SubSolution {
        public int[] countServers(int n, int[][] logs, int x, int[] queries) {
            int[] res = new int[queries.length];

            List<int[]> list = new ArrayList<>();
            for (int[] log : logs) {
                list.add(new int[]{log[1], log[0], 0});
            }


            for (int i = 0; i < queries.length; i++) {
                list.add(new int[]{queries[i], i, 1});
            }

//            草，这是什么奇妙的排序，好像启发了我的思路
            list.sort((a, b) -> {
                int d = a[0] - b[0];
                if (d == 0) {
                    return a[2] - b[2];
                }
                return d;
            });


            Deque<int[]> deque = new ArrayDeque<>();
//            服务器收到了几条消息
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                int type = list.get(i)[2];
                int time = list.get(i)[0];
                int idx = list.get(i)[1];
                if (type == 0) {
//                    服务器处理流程
                    deque.addLast(list.get(i));
                    map.put(idx, map.getOrDefault(idx, 0) + 1);
                } else {
//                    问题处理流程，开始收割前方存储的服务器数了
//                    若队列有数据，且队首时间小于当前的最小时间，则将其弹出，并对指定服务器消息数 - 1，若消息数为0则删除此服务器
                    while (!deque.isEmpty() && deque.peekFirst()[0] < time - x) {
                        int[] first = deque.pollFirst();
                        int j = first[1];
                        map.put(j, map.get(j) - 1);
                        if (map.get(j) == 0) {
                            map.remove(j);
                        }
                    }
//                    经过上面的删减后，map剩余的服务器数则是时间区间内接收到数据的服务器
                    res[idx] = n - map.size();
                }
            }
            return res;
        }
    }

    public static void main(String[] args) {
        final Solution demo = new Solution();

        System.out.println(demo.longestString(3, 2, 2));
    }
}
