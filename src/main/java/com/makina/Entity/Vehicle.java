package com.makina.Entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;


import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<Rental> rentals = new ArrayList<>();

    private String brand;
    private String model;
    private Integer year;
    private Double dailyRate;

    @Enumerated(EnumType.STRING)
    private Status status;



    public Vehicle() {
    }

    public Vehicle(String brand, String model, Integer year, Double dailyRate, Status status) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.dailyRate = dailyRate;
        this.status = status;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(Double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", rentals=" + rentals +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", dailyRate=" + dailyRate +
                ", status=" + status +
                '}';
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }


}

