package easy;

/**
 * 2023/7/22
 *
 * @author x.z
 */
public class Solution860 {
    public boolean lemonadeChange(int[] bills) {
        int b5, b10, b20;
        b5 = b10 = b20 = 0;
        for (int bill : bills) {
            if(bill == 5){
                b5 ++;
            }else if(bill == 10){
                if(b5 > 0){
                    b10 ++;
                    b5 --;
                }else{
                    return false;
                }
            }else if(bill == 20){
                if(b10 > 0 && b5 > 0){
                    b20++;
                    b10--;
                    b5--;
                }else if(b5 >= 3){
                    b5 -= 3;
                    b20 ++;
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}
