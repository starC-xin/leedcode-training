package easy;

import struct.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 2021/11/2
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *  
 *
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author x.c
 */
public class Solution144 {

    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2, new TreeNode(3), null);

        new Solution144().preorderTraversal(root).forEach(System.out::println);
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        return firstTreeStack(root);
    }

    private List<Integer> firstTree(TreeNode node, List<Integer> list){
        if(null == node){
            return list;
        }
        list.add(node.val);
        firstTree(node.left, list);
        firstTree(node.right, list);
        return list;
    }

    /**
     * 使用迭代的方式实现
     */
    private List<Integer> firstTreeStack(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(null == root){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode temp = root.left;

        result.add(root.val);

        while(stack.size() > 0){
            if(null == temp){
                temp = stack.pop();
                temp = temp.right;
            }
            if(null != temp){
                result.add(temp.val);
                stack.push(temp);
                temp = temp.left;
            }
        }
        return result;
    }
}
