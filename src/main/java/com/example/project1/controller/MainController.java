package com.example.project1.controller;

import com.example.project1.tours.Tour;
import com.example.project1.tours.TourDetails;
import com.example.project1.user.Usr;
import com.example.project1.user.repos.TourDetailsRepository;
import com.example.project1.user.repos.TourRepository;
import com.example.project1.user.repos.UserRepository;
import com.example.project1.user.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    TourService tourService;

    @GetMapping("main")
    public String main(Model model,@AuthenticationPrincipal Usr usr){
        List<Tour> tours=tourRepo.findAll();
        for(int i=0;i<tours.size();i++){
            tours.get(i).setActive();
        }
        tourService.DeleteExpiredTour(tours);
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
    
    @GetMapping("tourpage/{tourid}")
    public String tourDetails(Model model, @PathVariable(value="tourid") Long tourid,@AuthenticationPrincipal Usr usr){
        model.addAttribute("usr",usr);
        Tour selTour=tourRepo.findTourByTourid(tourid);
        TourDetails tourDetails=tourDetailsRepository.findTourDetailsByTour(selTour);
        model.addAttribute("tours",selTour);
        model.addAttribute("tourdet",tourDetails);
       return "tourpage";
    }

    @PostMapping("/booktour/{tourid}")
    public String bookingTour(HttpServletRequest request, @AuthenticationPrincipal Usr usr, Model model,
                              @PathVariable(value = "tourid") Long tourid
                               ){
        String date=request.getParameter("tourdate");
        Integer numpeople=Integer.parseInt(request.getParameter("numpeople"));
        Tour selTour=tourRepo.findTourByTourid(tourid);
        TourDetails selTourDet=tourDetailsRepository.findTourDetailsByTour(selTour);
        if(numpeople==selTourDet.getNumberofpeople()){
            selTour.setnonActive();
        }
        if(numpeople>selTourDet.getNumberofpeople()){
            model.addAttribute("numpeoplemax",true);
            return "confirmBooking";
        }
        selTour.setUsr(usr);
        tourRepo.save(selTour);
        model.addAttribute("succesfullyadded",true);
        model.addAttribute("usr",usr);
        return "confirmBooking";
    }
}
