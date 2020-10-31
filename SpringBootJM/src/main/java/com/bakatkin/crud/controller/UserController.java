package com.bakatkin.crud.controller;

import com.bakatkin.crud.model.User;
import com.bakatkin.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public String printWelcome(ModelMap model, Principal principal) {
        User user = userService.getByName(principal.getName());
        model.addAttribute("userName", user);
        model.addAttribute("userList", userService.allUsers());
        model.addAttribute("allRoles", userService.getAllRoles());
        return "admin/index";
    }

    @RequestMapping(value = "/admin/new")
    public String newUser(ModelMap model, Principal principal){
        User currentUser = userService.getByName(principal.getName());
        model.addAttribute("userName", currentUser);
        model.addAttribute("roles", userService.getAllRoles());
        User user = new User();
        model.addAttribute("user", user);
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

    @RequestMapping(value = "/admin/edit/{id}")
    public String edit (@PathVariable("id") long id, ModelMap model, Principal principal){
        User currentUser = userService.getByName(principal.getName());
        User user = userService.getById(id);
        model.addAttribute("userName", currentUser);
        model.addAttribute("user", user);
        return "admin/edit";
    }

    @PostMapping(value = "/admin/editUser/{id}")
    public String editUser (@PathVariable("id") long id, @ModelAttribute("user") User user){
        userService.changeUser(id, user.getName(), user.getSurname(), user.getAddress(), user.getRoles());
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