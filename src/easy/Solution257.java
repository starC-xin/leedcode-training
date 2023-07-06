package easy;

import struct.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 2023/6/22
 * 257. 二叉树的所有路径
 * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
 *
 * 叶子节点 是指没有子节点的节点。
 *
 *
 * @author x.z
 */
public class Solution257 {
    public List<String> binaryTreePaths(TreeNode root) {
        return dfs(root, null, new ArrayList<>());
    }

    private List<String> dfs(TreeNode node, String currentPath, List<String> cache){
        if(null == node){
            return cache;
        }
        currentPath = null == currentPath ? node.val + "" : currentPath + "->" + node.val;
        if(null == node.left && null == node.right){
            cache.add(currentPath);
            return cache;
        }
        dfs(node.left, currentPath, cache);
        dfs(node.right, currentPath, cache);
        return cache;
    }

    /**
     * 官方示例比我快
     * 大概是调用了 StringBuilder 的原因，比起我直接操作字符串要快
     */
    static class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> res = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            traversal(root,path,res);
            return res;
        }

        private void traversal(TreeNode root,List<Integer> path,List<String> res){
            path.add(root.val);
            if(root.left==null && root.right==null){
                StringBuilder sb = new StringBuilder();

                for(int i = 0;i<path.size()-1;i++){
                    sb.append(path.get(i)).append("->");
                }
                sb.append(path.get(path.size()-1));
                res.add(sb.toString());
                return;
            }

            if(root.left!=null){
                traversal(root.left,path,res);
                path.remove(path.size()-1);
            }
            if(root.right!=null){
                traversal(root.right,path,res);
                path.remove(path.size()-1);
            }

        }
    }
}
