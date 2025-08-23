package com.makina.Entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "rentals")
public class Rental {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startDate;
    private Date endDate;


    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;



    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    public Rental(){};

    public Rental(Customer customer, Vehicle vehicle, Date startDate, Date endDate) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }



    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }


    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", vehicle=" + vehicle +
                ", customer=" + customer +
                ", payments=" + payments +
                '}';
    }
}
