package utils;

import interf.TwoParamFunc;
import struct.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 2022/3/2
 * 力扣工具类
 * 输入参数转换
 * @author ZhaoXin
 */
public class Common {

    /**
     * 简单统计耗时
     */
    public static void run(Consumer<Object> accept, Consumer<Object> after){
        long start = System.currentTimeMillis();
        accept.accept(null);
        long cost = System.currentTimeMillis() - start;
        if(null != after){
            after.accept(null);
        }
        System.out.println("耗时：" + cost + "ms");
    }

    /**
     * 添加一个后置流程
     */
    public static void run(Consumer<Object> accept){
        run(accept, null);
    }

    /**
     * 统计耗时，比ms的反应更精确一点
     */
    public static <T1, T2> void run(TwoParamFunc<T1, T2> func, T1 param1, T2 param2){
        final long begin = System.nanoTime();
        func.accept(param1, param2);
        System.out.println("耗时：" + (System.nanoTime() - begin) / Math.pow(10, 6) + "ms");
    }

    /**
     * 字符串转为 int[][]
     */
    public static int[][] string2IntIntArr(String str){
        final String[] split = processStr(str).split("],\\[");
        List<int[]> tmpList = new ArrayList<>();
        for (String item : split) {
            final String[] tmp = item.split(",");
            int[] tmpArr = new int[tmp.length];
            for (int i = 0; i < tmp.length; i++) {
                tmpArr[i] = Integer.parseInt(tmp[i]);
            }
            tmpList.add(tmpArr);
        }
        return tmpList.toArray(new int[0][0]);
    }

    /**
     * 字符串转 List-List
     */
    public static List<List<Integer>> string2IntIntList(String str){
        final String[] split = processStr(str).split("],\\[");
        List<List<Integer>> res = new ArrayList<>();
        for (String item : split) {
            final String[] tmp = item.split(",");
            List<Integer> list = new ArrayList<>();
            for (String s : tmp) {
                list.add(Integer.parseInt(s));
            }
            res.add(list);
        }
        return res;
    }

    /**
     * 字符串转 int[]
     */
    public static int[] string2IntArr(String str){
        final String[] split = processStr(str).split(",");
        int[] result = new int[split.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = Integer.parseInt(split[i]);
        }
        return result;
    }

    /**
     * 字符串预处理
     */
    private static String processStr(String str){
        str = str.trim();
        while(str.startsWith("[")){
            str = str.substring(1);
        }
        while (str.endsWith("]")){
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    /**
     * 字符串转为字符串数组
     */
    public static String[] string2StringArr(String str){
        return processStr(str).replaceAll("\"", "").split(",");
    }

    /**
     * 字符串转链表
     */
    public static ListNode string2ListNode(String str){
        final int[] nodes = string2IntArr(str);
        final ListNode head = new ListNode(nodes[0]);
        ListNode pre = head;
        ListNode node;
        for (int i = 1; i < nodes.length; i++) {
            node = new ListNode(nodes[i]);
            pre.next = node;
            pre = node;
        }
        return head;
    }

    /**
     * 打印链表
     */
    public static void printListNode(ListNode head){
        while(head != null){
            System.out.print(head.val + ", ");
            head = head.next;
        }
    }
}
