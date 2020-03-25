package com.szb.aop.cache;

import java.lang.annotation.*;

/**
 * @ClassName CacheException
 * @Description TODO
 * @Author szb
 * @Date 2020/3/24 22:25
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface CacheException {

    String description() default "";
}
