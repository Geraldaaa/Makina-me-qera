package com.makina;

import com.makina.Entity.*;
import com.makina.Repository.*;
import com.makina.Services.InventariService;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        //Krijimi i objekteve
        CustomerRepository customerRepo = new CustomerRepository();
        VehicleRepository vehicleRepo = new VehicleRepository();
        RentedItemsRepository rentedItemsRepo = new RentedItemsRepository();
        InventoriRepository inventariRepo = new InventoriRepository();

        InventariService is = new InventariService();

        //customer
        //Shtojme nje customer
        Customer customer = new Customer("Ami", "Ami", "amina@test.com");
        customerRepo.shtoCustomer(customer);

        // vehicle
        //Shtojme makinat
        Vehicle vehicle1 = new Vehicle("Merc", "MercModel", "G5566G", 2023, 50.0, 100.0, Status.AVAILABLE);
        vehicleRepo.shtoVehicle(vehicle1);

        Vehicle vehicle2 = new Vehicle("Ford", " FordModel", "M112552M", 2023, 50.0, 300.0, Status.AVAILABLE);
        vehicleRepo.shtoVehicle(vehicle2);

        // inventari ---
        //Rrisim quantity ne inventar per cdo makine qe shtojme. Sasia e makinave vendoset manualisht.
        inventariRepo.increaseQuantity(1, 2);

        // rental ---
        // krijimi i rental
        Rental rental = new Rental(
                customer,
                Date.valueOf(LocalDate.now()),
                Date.valueOf("2025-09-06"),
                StatusiQirasë.active
        );

        //fillojme procesin per te bere rent nje vehicle
        is.startRenting(rental);

        //krijimi i items te cilet do behen rent
        RentedItems rent1 = new RentedItems(vehicle1,rental);
        RentedItems rent2 = new RentedItems(vehicle2,rental);

        //popullojme listat e rental dhe vehicle me makinat qe do i marrim me qera
        rental.getRentedItems().add(rent1);
        rental.getRentedItems().add(rent2);
        vehicle1.getRentedItems().add(rent1);
        vehicle2.getRentedItems().add(rent2);


        rentedItemsRepo.shtoItem(rent1);
        rentedItemsRepo.shtoItem(rent2);

        //behet rent makinat e zgjedhura
        is.rent(rental, rental.getRentedItems().size());


        //kthejme makinen e cila eshte marre me qera
        // is.ktheVehicle(7);
        //is.ktheVehicle(8);

        // --- payment ---
        //krijojme nje payment
        double shumaTotale = is.llogaritPagesen(rental);
        Payment payment = new Payment(
                rental,
                shumaTotale,
                Date.valueOf(LocalDate.now()),
                StatusiPageses.pending
        );

        //behet pagesa per kete payment
        is.bejPagesen(payment);

        //pasi te behet pagesa e kalojme statusin ne paid
       // is.markPaymentAsPaid(1);


    }
}
