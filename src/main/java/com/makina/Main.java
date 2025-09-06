package com.makina;

import com.makina.Entity.*;
import com.makina.Repository.*;
import com.makina.Services.InventariService;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        CustomerRepository customerRepo = new CustomerRepository();
        VehicleRepository vehicleRepo = new VehicleRepository();
        RentedItemsRepository rentedItemsRepo = new RentedItemsRepository();
        InventoriRepository inventariRepo = new InventoriRepository();

        InventariService is = new InventariService();

        //customer
        Customer customer = new Customer("Zoeee", "Zo", "zo@test.com");
        customerRepo.shtoCustomer(customer);

        // vehicle ---
        Vehicle vehicle1 = new Vehicle("Toyota", "ToyotaModel", "Z33Z", 2023, 50.0, 200.0, Status.AVAILABLE);
        vehicleRepo.shtoVehicle(vehicle1);

        Vehicle vehicle2 = new Vehicle("Ford", " FordModel", "Z444Z", 2023, 50.0, 300.0, Status.AVAILABLE);
        vehicleRepo.shtoVehicle(vehicle2);

        // inventari ---
        inventariRepo.increaseQuantity(1, 1);

        // rental ---
        Rental rental = new Rental(
                customer,
                Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(7)),
                StatusiQirasë.active
        );


        is.startRenting(rental);

        RentedItems rent1 = new RentedItems(vehicle1,rental);
        RentedItems rent2 = new RentedItems(vehicle2,rental);

        rental.getRentedItems().add(rent1);
        rental.getRentedItems().add(rent2);

        vehicle1.getRentedItems().add(rent1);
        vehicle2.getRentedItems().add(rent2);

        rentedItemsRepo.shtoItem(rent1);
        rentedItemsRepo.shtoItem(rent2);

        is.rent(rental, rental.getRentedItems().size());

     //   is.ktheVehicle(7);
        //is.ktheVehicle(8);

        // --- payment ---
        double shumaTotale = is.llogaritPagesen(rental);
        Payment payment = new Payment(
                rental,
                shumaTotale,
                Date.valueOf(LocalDate.now()),
                StatusiPageses.pending
        );

        is.bejPagesen(payment);

       // is.markPaymentAsPaid(1);


    }
}
