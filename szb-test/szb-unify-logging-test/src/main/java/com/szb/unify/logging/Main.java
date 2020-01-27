package com.szb.unify.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.UUID;

/**
 * @ClassName Main
 * @Description TODO
 * @Author szb
 * @Date 2020/1/27 20:01
 * @Version 1.0
 **/
public class Main {

    private static final String KEY = "requestId";
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        // 入口传入请求ID
        MDC.put(KEY, UUID.randomUUID().toString());

        // 打印日志
        logger.debug("log in main thread 1");
        logger.debug("log in main thread 2");
        logger.debug("log in main thread 3");

        // 出口移除请求ID
        MDC.remove(KEY);
    }

}
