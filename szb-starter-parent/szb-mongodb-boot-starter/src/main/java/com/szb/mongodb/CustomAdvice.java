package com.szb.mongodb;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ReflectiveMethodInvocation;

/**
 * @ClassName CustomAdvice
 * @Description TODO
 * @Author szb
 * @Date 2020/2/18 15:58
 * @Version 1.0
 **/
@Slf4j
public class CustomAdvice implements MethodInterceptor {

    private final String domainTypeName;
    public static final String QUERY_DOCUMENT = "queryEntity";

    public CustomAdvice(String domainTypeName) {
        this.domainTypeName = domainTypeName;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        ReflectiveMethodInvocation reflectiveMethodInvocation = (ReflectiveMethodInvocation)methodInvocation;
        reflectiveMethodInvocation.setUserAttribute(QUERY_DOCUMENT, domainTypeName);
        return CustomFilter.doFilter(reflectiveMethodInvocation);
    }


}
