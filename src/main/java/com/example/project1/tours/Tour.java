package com.example.project1.tours;

import com.example.project1.user.Usr;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Date;
import java.util.List;

import com.example.project1.user.Usr;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name="tourid")
    private Long tourid;

    @Column(nullable = false,name = "tourname")
    private String tourname;

    @Column(nullable = false,name = "address")
    private String address;

    @Column(nullable = false,name = "tourdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tourdate;

    @Column(nullable = false,name = "price")
    private Integer price;

    @ManyToMany
    @JoinColumn(
            nullable = false,
            name = "id"
    )
    private List<Usr> usr;

    @Column(name = "image")
    private String image;

    @Column (name="tourduration")
    private String tourduration;

    public Tour(@NotNull String tour_name,
                @NotNull String address,
                @NotNull Date tour_date,
                @NotNull Integer price,
                Usr usr) {
        this.tourname = tour_name;
        this.address = address;
        this.tourdate = tour_date;
        this.price = price;
        this.usr = (List<Usr>) usr;
        this.image=image;
        this.tourduration=tourduration;
    }

    public void setTour(Tour tour) {
        this.tourid=tour.tourid;
        this.tourname =tour.getTourname();
        this.address = tour.getAddress();
        this.tourdate = tour.getTourdate();
        this.price = tour.getPrice();
        this.image=tour.getImage();
        this.tourduration=tour.getTourduration();
    }

    public Tour(){

    }

    public String getTourname() {
        return tourname;
    }

    public String getAddress() {
        return address;
    }

    public Date getTourdate() {
        return tourdate;
    }

    public Integer getPrice() {
        return price;
    }

    public Long getTourid() {
        return tourid;
    }

    public void setTourname(String tourname) {
        this.tourname = tourname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTourdate(Date tourdate) {
        this.tourdate = tourdate;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Usr> getUsr() {
        return usr;
    }

    public void setUsr(List<Usr> usr) {
        this.usr = usr;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTourduration() {
        return tourduration;
    }

    public void setTourduration(String tourduration) {
        this.tourduration = tourduration;
    }
}
