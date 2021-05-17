package com.example.project1.user.service;

import com.example.project1.tours.Tour;
import com.example.project1.user.repos.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class TourService {

    @Autowired
    TourRepository tourRepo;

    public Page<Tour> paginationSort(int page, int size, Sort sort){
        return tourRepo.findAll(PageRequest.of(page-1,size,sort));
    }

    public void DeleteTour(Long Id){
        Tour deleteTour=tourRepo.findTourByTourid(Id);
        tourRepo.delete(deleteTour);
    }

    public void UpdateTour(Tour tour){
//        Tour updateTour=tourRepo.findTourByTourid(Id);
//        updateTour.setTour(tour);
        tourRepo.save(tour);
    }

}
