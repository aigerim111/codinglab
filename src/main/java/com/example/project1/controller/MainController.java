package com.example.project1.controller;

import com.example.project1.tours.Tour;
import com.example.project1.user.Usr;
import com.example.project1.user.repos.TourRepository;
import com.example.project1.user.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    TourService tourService;

    @GetMapping("main")
    public String main(Model model,@AuthenticationPrincipal Usr usr){
        model.addAttribute("usr",usr);
        return "index";
    }

    @GetMapping("tours")
    String tourPage(Model model,@AuthenticationPrincipal Usr usr){
        model.addAttribute("usr",usr);
        return tours(model,1,"address",usr);
    }


    @GetMapping("tours/{page}")
    public String tours(Model model,
                        @PathVariable(value="page") int page,
                        @Param("option") String option,
                        @AuthenticationPrincipal Usr usr
                        ){
        Page<Tour> pages=tourService.paginationSort(page,6,Sort.by(option).ascending());
        List<Tour> tours=pages.getContent();
        model.addAttribute("usr",usr);
        model.addAttribute("tours",tours);
        model.addAttribute("pages",pages);
        model.addAttribute("current",page);
        model.addAttribute("option",option);
        return "tours";
    }
}