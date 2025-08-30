package com.makina.Entity;

import jakarta.persistence.*;

@Entity
@Table (name = "inventori")
public class Inventori {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    public Inventori( Integer quantity) {

        this.quantity = quantity;

    }



    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        if (this.quantity >= amount) {
            this.quantity -= amount;
        } else {
            System.out.println("Nuk ka mjaftueshem makina në inventar!");
        }
    }

    public Inventori() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }





    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Inventori{" +
                "id=" + id +
                ", vehicle="  +
                ", quanity=" + quantity +
                '}';
    }
}
