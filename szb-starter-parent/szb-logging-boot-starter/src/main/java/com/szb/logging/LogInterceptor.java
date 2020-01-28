package com.szb.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class LogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);


    private static final String SESSION_KEY = "requestId";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.debug("-------preHandle---------");
        StringBuilder sb = new StringBuilder();
        String url = sb.append(request.getServerName()).append(":").append(request.getServerPort()).append(request.getServletPath()).toString();
        // 设置SessionId
        String token = UUID.randomUUID().toString().replace("-", "");
        logger.debug("token------------{}", url);
        MDCUtil.put(SESSION_KEY, url + "-" + token);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
        logger.debug("-------postHandle---------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        logger.debug("-------afterCompletion---------");
        logger.debug("MDC --------> {}", MDC.get(SESSION_KEY));
        // 删除SessionId
        MDCUtil.remove(SESSION_KEY);
    }

}
