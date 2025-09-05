package com.makina;

import com.makina.Entity.*;
import com.makina.Repository.*;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // Repositories
        CustomerRepository customerRepo = new CustomerRepository();
        VehicleRepository vehicleRepo = new VehicleRepository();
        RentalRepository rentalRepo = new RentalRepository();
        PaymentRepository paymentRepo = new PaymentRepository();
        RentedItemsRepository rentedItemsRepo = new RentedItemsRepository();
        InventoriRepository inventariRepo = new InventoriRepository();

        //customer
        Customer customer = new Customer("Ana", "g", "ana.g@test.com");
        customerRepo.shtoCustomer(customer);
        System.out.println("Customer u shtua: " + customer.getFirstName());

        // vehicle ---
        Vehicle vehicle = new Vehicle("Toyota", "Corolla", "A123C", 2023, 50.0, 20000.0, Status.AVAILABLE);
        vehicleRepo.shtoVehicle(vehicle);
        System.out.println("Makina u shtua: " + vehicle.getBrand() + " " + vehicle.getModel());

        // inventari ---
        // Supozojmë që ID e inventarit i cili do perditesohet është 1
        inventariRepo.increaseQuantity(1, 1);
        System.out.println("Inventari u perditesua me +1");

        // rental ---
        Rental rental = new Rental(
                customer,
                Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(7)),
                StatusiQirasë.active
        );
        rentalRepo.shtoRental(rental);
        System.out.println("Rental u krijua per customer: " + customer.getFirstName());

        // Lidhja e vehicle me rental permes RentedItems ---
        RentedItems rentedItem = new RentedItems();
        rentedItem.setVehicle(vehicle);
        rentedItem.setRental(rental);

        rental.getRentedItems().add(rentedItem);
        vehicle.getRentedItems().add(rentedItem);

        rentedItemsRepo.shtoItemRepository(rentedItem);
        System.out.println("Makina u lidh me rental permes RentedItems");

        //payment  ---
        Payment payment = new Payment(
                rental,
                vehicle.getPrice(),
                Date.valueOf(LocalDate.now()),
                StatusiPageses.pending
        );
        paymentRepo.shtoPayment(payment);
        System.out.println("Payment u krijua dhe ruajtur: " + payment.getAmount());

        System.out.println("Procesi i plote i marrjes me qera te nje makine perfundoi me sukses");
    }
}
