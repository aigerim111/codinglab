package com.example.project1.controller;


import com.example.project1.tours.Tour;
import com.example.project1.user.Role;
import com.example.project1.user.repos.TourRepository;
import com.example.project1.user.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@Controller
public class AdminController {

    @Autowired
    TourRepository tourRepo;

    @Autowired
    TourService tourService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/newTour")
    public String newTour(Model model){
        Tour tour=new Tour();
        model.addAttribute("tour",tour);
        model.addAttribute("tours",tourRepo.findAll());
        return "adminspage";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public String saveTour(@ModelAttribute("tour") @Valid Tour tour){
        tourRepo.save(tour);
        return "redirect:/tours";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/delete")
    public String deleteTour(@ModelAttribute("tourid") Long Id){
        tourService.DeleteTour(Id);
        return "redirect:/tours";
    }

}
