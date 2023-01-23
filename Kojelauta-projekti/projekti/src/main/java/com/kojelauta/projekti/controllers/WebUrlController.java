package com.kojelauta.projekti.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebUrlController {
    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping("/about")
    public ModelAndView about() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("about");
        return modelAndView;
    }

    @RequestMapping("/mirror")
    public ModelAndView mirror() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index-mirror");
        return modelAndView;
    }

    @RequestMapping("/user")
    public ModelAndView user() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index-user");
        return modelAndView;
    }

    @RequestMapping("/vertical")
    public ModelAndView vertical() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index-vertical");
        return modelAndView;
    }
}
