package easy;

import struct.TreeNode;

/**
 * 2021/10/20
 *
 * 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：false
 *
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：false
 *  
 *
 * 提示：
 *
 * 树中节点的数目在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * ---- case 1
 * [] 0
 *
 * false
 *
 * ---- case 2
 * [-2,null,-3]
 * -5
 *
 * true
 *
 * ---- case 3
 * [1,2]
 * 1
 *
 * false
 *
 * @author x.c
 */
public class Solution1733 {
    public static void main(String[] args){
        TreeNode root = new TreeNode(-2, null, new TreeNode(-3));
        int targetSum = -5;

//        TreeNode root = new TreeNode(1, new TreeNode(2), null);
//        int targetSum = 1;

        System.out.println(new Solution1733().hasPathSum(root, targetSum));
    }


    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(null == root){
            return false;
        }
        return dfs(root, targetSum, 0);
    }

    /**
     * 深度遍历
     * 从 root 节点依次向下累计计算，当发现大于时则执行回溯
     *                             当发现等于时执行叶子节点判定，满足则返回，否则回溯
     * 直到遍历整个树
     *
     * TODO 思路修正，因为节点可以为负数，所以无法根据简单的大小于来判定，现修正判定条件为 ==
     * TODO 思路修正，因上一步的错误，此处注释掉多余代码---1
     */
    public boolean dfs(TreeNode node, int targetSum, int curSum){
        curSum += node.val;
        if(null == node.left && null == node.right){
            return targetSum == curSum;
        }
//        TODO 多余代码段---1
//        if(curSum == targetSum){
//            return true;
//        }
        if(node.left != null){
            if(dfs(node.left, targetSum, curSum)){
                return true;
            }
        }
        if (node.right != null){
            return dfs(node.right, targetSum, curSum);
        }
        return false;
    }
}
