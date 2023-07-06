package media.other;

import struct.TreeNode;

import java.util.*;

/**
 * 2023/5/30
 *1110. 删点成林
 * 给出二叉树的根节点 root，树上每个节点都有一个不同的值。
 *
 * 如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。
 *
 * 返回森林中的每棵树。你可以按任意顺序组织答案。
 * @author x.z
 */
public class Solution1110 {
    /**
     * TODO 思路很简单，直接dfs，前序遍历，然后删节点然后入列表
     *      虽然是中等，但也可以秒杀嘛
     *      算了，还是bfs吧，设计上方便点似乎
     */
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> store = new ArrayList<>();
        Set<Integer> toDelete = new HashSet<>();
        for (Integer num : to_delete) {
            toDelete.add(num);
        }

        dfs(root, true, null, false, toDelete, store);
        return store;
    }

    private void dfs(TreeNode node, boolean isRoot, TreeNode parent, boolean leftOrRight, Set<Integer> toDelete, List<TreeNode> store){
        if(node == null){
            return;
        }

        if (toDelete.contains(node.val)){
            if(null != parent){
                if(leftOrRight){
                    parent.left = null;
                }else{
                    parent.right = null;
                }
            }
            dfs(node.left, true, node, true, toDelete, store);
            dfs(node.right, true, node, false, toDelete, store);
            return;
        }

        if(isRoot){
            store.add(node);
        }
        dfs(node.left, false, node, true, toDelete, store);
        dfs(node.right, false, node, false, toDelete, store);
    }

    public static void main(String[] args) {

    }
}
