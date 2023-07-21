package com.xingyuan.train.business.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xingyuan Huang
 * @since 2023/7/11 19:38
 */
@SpringBootApplication
@ComponentScan("com.xingyuan")
@MapperScan("com.xingyuan.train.*.mapper")
@EnableFeignClients("com.xingyuan.train.business.feign")
public class BusinessApplication {
    private static final Logger LOG = LoggerFactory.getLogger(BusinessApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BusinessApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！！");
        LOG.info("测试地址: \thttp://127.0.0.1:{}{}/hello", env.getProperty("server.port"), env.getProperty("server.servlet.context-path"));

        initFlowRules();
        LOG.info("已定义限流规则");
    }

    private static void initFlowRules(){
        List<FlowRule> rules= new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setRefResource("doConfirm");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(5);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
