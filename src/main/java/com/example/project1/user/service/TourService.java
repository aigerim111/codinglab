package com.example.project1.user.service;

import com.example.project1.tours.Tour;
import com.example.project1.user.repos.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void DeleteExpiredTour(List<Tour> tours){
        for(int i=0;i<tours.size();i++){
            if(!tours.get(i).isActive()){
                tourRepo.delete(tours.get(i));
            }
        }
    }

}
