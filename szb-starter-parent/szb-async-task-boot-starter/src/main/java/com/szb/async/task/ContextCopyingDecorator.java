package com.szb.async.task;

import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Map;

/**
 * @ClassName ContextCopyingDecorator
 * @Description TODO
 * @Author szb
 * @Date 2020/1/27 20:38
 * @Version 1.0
 **/
public class ContextCopyingDecorator implements TaskDecorator {

    private Map<String, String> map;

    @Override
    public Runnable decorate(Runnable runnable) {
        // 保存当前线程的MDC值
        this.map = MDCUtil.getCopyOfContextMap();
        // 上下文线程间传递
        RequestAttributes context = RequestContextHolder.currentRequestAttributes();
        return () -> {
            try {
                RequestContextHolder.setRequestAttributes(context);
                // 传入已保存的MDC值
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    MDCUtil.put(entry.getKey(), entry.getValue());
                }
                // 装饰器模式，执行run方法
                runnable.run();
            } finally {
                // 移除已保存的MDC值
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    MDCUtil.remove(entry.getKey());
                }
                RequestContextHolder.resetRequestAttributes();
            }
        };
    }
}
