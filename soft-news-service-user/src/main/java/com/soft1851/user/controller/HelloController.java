package com.soft1851.user.controller;

import com.soft1851.api.controller.user.HelloControllerApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName $(Name)
 * @Description HelloController
 * @Author Qin jian
 * @Date 2020/11/13
 * @Version 1.0
 **/
@RestController
@Slf4j
public class HelloController implements HelloControllerApi {
    @Override
    public Object hello() {
        log.info("info: hello");
        log.warn("warn: hello");
        log.error("error: hello");
        return "hello";
    }
}
