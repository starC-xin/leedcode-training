package easy;

/**
 * 2023/7/5
 *
 * @author x.z
 */
public class Solution2600 {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if(k <= numOnes){
            return k;
        }
        k -= numOnes;
        if(k <= numZeros){
            return numOnes;
        }
        k -= numZeros;
        return numOnes - k;
    }
}
