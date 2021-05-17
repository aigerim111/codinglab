package com.example.project1.controller;

import com.example.project1.user.Usr;
import com.example.project1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public String userpage (@AuthenticationPrincipal Usr usr, Model model){
        String username=usr.getUsername();
        model.addAttribute("username",username);
        return "userpage";
    }

    @GetMapping("{username}")
    public String userInfoPage(@PathVariable String username,Model model){
        return "userPrPage";
    }


}
