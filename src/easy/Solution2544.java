package easy;

/**
 * 2023/7/12
 *
 * @author x.z
 */
public class Solution2544 {
    /**
     * 水题罢了
     */
    public int alternateDigitSum(int n) {
        boolean flag = true;
        int res = 0;
        for (char c : String.valueOf(n).toCharArray()) {
            res += flag ? c - '0' : (c - '0') * -1;
            flag = !flag;
        }
        return res;
    }
}
