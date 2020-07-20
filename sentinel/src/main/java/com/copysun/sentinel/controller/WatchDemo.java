package com.copysun.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.copysun.sentinel.handler.BlockExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author copysun
 */
@RestController
public class WatchDemo {

    static {
        initFlowRules();
    }

    private static void initFlowRules(){
        List<FlowRule> rules=new ArrayList<>();
        rules.add(new FlowRule("test-sentinel-api")
                .setGrade(RuleConstant.FLOW_GRADE_QPS)
                .setCount(2)
        );
        FlowRuleManager.loadRules(rules);
    }

    @SentinelResource(value = "test-sentinel-api",blockHandler = "block",blockHandlerClass = {BlockExceptionHandler.class})
    @RequestMapping("/watch")
    public String watch(){
            System.out.println("进来了。。。。。。");
            return "进来了。。。。。。";
    }
}
