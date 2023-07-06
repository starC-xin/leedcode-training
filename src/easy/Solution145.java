package easy;

import struct.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 2021/11/2
 *
 * 给定一个二叉树，返回它的 后序 遍历。
 *
 * 示例:
 *
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        return lastTree(root, new ArrayList<>());
    }

    private List<Integer> lastTree(TreeNode node, List<Integer> list){
        if(null == node){
            return list;
        }
        lastTree(node.left, list);
        lastTree(node.right, list);
        list.add(node.val);
        return list;
    }

    /**
     * TODO 后序迭代暂未写出
     */
    private List<Integer> lastTreeStack(TreeNode root){
        List<Integer> result = new ArrayList<>();
        if(null == root){
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode temp = root.left;

        while(stack.size() > 0){
            if(null == temp){
                temp = stack.pop();
                result.add(temp.val);
                temp = temp.right;
            }
            if(null != temp){
                stack.push(temp);
                temp = temp.left;
            }
        }
        return result;
    }
}
