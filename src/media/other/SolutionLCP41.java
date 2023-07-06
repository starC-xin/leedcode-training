package media.other;

import utils.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2023/6/21
 * 在 n*m 大小的棋盘中，有黑白两种棋子，黑棋记作字母 "X", 白棋记作字母 "O"，空余位置记作 "."。当落下的棋子与其他相同颜色的棋子在行、列或对角线完全包围（中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。
 *
 * 「力扣挑战赛」黑白翻转棋项目中，将提供给选手一个未形成可翻转棋子的棋盘残局，其状态记作 chessboard。若下一步可放置一枚黑棋，请问选手最多能翻转多少枚白棋。
 *
 * 注意：
 *
 * 若翻转白棋成黑棋后，棋盘上仍存在可以翻转的白棋，将可以 继续 翻转白棋
 * 输入数据保证初始棋盘状态无可以翻转的棋子且存在空余位置
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fHi6rV
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author x.z
 */
public class SolutionLCP41 {
    /**
     * TODO 反正这种题贪心是走不通的
     *      虽然过了，但过得不太好看
     */
    public int flipChess(String[] chessboard) {
        Boolean[][] chess = new Boolean[chessboard.length][chessboard[0].length()];
        for (int i = 0; i < chessboard.length; i++) {
            final char[] chs = chessboard[i].toCharArray();
            for (int j = 0; j < chs.length; j++) {
                if(chs[j] == 'X'){
                    chess[i][j] = true;
                }else if(chs[j] == 'O'){
                    chess[i][j] = false;
                }
            }
        }

        int res = 0;
        Boolean[][] tmpChs = null;
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
                if(null == chess[i][j]){
                    if(null == tmpChs){
                        tmpChs = copySource(chess);
                    }
                    final int flip = flip(tmpChs, i, j);
                    res = Math.max(res, flip);
                    if(0 != flip){
                        tmpChs = null;
                    }
                }
            }
        }

        return res;
    }

    private int flip(Boolean[][] source, int x, int y){
        int sum = 0;
        source[x][y] = true;
//        top
        sum += subFlip(source, x, y, -1, 0);
//        left-top
        sum += subFlip(source, x, y, -1, -1);
//        right-top
        sum += subFlip(source, x, y, -1, 1);
//        left
        sum += subFlip(source, x, y, 0, -1);
//        right
        sum += subFlip(source, x, y, 0, 1);
//        bottom
        sum += subFlip(source, x, y, 1, 0);
//        left-bottom
        sum += subFlip(source, x, y, 1, -1);
//        right-bottom
        sum += subFlip(source, x, y, 1, 1);

        if(0 == sum){
            source[x][y] = null;
        }
        return sum;
    }

    private int subFlip(Boolean[][] source, int x, int y, int dirX, int dirY){
        int tmpX = x + dirX, tmpY = y + dirY;
        if(!check(source, tmpX, tmpY) || null == source[tmpX][tmpY]){
            return 0;
        }
        int sum = 0;
        List<int[]> wanna = new ArrayList<>();
        List<int[]> iList = new ArrayList<>();
        while(check(source, tmpX, tmpY) && null != source[tmpX][tmpY]){
            if(source[tmpX][tmpY]){
                if(!iList.isEmpty()){
                    wanna.addAll(iList);
                    iList.forEach(item -> source[item[0]][item[1]] = true);
                    sum += iList.size();
                    iList.clear();
                }
            }else{
                iList.add(new int[]{tmpX, tmpY});
            }
            tmpX += dirX;
            tmpY += dirY;
        }
        for (int[] item : wanna) {
            sum += flip(source, item[0], item[1]);
        }
        return sum;
    }

    private Boolean[][] copySource(Boolean[][] source){
        Boolean[][] tmpC = new Boolean[source.length][];
        for (int i = 0; i < tmpC.length; i++) {
            tmpC[i] = source[i].clone();
        }
        return tmpC;
    }

    private boolean check(Boolean[][] source, int x, int y){
        return x >= 0 && x < source.length && y >= 0 && y < source[0].length;
    }

    public static void main(String[] args) {
        final SolutionLCP41 demo = new SolutionLCP41();

        System.out.println(demo.flipChess(Common.string2StringArr("[\"....X.\",\"....X.\",\"XOOO..\",\"......\",\"......\"]")));
    }
}
