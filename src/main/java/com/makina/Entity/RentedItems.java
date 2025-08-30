package com.makina.Entity;

import jakarta.persistence.*;

@Entity
@Table(name="rentedItems")
public class RentedItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;


    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;



    public RentedItems() {
    }

    public RentedItems(Long id, Vehicle vehicle, Rental rental) {
        this.id = id;
        this.vehicle = vehicle;
        this.rental = rental;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }


    @Override
    public String toString() {
        return "RentedItems{" +
                "id=" + id +
                ", vehicle=" + vehicle +
                ", rental=" + rental +
                '}';
    }
}
