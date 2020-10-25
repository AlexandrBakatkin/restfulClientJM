package com.bakatkin.crud.controller;

import com.bakatkin.crud.model.User;
import com.bakatkin.crud.service.UserServiceJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {
    UserServiceJpaImpl userService;

    @Autowired
    public void setUserService(UserServiceJpaImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model) {
        model.addAttribute("userList", userService.allUsers());
        return "index";
    }

    @RequestMapping(value = "/admin/new")
    public String newUser(ModelMap model){
        User user = new User();
        model.put("user", user);
        return "admin/new";
    }

    @PostMapping(value = "/admin/save")
    public String save(@ModelAttribute("user") User user){
        userService.add(user);
        return "redirect:/";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String delete(@PathVariable("id") long id){
        userService.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/edit/{id}")
    public String edit (@PathVariable("id") long id, ModelMap model){
        User user = userService.getById(id);
        model.put("user", user);
        return "edit";
    }

    @PostMapping(value = "/editUser/{id}")
    public String editUser (@PathVariable("id") long id, @ModelAttribute("user") User user){
        userService.changeUser(id, user.getName(), user.getSurname(), user.getAddress());
        return "redirect:/";
    }

    @RequestMapping(value = "/user/")
    public String user (Principal principal, ModelMap modelMap){
        User user = userService.getByName(principal.getName());
        modelMap.addAttribute("userInfo", user);
        return "user";
    }

    @RequestMapping(value = "/admin/")
    public String admin(){
        return "redirect:/";
    }
}
