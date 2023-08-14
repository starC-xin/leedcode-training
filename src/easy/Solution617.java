package easy;

import struct.TreeNode;

/**
 * 2023/8/14
 *
 * @author x.z
 */
public class Solution617 {
    /**
     * 水题
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        return subDFS(root1, root2);
    }

    private TreeNode subDFS(TreeNode node1, TreeNode node2){
        if(node1 == null && node2 == null){
            return null;
        }
        if(node1 == null || node2 == null){
            return node1 == null ? node2 : node1;
        }
        node1.val += node2.val;
        node1.left = subDFS(node1.left, node2.left);
        node1.right = subDFS(node1.right, node2.right);
        return node1;
    }
}
