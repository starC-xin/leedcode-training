package day;

import struct.Node;

import java.util.*;

/**
 * 2022/3/10
 * 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
 *
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 *
 * 示例 1：
 * 输入：root = [1,null,3,2,4,null,5,6]
 * 输出：[1,3,5,6,2,4]
 *
 * 示例 2：
 * 输入：root = [1,null,2,3,4,5,null,null,6,7,null,8,null,9,10,null,null,11,null,12,null,13,null,null,14]
 * 输出：[1,2,3,6,7,11,14,4,8,12,5,9,13,10]
 *  
 *
 * 提示：
 * 节点总数在范围 [0, 104]内
 * 0 <= Node.val <= 104
 * n 叉树的高度小于或等于 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution589 {
    /**
     * TODO 先尝试递归，捋清其中的思路
     *      递归暴力破解，理应秒杀
     *      递归过了，很简单
     */
    public List<Integer> preorder(Node root) {
        List<Integer> result = new LinkedList<>();
        if(root == null){
            return result;
        }
        dfs(root, result);
        return result;
    }


    private void dfs(Node node, List<Integer> result){
        result.add(node.val);
        if(node.children == null){
            return;
        }
        for(Node child : node.children){
            dfs(child, result);
        }
    }

    /**
     * TODO 此处尝试使用迭代计算前序遍历
     *      算了，不写了，类似遍历迭代是真麻烦
     *      两个思路
     *      一、模仿递归中的栈运作
     *      二、直接将节点排列好，再执行序列编排
     */
    public List<Integer> preorder1(Node root){
//        保存遍历序列
        List<Integer> result = new LinkedList<>();
        if(root == null){
            return result;
        }
        Stack<Integer> stackNum = new Stack<>();
        Stack<Node> stackNode = new Stack<>();
        int num = 0;
        Node node = root;
        while(!stackNode.empty() || num < node.children.size()){
            result.add(node.val);
            if(null == node.children){
                node = stackNode.peek();
                num = stackNum.peek();
                if(node.children.size() > num){
                    node = node.children.get(num ++);
                }else{

                }

            }else{
                stackNode.push(node);
                stackNum.push(num + 1);

                node = node.children.get(num ++);
            }
        }

        return result;
    }
}
