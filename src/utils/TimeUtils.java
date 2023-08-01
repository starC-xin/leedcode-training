package utils;

/**
 * 2023/8/1
 * 简易的计时工具类
 * @author x.z
 */
public final class TimeUtils {
    static volatile long nano = 0;

    public static void begin(){
        nano = System.nanoTime();
    }

    public static void end(){
        end("");
    }

    public static void end(String str){
        long nano = TimeUtils.nano;
        System.out.println(str + ": " + (System.nanoTime() - nano) / Math.pow(10, 6) + " ms");
    }
}
