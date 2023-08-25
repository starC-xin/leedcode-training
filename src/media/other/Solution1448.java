package media.other;

import struct.TreeNode;

/**
 * 2023/08/25
 *
 * @author x.z
 */
public class Solution1448 {
    /**
     * 可以使用简单的DFS，然后保留路径上的最大值
     * 最大值与节点值进行比较，如符合，则 +1
     */
    public int goodNodes(TreeNode root) {
        return dfs(root, root.val);
    }

    private int dfs(TreeNode node, int curMax){
        if(null == node){
            return 0;
        }
        int count = 0;
        if(node.val >= curMax){
            count ++;
        }
        count += dfs(node.left, Math.max(curMax, node.val));
        count += dfs(node.right, Math.max(curMax, node.val));
        return count;
    }
}
