package com.bakatkin.SpringBootCRUD.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){
        return "login";
    }

    @GetMapping(value = "/login-error")
    public ModelAndView loginError() {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginError", true);
        return modelAndView;
    }
}
