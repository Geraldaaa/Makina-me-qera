package com.makina.Services;

import com.makina.Entity.Payment;
import com.makina.Entity.Status;
import com.makina.Entity.Vehicle;
import com.makina.Repository.VehicleRepository;
import com.makina.Repository.PaymentRepository;
import com.makina.util.HibernateConn;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public List<Vehicle> getVehiclesByStatus(String statusi) {
        Status status = Status.valueOf(statusi);

        try (Session session = HibernateConn.getSessionFactory().openSession()) {
            String hql = "FROM Vehicle v WHERE v.status = :status";
            return session.createQuery(hql, Vehicle.class)
                    .setParameter("status", status)
                    .list();
        }
    }

    public List<Vehicle> getVehiclesByCustomer(Long customerId) {

        List<Vehicle> vehicles = new ArrayList<>();
        Transaction t = null;
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            t = s.beginTransaction();
            vehicles = s.createQuery("SELECT r.vehicle FROM Rental r WHERE r.customer.id = :customerId", Vehicle.class
                    ).setParameter("customerId", customerId)
                    .list();
            t.commit();

        } catch (Exception e) {
            if (t != null && t.getStatus().canRollback()) {
                t.rollback();
            }
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Payment> pagesatSipasDates(Date paymentDate){

        try (Session s = HibernateConn.getSessionFactory().openSession()) {

           return s.createQuery("FROM Payment p WHERE p.paymentDate = :paymentDate", Payment.class)
                   .setParameter("paymentDate", paymentDate)
                   .list();

        }

    }

    public List<Payment> pagesatPerCustomer(Integer customerID){

        try (Session s = HibernateConn.getSessionFactory().openSession()) {

            return s.createQuery("FROM Payment p JOIN Rental r ON p.id = r.id WHERE r.customer.id = :customerID", Payment.class)
                    .setParameter("customerID", customerID)
                    .list();

        }

    }
}
