package day;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2021/10/28
 *
 * 给定正整数 N ，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 *
 * 如果我们可以通过上述方式得到 2 的幂，返回 true；否则，返回 false。
 *
 * 示例 1：
 * 输入：1
 * 输出：true
 *
 * 示例 2：
 * 输入：10
 * 输出：false
 *
 * 示例 3：
 * 输入：16
 * 输出：true
 *
 * 示例 4：
 * 输入：24
 * 输出：false
 *
 * 示例 5：
 * 输入：46
 * 输出：true
 *  
 * 提示：
 * 1 <= N <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reordered-power-of-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author x.c
 */
public class Solution869 {

    public static void main(String[] args){
        int num = 2;
        int result = 1;
        int condition = (int)Math.pow(10, 9);
        while(result <= condition){
            System.out.println(result);
            result *= num;
        }
    }

    public boolean reorderedPowerOf2(int n) {
        List<Integer> list = init();
        if(list.contains(n)){
            return true;
        }
        String num = String.valueOf(n);
        int[] arr = new int[10];
        for(char ch : num.toCharArray()){
            arr[ch - '0'] ++;
        }
        for(Integer listNum : list){
            String strNum = String.valueOf(listNum);
            if(strNum.length() != num.length()){
                continue;
            }
            int[] listArr = Arrays.copyOf(arr, arr.length);
            for(char ch : strNum.toCharArray()){
                listArr[ch - '0'] --;
            }
            int i = 0;
            for(; i < listArr.length; i++){
                if(listArr[i] != 0){
                    break;
                }
            }
            if(i == listArr.length){
                return true;
            }
        }
        return false;
    }

    /**
     * 设置提前处理，备用
     */
    private List<Integer> init(){
        List<Integer> result = new ArrayList<>();
        int num = 2;
        int temp = 1;
        int condition = (int)Math.pow(10, 9);
        while(temp <= condition){
            result.add(temp);
            temp *= num;
        }
        return result;
    }
}
