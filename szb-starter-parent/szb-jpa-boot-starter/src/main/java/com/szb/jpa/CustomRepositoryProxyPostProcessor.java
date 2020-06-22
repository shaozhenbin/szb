package com.szb.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;
import org.springframework.util.StringUtils;

/**
 * @ClassName CustomRepositoryProxyPostProcessor
 * @Description TODO
 * @Author szb
 * @Date 2020/2/15 23:11
 * @Version 1.0
 **/
@Slf4j
public class CustomRepositoryProxyPostProcessor implements RepositoryProxyPostProcessor{

    @Override
    public void postProcess(ProxyFactory proxyFactory, RepositoryInformation repositoryInformation) {
        log.info("Domain class ---------> {}", repositoryInformation.getDomainType().getSimpleName());
        String domainTypeName;

        if("BaseEntity".equals(repositoryInformation.getDomainType().getSuperclass().getSimpleName())) {
            domainTypeName = StringUtils.uncapitalize(repositoryInformation.getDomainType()
                    .getSimpleName());
            proxyFactory.addAdvice(new CustomAdvice(domainTypeName));
        }

    }


//    static enum BeforeAdvisor implements MethodBeforeAdvice {
//        instance;
//        @Override
//        public void before(Method method, Object[] args, Object target) throws Throwable {
//            System.out.println("additional concern before actual logic");
//            System.out.println("method info:" + method.getName() + " " + method.getModifiers());
//            System.out.println("argument info:");
//            for (Object arg : args)
//                System.out.println(arg);
//            System.out.println("target Object:" + target);
//            System.out.println("target object class name: " + target.getClass().getName());
//            SimpleJpaRepository simpleJpaRepository  = (SimpleJpaRepository)target;
//        }
//    }
}
