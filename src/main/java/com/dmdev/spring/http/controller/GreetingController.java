package com.dmdev.spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/v1")
public class GreetingController {

    @GetMapping("/hello/{id}")
    public ModelAndView hello(ModelAndView modelAndView, HttpServletRequest request,
                              @RequestParam Integer age,
                              @RequestHeader String accept,
                              @CookieValue("JSESSIONID") String jsessionId,
                              @PathVariable Integer id){
        modelAndView.setViewName("greeting/hello");
        return modelAndView;
    }

    @GetMapping("/bye")
    public ModelAndView bye(ModelAndView modelAndView){
        modelAndView.setViewName("greeting/bye");
        return modelAndView;
    }
}
