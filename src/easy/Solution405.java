package easy;

/**
 * 2023/6/26
 *
 * @author x.z
 */
public class Solution405 {
    public String toHex(int num) {
        if(0 == num){
            return "0";
        }
        final StringBuilder sb = new StringBuilder();
        int count = 8;
        while(num != 0 && count-- > 0){
            int tmp = num & 15;
            num >>= 4;
            if(tmp < 10){
                sb.append(tmp);
            }else{
                sb.append((char)(tmp - 10 + 'a'));
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        final Solution405 demo = new Solution405();

        System.out.println(demo.toHex(-2));
    }
}
