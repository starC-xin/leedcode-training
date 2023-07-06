package high;

import utils.Common;

import java.util.*;

/**
 * 2023/5/24
 * 1377. T 秒后青蛙的位置
 * 给你一棵由 n 个顶点组成的无向树，顶点编号从 1 到 n。青蛙从 顶点 1 开始起跳。规则如下：
 *
 * 在一秒内，青蛙从它所在的当前顶点跳到另一个 未访问 过的顶点（如果它们直接相连）。
 * 青蛙无法跳回已经访问过的顶点。
 * 如果青蛙可以跳到多个不同顶点，那么它跳到其中任意一个顶点上的机率都相同。
 * 如果青蛙不能跳到任何未访问过的顶点上，那么它每次跳跃都会停留在原地。
 * 无向树的边用数组 edges 描述，其中 edges[i] = [ai, bi] 意味着存在一条直接连通 ai 和 bi 两个顶点的边。
 *
 * 返回青蛙在 t 秒后位于目标顶点 target 上的概率。与实际答案相差不超过 10-5 的结果将被视为正确答案。
 * @author x.z
 */
public class Solution1377 {
    /**
     * TODO BFS
     *      算是通过了，如果采用回归算法，也许会更快
     */
    public double frogPosition(int n, int[][] edges, int t, int target) {
        if(edges.length == 0 && target == 1){
            return 1;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], var -> new ArrayList<>()).add(edge[1]);
            map.computeIfAbsent(edge[1], var -> new ArrayList<>()).add(edge[0]);
        }
        double result = 0.0;
        double[] probability = new double[n + 1];
        probability[1] = 1;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> queueTmp = new LinkedList<>();
        queue.offer(1);
        for (int i = 0; i <= t; i++) {
            while(queue.size() > 0){
                final Integer poll = queue.poll();
                visited[poll] = true;
                final List<Integer> list = map.getOrDefault(poll, new ArrayList<>());
//                切断循环
                if(poll == target){
                    if ((poll != 1 && list.size() == 1) || i == t) {
                        result = probability[poll];
                    }
                    i = t;
                    break;
                }
                int nextPro = poll == 1 ? list.size() : list.size() - 1;
                for (Integer next : list) {
                    if(visited[next]){
                        continue;
                    }
                    probability[next] = probability[poll] / nextPro;
                    queueTmp.offer(next);
                }
            }
//            切换到下一层开始循环
            Queue<Integer> tmp = queue;
            queue = queueTmp;
            queueTmp = tmp;
        }
        return result;
    }

    /**
     * TODO 此处采用回归实现，尽量不使用容器类
     */
    public double frogPosition1(int n, int[][] edges, int t, int target) {
        if(edges.length == 0 && target == 1){
            return 1;
        }
        boolean[][] graph = new boolean[n + 1][n + 1];
        int[] children = new int[n];
        for (int[] edge : edges) {
            graph[edge[0]][edge[1]] = true;
            graph[edge[1]][edge[0]] = true;
            children[edge[0] - 1]++;
            children[edge[1] - 1]++;
        }
        double[] probability = new double[n + 1];
        boolean[] visited = new boolean[n + 1];
        probability[1] = 1;
        if(dfs(t, 0, 1, target, graph, visited, probability, children)){
            return probability[target];
        }else{
            return 0.0;
        }
    }

    private boolean dfs(final int t, int curT, int curNode, final int target, boolean[][] graph, boolean[] visited, double[] probability, int[] children){
        if(target == curNode){
            if ((1 == curNode || children[curNode - 1] != 1) && t != curT) {
                probability[curNode] = 0.0;
            }else if(t < curT){
                probability[curNode] = 0.0;
            }
            return true;
        }
        if(curT > t){
            return false;
        }
        visited[curNode] = true;
        int nextPro = curNode == 1 ? children[curNode - 1] : children[curNode - 1] - 1;
        final boolean[] wannaChildren = graph[curNode];
        for (int i = 0; i < wannaChildren.length; i++) {
            if(wannaChildren[i] && !visited[i]){
                probability[i] = probability[curNode] / nextPro;
                if(dfs(t, curT + 1, i, target, graph, visited, probability, children)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final Solution1377 demo = new Solution1377();

        for (Case item : genCase()) {
            System.out.println(demo.frogPosition1(item.n, item.edges, item.t, item.target));
        }
    }

    private static List<Case> genCase(){
        List<Case> result = new ArrayList<>();
//        result.add(new Case(7, 2, 4, "[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]"));
//        result.add(new Case(3, 1, 2, "[2,1],[3,2]"));
//        result.add(new Case(5, 3, 2, "[1,5],[1,4],[5,3],[3,2]"));
//        result.add(new Case(9, 9, 1, "[2,1],[3,2],[4,3],[5,3],[6,5],[7,3],[8,4],[9,5]"));

//        result.add(new Case(8, 7, 7, "[2,1],[3,2],[4,1],[5,1],[6,4],[7,1],[8,7]"));
        result.add(new Case(82, 6, 73, "[2,1],[3,2],[4,2],[5,1],[6,2],[7,2],[8,3],[9,8],[10,6],[11,10],[12,1],[13,1],[14,12],[15,8],[16,3],[17,15],[18,16],[19,17],[20,7],[21,9],[22,9],[23,20],[24,5],[25,10],[26,4],[27,11],[28,8],[29,11],[30,11],[31,7],[32,25],[33,8],[34,27],[35,14],[36,27],[37,9],[38,33],[39,35],[40,6],[41,25],[42,2],[43,25],[44,9],[45,26],[46,23],[47,40],[48,34],[49,26],[50,39],[51,10],[52,47],[53,43],[54,6],[55,49],[56,44],[57,34],[58,15],[59,49],[60,13],[61,32],[62,31],[63,25],[64,50],[65,41],[66,33],[67,2],[68,34],[69,4],[70,49],[71,67],[72,51],[73,19],[74,22],[75,34],[76,13],[77,53],[78,15],[79,62],[80,52],[81,7],[82,63]"));

        return result;
    }

    private static class Case{
        int n;
        int t;
        int target;
        int[][] edges;

        public Case(int n, int t, int target, String str) {
            this.n = n;
            this.t = t;
            this.target = target;
            this.edges = Common.string2IntIntArr(str);
        }
    }
}
