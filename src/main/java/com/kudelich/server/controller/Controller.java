package com.kudelich.server.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
@RequestMapping("/server")
public class Controller {
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public String getMessage(ModelMap modelMap){
        return "My first server message";
    }
}
