package com.makina.Entity;

import jakarta.persistence.*;

import java.sql.Date;


@Entity
@Table(name = "payments")
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private Date paymentDate;

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;


     public Payment(){};

    public Payment(Rental rental,Double amount, Date paymentDate) {
        this.rental = rental;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
