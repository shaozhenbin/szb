package com.szb.aop.cache;

import com.szb.aop.executetime.ExecuteTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

/**
 * @ClassName CacheExceptionAspect
 * @Description TODO
 * @Author szb
 * @Date 2020/2/6 18:42
 * @Version 1.0
 **/
@Aspect
@Order(2)
@Component
public class CacheExceptionAspect {

    private final Logger logger = LoggerFactory.getLogger(CacheExceptionAspect.class);

    @Pointcut(value = "execution(* com.szb.*.cache.manager.impl.*CacheManagerImpl.*(..))")
    public void cacheAspect() {
    }

    @Around(value = "cacheAspect()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        Signature signature = proceedingJoinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            //有CacheException注解需要抛出异常
            if(method.isAnnotationPresent(CacheException.class)) {
                throw throwable;
            } else {
                logger.error(throwable.getMessage());
            }
        }
        return result;
    }
}
