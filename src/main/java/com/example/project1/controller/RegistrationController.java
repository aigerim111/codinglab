package com.example.project1.controller;

import com.example.project1.user.Usr;
import com.example.project1.user.config.emailToken.ConfirmationToken;
import com.example.project1.user.config.emailToken.EmailToken;
import com.example.project1.user.repos.UserRepository;
import com.example.project1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class RegistrationController {

    @Autowired
    public UserService userService;

    @Autowired
    public ConfirmationToken confirmationToken;

    @Autowired
    public UserRepository userRepo;

    @GetMapping("login")
    public String log(){
        return "login";
    }

    @GetMapping("registration")
    public String addUser(WebRequest request, Model model) throws IllegalAccessException {
        Usr usr = new Usr();
        model.addAttribute("usr", usr);
        return "registration";
    }

    @PostMapping("registration")
    public String saveUser(@ModelAttribute("usr") @Valid Usr usr, BindingResult result, Model model) throws IllegalAccessException {
        if(!userService.addNewUser(usr)){
            model.addAttribute("check",true);
            return "registration";
        }

        return "redirect:/confirmpage";
    }

    @GetMapping("confirmpage")
    public String confirmPage(){
        return "confirmationPage";
    }

    @RequestMapping(value="confirmaccount", method= {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(Model model, @RequestParam("token")String emailToken){

        EmailToken token = confirmationToken.findByToken(emailToken);
        if(token.getExpiryDate().isBefore(LocalDateTime.now()) && token!=null){
            model.addAttribute("time",true);
            userService.deleteusr(token.getUsr());
            model.addAttribute("username",token.getUsr().getUsername());
            return "confirm";
        }
        else if(token != null)
        {
            Usr usr = userRepo.findByUsername(token.getUsr().getUsername());
            usr.setEnabled(true);
            userRepo.save(usr);
            return "redirect:/login";
        }
        else
        {
            model.addAttribute("message","Something wrong happened!");
            return "confirm";
        }
    }



}
