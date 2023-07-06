package interf;

/**
 * 2023/4/25
 *
 * @author x.z
 */
public interface TwoParamFunc<T1, T2> {
    /**
     * 接受两个参数的接口方法，无返回值
     */
    void accept(T1 param1, T2 param2);
}
