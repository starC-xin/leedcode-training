package easy;

/**
 * 2023/6/27
 *
 * @author x.z
 */
public class Solution415 {
    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int cache = 0;
        int i = 0;
        for (; i < num1.length() && i < num2.length(); i++) {
            int n1 = num1.charAt(num1.length() - 1 - i) - '0';
            int n2 = num2.charAt(num2.length() - 1 - i) - '0';
            int sum = n1 + n2 + cache;
            cache = 0;
            if(sum < 10){
                sb.append(sum);
            }else{
                cache = 1;
                sb.append(sum % 10);
            }
        }
        while(i < num1.length()){
            int n1 = num1.charAt(num1.length() - 1 - i) - '0';
            int sum = n1 + cache;
            cache = 0;
            if(sum < 10){
                sb.append(sum);
            }else{
                cache = 1;
                sb.append(sum % 10);
            }
            i ++;
        }
        while(i < num2.length()){
            int n1 = num2.charAt(num2.length() - 1 - i) - '0';
            int sum = n1 + cache;
            cache = 0;
            if(sum < 10){
                sb.append(sum);
            }else{
                cache = 1;
                sb.append(sum % 10);
            }
            i ++;
        }
        if(cache > 0){
            sb.append(cache);
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        final Solution415 demo = new Solution415();

        System.out.println(demo.addStrings("9", "99"));
    }
}
