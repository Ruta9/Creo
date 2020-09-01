package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClientForwardController {

    @RequestMapping(value = {"/{path:[^\\.]*}", "/**/{path:^(?!oauth|api|h2).*}/{path:[^\\.]*}"})
    public String redirect() {
        return "forward:/index.html";
    }

}
