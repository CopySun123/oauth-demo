package com.copysun.sentinel.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 异常回调
 * @author copysun
 */
@Component
@Slf4j
public class FallBackExceptionHandler {

    public static String fallback(String message,Exception ex){
        log.warn(ex.toString());
        return "限流或者被降级了 fallback!";
    }
}
