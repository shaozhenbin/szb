package com.szb.logging;

import com.szb.utils.MDCUtil;
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
public class LogMdcInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LogMdcInterceptor.class);
    private static final String REQUEST_KEY = "requestId";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        logger.debug("-------preHandle---------");
        StringBuilder sb = new StringBuilder();
        //获取token
        String token = UUID.randomUUID().toString().replace("-", "");
        logger.debug("token------------{}", token);
        MDCUtil.put(REQUEST_KEY, token);
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
        logger.debug("MDC --------> {}", MDC.get(REQUEST_KEY));
        // 删除REQUESTID_KEY
        MDCUtil.remove(REQUEST_KEY);
    }

}
