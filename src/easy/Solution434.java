package easy;

/**
 * 2023/6/28
 *
 * @author x.z
 */
public class Solution434 {
    public int countSegments(String s) {
        final String trim = s.trim();
        if("".equals(trim)){
            return 0;
        }
        return trim.split("\\s+").length;
    }
}
