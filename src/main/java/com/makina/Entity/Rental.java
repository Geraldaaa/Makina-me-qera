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

    private  Date startDate;
    private  Date endDate;
    @Enumerated (EnumType.STRING)
    private StatusiQirasë statusiQirasë;


    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private List<RentedItems> RentedItems = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;



    @OneToMany(mappedBy = "rental", cascade = CascadeType.ALL)
    private List<Payment> payments = new ArrayList<>();

    //private Integer sasiaMakinave;

    public Rental(){};

    public Rental(Customer customer, Date startDate, Date endDate, StatusiQirasë statusiQirasë) {
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.statusiQirasë = statusiQirasë;

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

    public StatusiQirasë getStatusiQirasë() {
        return statusiQirasë;
    }

    public void setStatusiQirasë(StatusiQirasë statusiQirasë) {
        this.statusiQirasë = statusiQirasë;
    }

    public List<RentedItems> getRentedItems() {
        return RentedItems;
    }

    public void setRentedItems(List<RentedItems> rentedItems) {
        RentedItems = rentedItems;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", customer=" + customer +
                ", payments=" + payments +
                '}';
    }
}
