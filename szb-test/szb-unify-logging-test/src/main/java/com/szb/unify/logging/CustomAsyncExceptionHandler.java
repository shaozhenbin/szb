package com.szb.unify.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;

/**
 * @ClassName CustomAsyncExceptionHandler
 * @Description TODO
 * @Author szb
 * @Date 2020/1/27 20:47
 * @Version 1.0
 **/
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAsyncExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
        logger.debug("Exception message - " + throwable.getMessage());
        logger.debug("Method name - " + method.getName());
        for (Object param : objects) {
            logger.debug("Parameter value - " + param);
        }
    }
}
