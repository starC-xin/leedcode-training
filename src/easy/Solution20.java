package easy;

import java.util.Stack;

/**
 * 2023/5/9
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 *
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 *
 * 来源力扣： https://leetcode.cn/problems/valid-parentheses/
 * @author x.z
 */
public class Solution20 {

    /**
     * 思路：利用栈性质，遇到左括号执行压入，遇到右括号弹出一个左括号，
     *      当弹出的括号与右括号不匹配则直接返回 false
     */
    public boolean isValid(String s) {
        final char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char aChar : chars) {
            if(aChar == '(' || aChar == '[' || aChar == '{'){
                stack.push(aChar);
                continue;
            }
            if(stack.isEmpty()){
                return false;
            }
            final char c = stack.pop();
            if(c == '('){
                if(aChar != ')'){
                    return false;
                }
            }else if(aChar - c != 2){
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        final Solution20 test = new Solution20();
        final String s = "({{{{}}}))";

        System.out.println(test.isValid(s));
        System.out.println(((int) '('));
        System.out.println(((int) ')'));

        System.out.println(((int) '['));
        System.out.println(((int) ']'));

        System.out.println(((int) '{'));
        System.out.println(((int) '}'));

        System.out.println(((char) 92));
        System.out.println(((char) 124));
    }

}
