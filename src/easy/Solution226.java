package easy;

import struct.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 2023/6/17
 *
 * @author x.z
 */
public class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        if(null == root){
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
            if(null != node.left){
                queue.offer(node.left);
            }
            if(null != node.right){
                queue.offer(node.right);
            }
        }
        return root;
    }
}
