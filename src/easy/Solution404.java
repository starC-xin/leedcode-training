package easy;

import struct.TreeNode;

/**
 * 2023/6/26
 *
 * @author x.z
 */
public class Solution404 {
    public int sumOfLeftLeaves(TreeNode root) {
        return dfs(root, false);
    }

    private int dfs(TreeNode node, boolean isLeft){
        if(null == node){
            return 0;
        }
        if(node.left == null && node.right == null){
            return isLeft ? node.val : 0;
        }
        return dfs(node.left, true) + dfs(node.right, false);
    }
}
