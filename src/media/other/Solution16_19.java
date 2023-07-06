package media.other;

import utils.Common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 2023/6/22
 * 面试题 16.19. 水域大小
 * 你有一个用于表示一片土地的整数矩阵land，该矩阵中每个点的值代表对应地点的海拔高度。若值为0则表示水域。由垂直、水平或对角连接的水域为池塘。池塘的大小是指相连接的水域的个数。编写一个方法来计算矩阵中所有池塘的大小，返回值需要从小到大排序。
 * @author x.z
 */
public class Solution16_19 {
    public int[] pondSizes(int[][] land) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                final int size = findPond(i, j, land);
                if(size > 0){
                    list.add(size);
                }
            }
        }
        list.sort(Comparator.naturalOrder());
        final int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    final int[][] dir = {
            {-1, 0}, {-1, -1}, {-1, 1},
            {0, -1}, {0, 1},
            {1, 0}, {1, -1}, {1, 1}
    };
    private int findPond(int i, int j, int[][] land) {
        if(!(i >= 0 && i < land.length && j >= 0 && j < land[0].length)){
            return 0;
        }
        if(land[i][j] != 0){
            return 0;
        }
        land[i][j] = -1;
        int sum = 1;
        for (int k = 0; k < dir.length; k++) {
            sum += findPond(i + dir[k][0], j + dir[k][1], land);
        }
        return sum;
    }

    public static void main(String[] args) {
        final Solution16_19 demo = new Solution16_19();

        final String exp = "[[0,2,1,0],[0,1,0,1],[1,1,0,1],[0,1,0,1]]";
        System.out.println(Arrays.toString(demo.pondSizes(Common.string2IntIntArr(exp))));
    }
}
