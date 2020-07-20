package com.copysun.functioninterface.demo;

/**供给型接口函数
 * @author copysun
 */
@FunctionalInterface
public interface GongGiFunction<T> {

    T produce();
}
