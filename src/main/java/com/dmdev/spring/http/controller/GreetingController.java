package com.dmdev.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {

    public ModelAndView hello(ModelAndView modelAndView){
        modelAndView.setViewName("greeting/hello");
        return modelAndView;
    }

    public ModelAndView bye(ModelAndView modelAndView){
        modelAndView.setViewName("greeting/bye");
        return modelAndView;
    }
}
