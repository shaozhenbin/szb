package com.szb.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author szb
 * @Date 2020/1/27 21:00
 * @Version 1.0
 **/
@RestController
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    /*
   1.异步线程
    */
    @GetMapping("/sayhello")
    public String sayHello() {
        logger.debug("-----------say Hello------------");
        return "hi,spring";
    }

}
