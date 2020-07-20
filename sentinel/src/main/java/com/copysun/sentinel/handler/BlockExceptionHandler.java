package com.copysun.sentinel.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 自定义异常
 * @author copysun
 */
@Slf4j
@Component
public class BlockExceptionHandler {

    public static String block(String message, Exception e){
        log.warn(e.getMessage());
        return "限流或者被降级了 block!";
    }
}
