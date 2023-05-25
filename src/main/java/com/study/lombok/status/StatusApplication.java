package com.study.lombok.status;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusApplication {

    @RequestMapping("/")
    public String Hello(){
        return "Hello Lombok";
    }
}
