package com.ninggc.controller;

import com.ninggc.util.IGson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController implements IGson {
    @ResponseBody
    @RequestMapping("/test")
    public String test() {
        return gson.toJson("test");
    }
}
