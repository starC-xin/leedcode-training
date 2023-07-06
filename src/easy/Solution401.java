package easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 2023/6/26
 *
 * @author x.z
 */
public class Solution401 {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> res = new ArrayList<>();
        if(turnedOn > 8){
            return res;
        }
        for (int i = 0; i < 12; i ++){
            final int iCount = Integer.bitCount(i);
            for (int j = 0; j < 60; j++) {
                final int jCount = Integer.bitCount(j);
                if(iCount + jCount == turnedOn){
                    res.add(i + ":" + (j < 10 ? "0" + j : j));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        final Solution401 demo = new Solution401();

        System.out.println(demo.readBinaryWatch(2));
    }
}
