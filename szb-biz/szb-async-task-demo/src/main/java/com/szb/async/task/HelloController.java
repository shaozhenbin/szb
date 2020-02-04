package com.szb.async.task;

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

    private final AsyncBusiness asyncBusiness;

    public HelloController(AsyncBusiness asyncBusiness) {
        this.asyncBusiness = asyncBusiness;
    }

    @GetMapping("/sayhello")
    public String sayHello() {
        logger.debug("-----------say Hello------------");
        asyncBusiness.asyncOne();
        asyncBusiness.asyncTwo();
        return "hi,spring";
    }

}
