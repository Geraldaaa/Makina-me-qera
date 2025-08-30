package com.makina.Entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name = "vehicles")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    private List<RentedItems> RentedItems = new ArrayList<>();

    private String brand;
    private String model;

    @Column(unique=true)
    private String targa;
    private Integer year;
    private Double dailyRate;
    private Double price;

    @Enumerated(EnumType.STRING)
    private Status status;



    public Vehicle() {
    }

    public Vehicle(String brand, String model, String targa, Integer year, Double dailyRate,  Double price, Status status) {
        this.brand = brand;
        this.model = model;
        this.targa = targa;
        this.year = year;
        this.dailyRate = dailyRate;
        this.status = status;
        this.price = price;

    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", dailyRate=" + dailyRate +
                ", status=" + status +
                '}';
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
    }

    public List<RentedItems> getRentedItems() {
        return RentedItems;
    }

    public void setRentedItems(List<RentedItems> rentedItems) {
        RentedItems = rentedItems;
    }
}

