package com.szb.aop;

import java.lang.annotation.*;

/**
 * @Title 自定义日志注解
 * @Author szb
 * @Description
 * @Date 12:05 2020/2/3
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
public @interface WebLog {

    /**
     * @Title 日志描述信息
     * @Author szb
     * @Description
     * ①：什么时候使用该注解，我们定义为运行时；
     * ②：注解用于什么地方，我们定义为作用于方法上；
     * ③：注解是否将包含在 JavaDoc 中；
     * ④：注解名为 WebLog;
     * ⑤：定义一个属性，默认为空字符串；
     * @Date 12:08 2020/2/3
     * @param
     * @return java.lang.String
     **/
    String description() default "";
}
