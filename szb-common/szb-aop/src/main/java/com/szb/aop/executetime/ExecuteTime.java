package com.szb.aop.executetime;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface ExecuteTime {

    String description() default "";
}
