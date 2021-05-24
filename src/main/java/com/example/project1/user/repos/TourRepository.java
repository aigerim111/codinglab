package com.example.project1.user.repos;

import com.example.project1.tours.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TourRepository extends JpaRepository<Tour,Long> {
    Page<Tour> findAll(Pageable pageable);
    Tour findTourByTourid(Long Id);
    List<Tour> findAll();
    List<Tour> findTourByUsr(Usr usr);

}
