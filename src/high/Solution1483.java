package high;

import utils.Common;

import java.util.*;
import java.util.function.Function;

/**
 * 2023/6/12
 * 1483. 树节点的第 K 个祖先
 * 给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0 的节点。
 *
 * 树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。
 *
 * 实现 TreeAncestor 类：
 *
 * TreeAncestor（int n， int[] parent） 对树和父数组中的节点数初始化对象。
 * getKthAncestor(int node, int k) 返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。
 *
 * @author x.z
 */
public class Solution1483 {
    int size;
    int[] parent;
    Map<Integer, List<Integer>> cache;

    Map<Integer, List<Integer>> tree;

    public Solution1483(int n, int[] parent) {
        size = n;
        this.parent = parent;
//        cache = new HashMap<>();
//        tree = new HashMap<>();
//
//        for (int i = parent.length - 1; i > 0; i--) {
//            tree.computeIfAbsent(parent[i], var -> new ArrayList<>()).add(i);
//        }
//
//        bfs();
    }

    public int getKthAncestor(int node, int k){
        while(k -- > 0){
            if(-1 == node){
                break;
            }
            node = parent[node];
        }
        return node;
    }

//    private void bfs(){
//        Queue<Integer> queue = new LinkedList<>();
//        queue.offer(0);
//        final Function<Integer, List<Integer>> func = var -> new ArrayList<>();
//        cache.computeIfAbsent(0, func);
//        while(!queue.isEmpty()){
//            final Integer poll = queue.poll();
//            final List<Integer> children = tree.get(poll);
//            if(null == children){
//                continue;
//            }
//            final List<Integer> c = cache.get(poll);
//            for (Integer child : children) {
//                queue.offer(child);
//                final List<Integer> parentL = cache.computeIfAbsent(child, func);
//                parentL.add(poll);
//                parentL.addAll(c);
//            }
//        }
//    }
//
//    public int getKthAncestor(int node, int k){
//        final List<Integer> parentL = cache.get(node);
//        return parentL.size() < k ? - 1 : parentL.get(k - 1);
//    }

//    public int getKthAncestor(int node, int k) {
//        final List<Integer> parentL = cache.computeIfAbsent(node, var -> new ArrayList<>());
//        if(parentL.size() < k){
//            int cur;
//            List<List<Integer>> tmpL = new LinkedList<>();
//            if(parentL.isEmpty()){
//                cur = node;
//            }else{
//                cur = parentL.get(parentL.size() - 1);
//                tmpL.add(cache.computeIfAbsent(cur, var -> new ArrayList<>()));
//            }
//            while (parentL.size() < k && cur >= 0) {
//                cur = parent[cur];
//                parentL.add(cur);
//                for (List<Integer> iL : tmpL) {
//                    if(!iL.isEmpty() && iL.get(iL.size() - 1) <= cur){
//                        continue;
//                    }
//                    iL.add(cur);
//                }
//                tmpL.add(cache.computeIfAbsent(cur, var -> new ArrayList<>()));
//            }
//        }
//        return k > parentL.size() ? -1 : parentL.get(k - 1);
//    }

    /**
     * 官方题解
     * 倍增思路
     * TODO 下来了解一下倍增思路和区间DP
     *
     */
    static class TreeAncestor {
        static final int LOG = 16;
        int[][] ancestors;

        public TreeAncestor(int n, int[] parent) {
            ancestors = new int[n][LOG];
            for (int i = 0; i < n; i++) {
                Arrays.fill(ancestors[i], -1);
            }
            for (int i = 0; i < n; i++) {
                ancestors[i][0] = parent[i];
            }
            for (int j = 1; j < LOG; j++) {
                for (int i = 0; i < n; i++) {
                    if (ancestors[i][j - 1] != -1) {
                        ancestors[i][j] = ancestors[ancestors[i][j - 1]][j - 1];
                    }
                }
            }
        }

        public int getKthAncestor(int node, int k) {
            for (int j = 0; j < LOG; j++) {
                if (((k >> j) & 1) != 0) {
                    node = ancestors[node][j];
                    if (node == -1) {
                        return -1;
                    }
                }
            }
            return node;
        }
    }

    public static void main(String[] args) {
        int n = 12;
        int[] parent = Common.string2IntArr("[-1,0,1,2,3,4,5,6,7,8,9,10]");

        final Solution1483.TreeAncestor demo = new Solution1483.TreeAncestor(n, parent);

        System.out.println(demo.getKthAncestor(10, 9));
        System.out.println(demo.getKthAncestor(3, 1));
        System.out.println(demo.getKthAncestor(5, 2));
        System.out.println(demo.getKthAncestor(6, 3));
    }
}
