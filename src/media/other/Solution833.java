package media.other;

import utils.Common;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2023/8/15
 *
 * @author x.z
 */
public class Solution833 {
    /**
     * 稍稍有些难度
     * 主要体现在原字符串不可变动和一个索引数组辅助处理，以及一个越界问题
     * 原字符串不可变动：因为 indices 中的数据始终为原始字符串的下标，如果变动原始字符串则需要额外的映射处理，很麻烦
     * 索引数组：为了方便处理，即字符串从左到右执行替换 / 从右到左执行替换，索引数组是最优选择，可以同时兼顾后面的 source 和 target
     * 越界问题：主要体现在替换时的 substring 子字符串的切割问题
     */
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
        StringBuilder sb = new StringBuilder();
        Integer[] iArr = new Integer[indices.length];
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = i;
        }
        Arrays.sort(iArr, Comparator.comparingInt(o -> indices[o]));

        int last = 0;
        for (int i = 0; i < indices.length; i++) {
            int index = indices[iArr[i]];
            if(last != index){
                final String substring = s.substring(last, index);
                sb.append(substring);
            }
            if (index + sources[iArr[i]].length() <= s.length() && s.substring(index, index + sources[iArr[i]].length()).equals(sources[iArr[i]])) {
                sb.append(targets[iArr[i]]);
                last = index + sources[iArr[i]].length();
            }else{
                last = index;
            }
        }
        if(last < s.length()){
            sb.append(s.substring(last, s.length()));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        final Solution833 demo = new Solution833();
        System.out.println(demo.findReplaceString(
                "vmokgggqzp",
                Common.string2IntArr("[3,5,1]"),
                Common.string2StringArr("[\"kg\",\"ggq\",\"mo\"]"),
                Common.string2StringArr("[\"s\",\"so\",\"bfr\"]")
        ));
    }
}
