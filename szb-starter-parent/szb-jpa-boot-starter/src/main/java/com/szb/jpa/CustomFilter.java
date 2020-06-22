package com.szb.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Ops;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.Expressions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.util.StopWatch;

/**
 * @ClassName CustomFilter
 * @Description TODO
 * @Author szb
 * @Date 2020/2/15 23:08
 * @Version 1.0
 **/
@Slf4j
public abstract class CustomFilter {

    public static Object doFilter(ReflectiveMethodInvocation reflectiveMethodInvocation) throws Throwable {
        log.info("将要执行方法： {}", reflectiveMethodInvocation.getMethod().getName());
        log.info("参数: {}", reflectiveMethodInvocation.getArguments());
        //查询方法find开头进行数据权限过滤
        if(reflectiveMethodInvocation.getMethod().getName().startsWith("find")) {
            Class<?>[] classType = reflectiveMethodInvocation.getMethod().getParameterTypes();//截取参数值
            Object[] args = reflectiveMethodInvocation.getArguments();
            Integer paramNumber = classType.length;
            for (int i = 0; i < paramNumber; i++) {
                if (classType[i] == Predicate.class) {
                    log.info("args ---------------> {}",args[i]);
                    Predicate predicate = (Predicate) args[i];
                    BooleanBuilder booleanBuilder = new BooleanBuilder();
                    booleanBuilder.and(predicate);
                    //could be constructed like this if Q-types wouldn't be available
                    Path<BaseEntity> baseEntity;
                    String queryEntityName = (String)reflectiveMethodInvocation.getUserAttribute(CustomAdvice.QUERY_ENTITY);
                    baseEntity = Expressions.path(BaseEntity.class, queryEntityName);
                    Path<String> baseEntityGroupBy = Expressions.path(String.class, baseEntity, "groupBy");
                    Expression<String> constant = Expressions.constant("A/B");
                    booleanBuilder.and(Expressions.predicate(Ops.STRING_CONTAINS, baseEntityGroupBy, constant));
                    //java反射机制，动态设置值
                    args[i] = booleanBuilder;
                    log.info("args changed---------------> {}",args[i]);
                }
            }
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object obj = reflectiveMethodInvocation.proceed();
        stopWatch.stop();
        log.info("执行时间： {}ms", stopWatch.getTotalTimeMillis());
        return obj;
    }
}
