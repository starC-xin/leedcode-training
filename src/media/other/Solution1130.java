package media.other;

import java.util.Stack;

/**
 * 2023/5/31
 * 1130. 叶值的最小代价生成树
 * 给你一个正整数数组 arr，考虑所有满足以下条件的二叉树：
 *
 * 每个节点都有 0 个或是 2 个子节点。
 * 数组 arr 中的值与树的中序遍历中每个叶节点的值一一对应。
 * 每个非叶节点的值等于其左子树和右子树中叶节点的最大值的乘积。
 * 在所有这样的二叉树中，返回每个非叶节点的值的最小可能总和。这个和的值是一个 32 位整数。
 *
 * 如果一个节点有 0 个子节点，那么该节点为叶节点。
 *
 * @author x.z
 */
public class Solution1130 {
    /**
     * TODO 目前来看应该是找到所有叶子节点中的较大值相乘
     *      总是要排除最小值
     *      DP 来计算，主要是判断如何来排除最小值
     *      按照三个为一组，选出三个中的两个最大值，相乘作为当前结果集
     *      若最后余一个，则最后四个值一起选出最大值
     *      若最后余两个，则最后五个数排除两个值，其余相乘
     * TODO 修正：还是需要一个 DP 数组，只有贪心会出问题
     * TODO 修正：不对，需要计算树的最大层数，只会排除底层的满子树中的最小值，无子树的则直接相乘
     *      如果这样的话，可以直接贪心，把整个树拉长成一个近似的链表结构
     * TODO 修正：那就还是回到思路2，建立 dp 数组
     * TODO 算了，没思路，看看官方题解
     * TODO 官方题解给出了两个思路，dp思路 O(n^3) 和单调栈思路 O(n)
     *      dp 思路为 由上而下切分树，获取所有合法树，取最小值
     *      单调栈思路为 由下而上，合并子树，返回最终合并代价（也叫贪心，和贪心相似
     *      此处尝试使用单调栈思路
     */
    public int mctFromLeafValues(int[] arr) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (int x : arr) {
            while(! stack.isEmpty() && stack.peek() <= x){
                final Integer y = stack.pop();
//                注意此处 isEmpty 细节
                if(stack.isEmpty() || x <= stack.peek()){
                    sum += x * y;
                }else{
                    sum += stack.peek() * y;
                }
            }
            stack.push(x);
        }
        while (stack.size() >= 2){
            final Integer one = stack.pop();
            sum += stack.peek() * one;
        }
        return sum;
    }
}
