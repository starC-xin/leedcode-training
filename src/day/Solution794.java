package day;

/**
 * 2021/12/9
 * 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
 *
 * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
 *
 * 以下是井字游戏的规则：
 *
 * 玩家轮流将字符放入空位（' '）中。
 * 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
 * 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-tic-tac-toe-state
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution794 {

    public static void main(String[] args){
//        System.out.println(new Solution794().validTicTacToe(new String[]{"XXX","   ","OOO"}));

//        System.out.println(new Solution794().validTicTacToe(new String[]{"XOX","OXO","XOX"}));

//        System.out.println(new Solution794().validTicTacToe(new String[]{"XXX","XOO","OO "}));

        System.out.println(new Solution794().validTicTacToe(new String[]{"OXX","XOX","OXO"}));
    }

    public boolean validTicTacToe(String[] board) {
        char x = 'X';
        char o = 'O';
        char[][] chs = new char[3][3];
        int row = 0, col;
        int num1 = 0;
        int num2 = 0;
        for(String str : board){
            col = 0;
            for(char ch : str.toCharArray()){
                chs[row][col] = ch;
                col ++;
                if(ch == x){
                    num1 ++;
                }
                if(ch == o){
                    num2 ++;
                }
            }
            row ++;
        }
        if(num1 < num2){
            return false;
        }
        if(Math.abs(num1 - num2) > 1){
            return false;
        }
//        扫描是否存有连线
        boolean flag1 = false;
        boolean flag2 = false;
        if(num1 >= 3){
            char ch = chs[1][1];
            if((ch == chs[0][0] && ch == chs[2][2]) || (ch == chs[0][2] && ch == chs[2][0])){
                if(ch == o){
                    return num1 == num2;
                }
                if(ch == x){
                    return num1 > num2;
                }
                return true;
            }
            for(row = 0; row < chs.length; row ++){
                if(chs[row][0] == chs[row][1] && chs[row][0] == chs[row][2]){
                    if(chs[row][0] == o){
                        flag2 = true;
                    }
                    if(chs[row][0] == x){
                        flag1 = true;
                    }
                }
            }
            if(! flag1 && ! flag2){
                for(col = 0; col < chs.length; col ++){
                    if(chs[0][col] == chs[1][col] && chs[0][col] == chs[2][col]){
                        if(chs[0][col] == o){
                            flag2 = true;
                        }
                        if(chs[0][col] == x){
                            flag1 = true;
                        }
                    }
                }
            }
        }
        if(flag1 && ! flag2){
            return num1 > num2;
        }
        if(! flag1 && flag2){
            return num1 == num2;
        }

        return ! (flag1 && flag2);
    }
}
