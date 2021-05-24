package com.example.project1.controller;

import com.example.project1.tours.Tour;
import com.example.project1.user.UserDetails;
import com.example.project1.user.Usr;
import com.example.project1.user.repos.TourRepository;
import com.example.project1.user.repos.UserDetailsRepository;
import com.example.project1.user.repos.UserRepository;
import com.example.project1.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

   @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private TourRepository tourRepository;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("user/{username}")
    public String userInfoPage(@PathVariable String username, Model model, @AuthenticationPrincipal Usr usr,
                             UserDetails userDetails) {
        model.addAttribute("username",username);
        userDetails=userDetailsRepository.findUserDetailsByUsr(usr);
        model.addAttribute("usrdet",userDetails);
        UserDetails userDetails1=new UserDetails();
        model.addAttribute("usrdet1",userDetails1);
        model.addAttribute("newusername",usr.getUsername());
        model.addAttribute("password",usr.getPassword());
        model.addAttribute("newpassword",usr.getPassword());
        List<Tour> tours=tourRepository.findTourByUsr(usr);
        model.addAttribute("tours",tours);
        return "userpage";
    }

    @PostMapping("addinfo")
    public ModelAndView addUserDetails(@AuthenticationPrincipal Usr usr,
                                 @ModelAttribute("usrdet1") @Valid UserDetails userDetails,BindingResult bindingResult) {
        ModelAndView mav=new ModelAndView();
        mav.setViewName("redirect:/user/"+usr.getUsername());
        if(bindingResult.hasErrors()){
            return mav;
        }
        userService.addPersonalInfo(usr.getId(),userDetails);
        return mav;
    }

    @PostMapping("changeusername")
    public ModelAndView changeUsername(@AuthenticationPrincipal Usr usr,
                                       @ModelAttribute("newusername") @Valid String newusername,
                                       Model model) {
        ModelAndView mav=new ModelAndView();
        mav.setViewName("redirect:/user/"+usr.getUsername());
        if(userRepo.findByUsername(newusername)!=null){
            model.addAttribute("usernameerror",true);
            return mav;
        }
        userService.updateUsername(newusername,usr.getId());
        mav.setViewName("redirect:/user/"+newusername);
        return mav;
    }

    @PostMapping("changepassword")
    public ModelAndView changePassword(@AuthenticationPrincipal Usr usr, HttpServletRequest request,
                                       RedirectAttributes redirectAttributes) {
        ModelAndView mav=new ModelAndView();
        mav.setViewName("redirect:/user/"+usr.getUsername());
        String newpassword=request.getParameter("newpassword");
        String oldpassword=request.getParameter("password");
        if(newpassword.length()<8){
            redirectAttributes.addFlashAttribute("passwordlength",true);
            return mav;
        }
        if(!userService.updatePassword(oldpassword,newpassword,usr)){
            redirectAttributes.addAttribute("passworderr",true);
            return mav;
        }
        return mav;
    }
    
    


}
