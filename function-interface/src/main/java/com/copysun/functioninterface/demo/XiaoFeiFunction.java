package com.copysun.functioninterface.demo;

/**消费型接口函数
 * @author copysun
 */
@FunctionalInterface
public interface XiaoFeiFunction<T> {
    void onlyUse(T t);
}
