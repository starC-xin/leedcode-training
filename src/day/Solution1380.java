package day;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 2022/2/15
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 *
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 *
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 *  
 *
 * 示例 1：
 * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * 输出：[15]
 * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 *
 * 示例 2：
 * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * 输出：[12]
 * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 *
 * 示例 3：
 * 输入：matrix = [[7,8],[1,2]]
 * 输出：[7]
 *  
 *
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= n, m <= 50
 * 1 <= matrix[i][j] <= 10^5
 * 矩阵中的所有元素都是不同的
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lucky-numbers-in-a-matrix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author ZhaoXin
 */
public class Solution1380 {
    /**
     * TODO 初步看题，思路依然是暴力解
     *      耗时2ms，击破84.14%，这属实是我没想到的
     *      但逻辑上一定还有优化空间，看看题解怎么说
     *
     *      看了下题解，给出的两种思路均一致，要么暴力遍历，要么预处理每行/列，再做列/行比较
     *
     *      评论有一个大佬运用数学证明仅存在一个或另个，若有一个，则一定是所有最小值的最大值
     *                                                      或者说所有最大值的最小值
     *                                                      可惜大佬用得是C写的
     *                                                      我不懂，但我大受震撼
     *
     */
    public List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> result = new ArrayList<>();
//        存储已有结果的列
        Set<Integer> cell = new HashSet<>();
//        存储列索引
        int luck;
        for(int i = 0; i < matrix.length; i++){
            int j;
            luck = 0;
//            搜索行最小
            for(j = 1; j < matrix[i].length; j++){
                if(matrix[i][luck] > matrix[i][j]){
                    luck = j;
                }
            }
//            是否同列判定
            if(cell.contains(luck)){
                continue;
            }
//            列比较
            for(j = 0; j < matrix.length; j++){
//                同行跳过
                if(j == i){
                    continue;
                }
                if(matrix[i][luck] < matrix[j][luck]){
                    break;
                }
            }
            if(j == matrix.length){
                result.add(matrix[i][luck]);
                cell.add(luck);
            }
        }
        return result;
    }
}
