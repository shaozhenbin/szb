package com.szb.unify.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassName AsyncBusiness
 * @Description TODO
 * @Author szb
 * @Date 2020/1/27 20:58
 * @Version 1.0
 **/
@Service
public class AsyncBusiness {

    private static final Logger logger = LoggerFactory.getLogger(AsyncBusiness.class);

    @Async
    public void asyncOne() {
        logger.debug("log in asyncOne thread");
    }

    @Async
    void asyncTwo() {
        logger.debug("log in asyncTwo thread");
    }
}
