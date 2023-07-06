package easy;

import struct.TreeNode;

/**
 * 2021/10/19
 *
 * 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 *
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 *
 * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sum-of-root-to-leaf-binary-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution1022 {

    public static void main(String[] args){
//        TreeNode subLeft = new TreeNode(0);
//        TreeNode subRight = new TreeNode(1);
//        TreeNode left = new TreeNode(0, subLeft, subRight);
//        subLeft = new TreeNode(0);
//        subRight = new TreeNode(1);
//        TreeNode right = new TreeNode(1, subLeft, subRight);
//        TreeNode root = new TreeNode(1, left, right);

        TreeNode root = new TreeNode(1, new TreeNode(1), null);
        System.out.println("total: " + new Solution1022().sumRootToLeaf(root));
    }

    public int sumRootToLeaf(TreeNode root) {
        return def(root, "", 0);
    }

    /**
     * 深度遍历
     */
    private int def(TreeNode root, String curStr, int curTotal){
        if(root == null){
            return curTotal;
        }
        if(root.left == null && root.right == null){
            return curTotal + binToOex(curStr + root.val);
        }
        curStr += root.val;
        curTotal = def(root.left, curStr, curTotal);
        curTotal = def(root.right, curStr, curTotal);
        return curTotal;
    }

    /**
     * 二进制至十进制
     */
    private int binToOex(String bin){
        char[] chs = bin.toCharArray();
        int oex = 0;
        for(int i = chs.length - 1; i >= 0; i --){
            if(chs[i] - '0' == 1){
                oex += Math.pow(2, chs.length - i - 1);
            }
        }
        return oex;
    }

    /**
     * 耗时与占用排名前列
     */
    int sum = 0;
    public void dfs(TreeNode root, int val) {
        if(root == null) return;
        if(root.left == null && root.right == null) sum += val * 2 + root.val;
        dfs(root.left, 2 * val + root.val);
        dfs(root.right, 2 * val + root.val);
    }
    public int sumRootToLeafOther(TreeNode root) {
        dfs(root, 0);
        return sum;
    }
}
