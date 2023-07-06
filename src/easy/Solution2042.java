package easy;

/**
 * 2021/10/20
 *
 * 句子是由若干 token 组成的一个列表，token 间用 单个 空格分隔，句子没有前导或尾随空格。每个 token 要么是一个由数字 0-9 组成的不含前导零的 正整数 ，要么是一个由小写英文字母组成的 单词 。
 *
 * 示例，"a puppy has 2 eyes 4 legs" 是一个由 7 个 token 组成的句子："2" 和 "4" 是数字，其他像 "puppy" 这样的 tokens 属于单词。
 * 给你一个表示句子的字符串 s ，你需要检查 s 中的 全部 数字是否从左到右严格递增（即，除了最后一个数字，s 中的 每个 数字都严格小于它 右侧 的数字）。
 *
 * 如果满足题目要求，返回 true ，否则，返回 false 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-if-numbers-are-ascending-in-a-sentence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution2042 {
    public boolean areNumbersAscending(String s) {
        String[] children = s.split(" ");
        int pre = -1, cur = 0;
        for(String child : children){
            char first = child.charAt(0);
            if(first < '0' || first > '9'){
                continue;
            }
            char[] chs = child.toCharArray();
            pre = cur;
            cur = 0;
            for(char ch : chs){
                cur *= 10;
                cur += ch - '0';
            }
            if(cur <= pre){
                return false;
            }
        }
        return true;
    }
}
