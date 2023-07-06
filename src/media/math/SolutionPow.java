package media.math;

public class SolutionPow {

    public static void  main(String[] args){
        SolutionPow temp = new SolutionPow();
        System.out.println(temp.myPowEm(8.84372, -5));
    }

    /**
     * 第一次修正，未考虑到 1 的 n 次方始终是 1
     * 测试用例几乎全过，剩余最后几个边界问题未处理，最笨的办法
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if(0 == x || 1 == x){
            return x;
        }
        if(0 == n){
            return 1;
        }
        boolean flag = n > 0;
        n = Math.abs(n);
        double temp = x;
        for (int i = 1; i < n; i++){
            x *= temp;
        }
        return flag ? x : 1 / x;
    }

    /**
     * 参考例子，采用递归，可改为迭代
     * @param x
     * @param n
     * @return
     */
    public double myPowEm(double x, int n) {
        if(n == 0) {
            return 1;
        }
        double ans = myPowEm(x,n /2);
        ans = ans * ans;
        if((n & 1) == 1){
            ans *= n>0? x:1/x;
        }
        return ans;
    }

    /**
     * err，测试用例只通过了240个
     * @param x
     * @param n
     * @return 
     */
    public double myPowExch(double x, int n) {
        if(n == 0) {
            return 1;
        }
        boolean flag = n > 0;
        double ans = flag ? x : 1 / x;
        n = n > 0 ? n : -n;
        for (; n > 1; n /= 2){
            ans *= ans;
            if(n % 2 != 0){
                ans *= flag ? x : 1 / x;
            }
        }
        return ans;
    }
}
