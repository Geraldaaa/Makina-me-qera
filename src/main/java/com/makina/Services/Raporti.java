package com.makina.Services;

import com.makina.Entity.Status;
import com.makina.Repository.VehicleRepository;
import com.makina.Repository.PaymentRepository;
import com.makina.util.HibernateConn;
import org.hibernate.Session;

public class Raporti {
    private final VehicleRepository vehicleRepo = new VehicleRepository();
    private final PaymentRepository paymentRepo = new PaymentRepository();


    public void showVehicleStats() {
        System.out.println("Available vehicles: " + countAvailableVehicles());
        System.out.println("Rented vehicles: " + countRentedVehicles());
    }


    public Long countAvailableVehicles() {
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            return s.createQuery(
                            "SELECT COUNT(v) FROM Vehicle v WHERE v.status = :status", Long.class)
                    .setParameter("status", Status.AVAILABLE)
                    .uniqueResult();
        }
    }

    public Long countRentedVehicles() {
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            return s.createQuery(
                            "SELECT COUNT(v) FROM Vehicle v WHERE v.status = :status", Long.class)
                    .setParameter("status", Status.RENTED)
                    .uniqueResult();
        }
    }


}
