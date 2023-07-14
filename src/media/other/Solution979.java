package media.other;

import struct.TreeNode;

/**
 * 2023/7/14
 *
 * @author x.z
 */
public class Solution979 {
    /**
     * TODO 破局点在于深搜递归方法的返回值的含义
     *      如果能想明白 dfs(node) 返回值表示的含义为 node 的父节点需要从以 node 为根节点的子树“拿走”得金币数量，就很简单了
     *      这里的“拿走”并非“xx从xx处取走”的意思，而是“xx与xx交换”的意思，即这里存在正负关系，而非单纯的输出/输入
     *      简单说，dfs(node)>0 即node需要向外输出dfs(node)个金币；dfs(node)<0 则反之；dfs(node)=0 则无视
     *      注意：如果为 0，并不代表此子树没有移动操作，子树内部可能也是有金币移动的，但因为金币数量和子树节点相同，即内部抵消了
     *      在这点的基础上，再去思考移动次数
     *      根据题目定义，可以再此基础上进行递推，下面用 move(node) 表示以 node 为根节点的子树需要移动的次数
     *      那么 move(node)=|dfs(node)|=|dfs(left) + dfs(right) - 1|
     *      dfs(left) 意为 node 左子树，dfs(right) 同理
     *      -1 是因为 node本身需要保留一枚金币
     *      最终将所有 move 累计，则为本题期望的解答
     */
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return move;
    }

    private int move = 0;
    public int dfs(TreeNode root) {
        int moveleft = 0;
        int moveright = 0;
        if (root == null) {
            return 0;
        }
        if (root.left != null) {
            moveleft = dfs(root.left);
        }
        if (root.right != null) {
            moveright = dfs(root.right);
        }
        move += Math.abs(moveleft) + Math.abs(moveright);
        return moveleft + moveright + root.val - 1;
    }
}
