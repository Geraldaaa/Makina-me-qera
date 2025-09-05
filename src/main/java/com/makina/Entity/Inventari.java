package com.makina.Entity;

import jakarta.persistence.*;

@Entity
@Table (name = "inventari")
public class Inventari {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    public Inventari( Integer quantity) {

        this.quantity = quantity;

    }



    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        if (this.quantity >= amount) {
            this.quantity -= amount;
        } else {
            System.out.println("Nuk ka mjaftueshem makina ne inventar");
        }
    }

    public Inventari() {
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
