package com.makina;

import com.makina.Entity.*;
import com.makina.Repository.*;
import com.makina.Services.InventariService;
import com.makina.Services.Raporti;

import java.sql.Date;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        CustomerRepository cr = new CustomerRepository();
        VehicleRepository vr = new VehicleRepository();
        RentalRepository rr = new RentalRepository();
        PaymentRepository pr = new PaymentRepository();
        InventoriRepository ir = new InventoriRepository();
        InventariService is = new InventariService();

        Customer c1 = new Customer("Gz", "GZ", "gz@test.com");
     //   cr.shtoCustomer(c1);

        Vehicle v1 = new Vehicle("NewBrand", "NewModel", "AAVAAA", 2020, 3.0, 2000.0, Status.AVAILABLE);
       // vr.shtoVehicle(v1);

        Inventori in = new Inventori();
       // ir.increaseQuantity(1,1);

     //   ir.shtoInventar(in);
        //ir.updateInventar(in);

      //  ir.decreaseQuantity(1,2);

        Rental rent1 = new Rental(
                c1,
                Date.valueOf(LocalDate.now()),
                Date.valueOf(LocalDate.now().plusDays(10)),
                StatusiQirasë.active
        );

      // rr.shtoRental(rent1);
      // is.rent(v1, rent1,2);
       // is.ktheVehicle(v1.getId(),1);

        Payment p = new Payment(rent1, v1.getPrice(), Date.valueOf(LocalDate.now()),StatusiPageses.pending);
      //  pr.shtoPayment(p);
      //  is.markPaymentAsPaid(2);


        Raporti raporti = new Raporti();
       //System.out.println(raporti.countAvailableVehicles());
        //raporti.showVehicleStats();



    }
}
