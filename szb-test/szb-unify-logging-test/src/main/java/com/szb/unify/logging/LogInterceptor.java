package com.szb.unify.logging;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @ClassName LogInterceptor
 * @Description TODO
 * @Author szb
 * @Date 2019/6/30 22:25
 * @Version 1.0
 **/
@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private static final String SESSION_KEY = "requestId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.debug("-------preHandle---------");
        StringBuilder sb = new StringBuilder();
        String url = sb.append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getServletPath()).toString();
        // 设置SessionId
        String token = UUID.randomUUID().toString().replace("-", "");
        log.debug("token------------{}", url);
        MDC.put(SESSION_KEY, url + "-" + token);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
        log.debug("-------postHandle---------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        log.debug("-------afterCompletion---------");
        log.debug("MDC --------> {}", MDC.get(SESSION_KEY));
        // 删除SessionId
        MDC.remove(SESSION_KEY);
    }

}
