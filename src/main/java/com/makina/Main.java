package com.makina;

import com.makina.Entity.*;
import com.makina.Repository.*;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {



        CustomerRepository cr = new CustomerRepository();
        VehicleRepository vr = new VehicleRepository();
        RentalRepository rr = new RentalRepository();
        PaymentRepository pr = new PaymentRepository();
        InventoriRepository ir = new InventoriRepository();


        Customer c1= new Customer("gr", "G", "g@gmail.com");
        //cr.shtoCustomer(c1);
        //c1.setEmail("miriUpdated@gmail.com");
       // cr.updateCustomer(c1);
       // cr.fshiCustomer(c1);

        Vehicle v1 = new Vehicle("V", "v",2010,3000.0, Status.AVAILABLE);
        vr.shtoVehicle(v1);




      //  pr.shtoPayment(p1);

       // cr.shfaqCustomer();
        //pr.shfaqPayments();

        //shfaqVehicle
         /*for (Vehicle v : vr.shfaqVehicle()) {
            System.out.println(v.getId());
        }*/

        //shfaq vehicle sipas statusit
       /* for (Vehicle v : vr.getVehiclesByStatus("AVAILABLE")) {
            System.out.println("ID: " + v.getId() + ", Model: " + v.getModel());
        }*/

        Inventori in = new Inventori(v1, 50);
        ir.shtoInventar(in);
       // rr.shfaqRents();

        Rental rent1 = new Rental(c1,v1,Date.valueOf("2025-06-02"),Date.valueOf("2025-06-15"));
        rr.shtoRental(rent1);
        ir.rent(v1,2);
        ir.ktheVehicle(v1,1);



        Payment p1 = new Payment(rent1,400.0,Date.valueOf("2025-06-15"));

    }
}