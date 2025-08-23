package com.makina;

import com.makina.Entity.*;
import com.makina.Repository.CustomerRepository;
import com.makina.Repository.PaymentRepository;
import com.makina.Repository.RentalRepository;
import com.makina.Repository.VehicleRepository;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {



        CustomerRepository cr = new CustomerRepository();
        VehicleRepository vr = new VehicleRepository();
        RentalRepository rr = new RentalRepository();
        PaymentRepository pr = new PaymentRepository();


        Customer c1= new Customer("gz", "G", "g@gmail.com");
        //cr.shtoCustomer(c1);
        //c1.setEmail("miriUpdated@gmail.com");
       // cr.updateCustomer(c1);
       // cr.fshiCustomer(c1);

        Vehicle v1 = new Vehicle("D", "d",2018,400.0, Status.AVAILABLE);
       // vr.shtoVehicle(v1);

        Rental rent1 = new Rental(c1,v1,Date.valueOf("2025-06-02"),Date.valueOf("2025-06-15"));
      //  rr.shtoRental(rent1);

        Payment p1 = new Payment(rent1,400.0,Date.valueOf("2025-06-15"));
      //  pr.shtoPayment(p1);

       // cr.shfaqCustomer();
        //pr.shfaqPayments();
        //shfaqVehicle();
/*     for (Vehicle v : vr.shfaqVehicle()) {
            System.out.println(v.getStatus().name());
        }*/
       // rr.shfaqRents();

    }
}