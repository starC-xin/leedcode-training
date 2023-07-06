package day;

/**
 * 2021/10/19
 * 每日打卡题
 *
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 *
 * 实现词典类 WordDictionary ：
 *
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 * ----case one
 * ["WordDictionary","addWord","addWord","search","search","search","search","search","search","search","search"]
 * [[],["a"],["ab"],["a"],["a."],["ab"],[".a"],[".b"],["ab."],["."],[".."]]
 *
 * 预期：[null,null,null,true,true,true,false,true,false,true,true]
 *
 * @author x.c
 */
public class WordDictionary {
    public static void main(String[] args){
        String[] add = {"bad", "dad", "mad"};
        String[] search = {"pad", "bad", ".ad", "b.."};

//        case one
//        String[] add = {"a", "ab"};
//        String[] search = {"a", "a.", "ab", ".a", ".b", "ab.", ".", ".."};
        WordDictionary dic = new WordDictionary();

        for(String var : add){
            dic.addWord(var);
        }

        for(String var : search){
            System.out.println(dic.search(var));
        }
    }


    private TreeNode root = new TreeNode();

    class TreeNode{
        TreeNode[] children = new TreeNode[26];
        boolean isEnd = false;
    }
    public WordDictionary() {
    }

    public void addWord(String word) {
        char[] chs = word.toCharArray();
        TreeNode cur = root;
        for (char ch : chs){
            int index = ch - 'a';
            if(null == cur.children[index]){
                cur.children[index] = new TreeNode();
            }
            cur = cur.children[index];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        char[] chs = word.toCharArray();
        return find(0, chs.length, chs, root);
    }

    private boolean find(int index, int len, char[] chs, TreeNode node){
        char ch = chs[index];
        if(ch == '.'){
            for(TreeNode temp : node.children){
                if(null == temp){
                    continue;
                }
                if(index + 1 >= len){
                    if(temp.isEnd){
                        return true;
                    }
                    continue;
                }
                if(find(index + 1, len, chs, temp)){
                    return true;
                }
            }
            return false;
        }

        int i = ch - 'a';
        TreeNode cur = node.children[i];
        if(null == cur){
            return false;
        }
        if(index + 1 >= len){
            return cur.isEnd;
        }
        return find(index + 1, len, chs, cur);
    }
}
