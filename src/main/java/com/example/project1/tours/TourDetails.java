package com.example.project1.tours;


import javax.persistence.*;

@Entity
public class TourDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long tourDetId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer numberofpeople;

    @OneToOne
    @JoinColumn(name="tourid")
    private Tour tour;

    public Long getTourDetIid() {
        return tourDetId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberofpeople() {
        return numberofpeople;
    }

    public void setNumberofpeople(Integer numberofpeople) {
        this.numberofpeople = numberofpeople;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
