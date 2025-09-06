package com.makina.Services;

import com.makina.Entity.*;
import com.makina.Repository.PaymentRepository;
import com.makina.Repository.RentalRepository;
import com.makina.util.HibernateConn;
import org.hibernate.Session;
import org.hibernate.Transaction;
import  com.makina.Entity.Rental;
import com.makina.Entity.Payment;


public class InventariService {

    public void startRenting(Rental rental){
        RentalRepository rr = new RentalRepository();
        rr.shtoRental(rental);
    }

    public void rent(Rental rental, Integer sasiaMakinave) {
        Transaction t = null;
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            t = s.beginTransaction();

            Rental r = s.get(Rental.class, rental.getId());
            if (r == null) {
                throw new RuntimeException("Rental me id " + rental.getId() + " nuk ekziston");
            }

            r.setStatusiQirasë(StatusiQirasë.active);
            s.update(r);

            for (RentedItems ri : r.getRentedItems()) {
                Vehicle v = s.get(Vehicle.class, ri.getVehicle().getId());
                if (v != null) {
                    v.setStatus(Status.RENTED);
                    s.update(v);
                }
            }

            Inventari i = s.get(Inventari.class, 1);
            if (i != null) {
                i.decreaseQuantity(sasiaMakinave);
                s.update(i);
            }

            t.commit();
        } catch (Exception e) {
            if (t != null && t.isActive()) t.rollback();
            e.printStackTrace();
        }
    }


    public void ktheVehicle(Integer vehicleId) {
        Transaction t = null;
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            t = s.beginTransaction();

            setStatusiVehiclesAvailable(s,vehicleId);
            setStatusiQeraseRenturned(s,vehicleId);

            Inventari i = s.get(Inventari.class, 1);
            i.increaseQuantity(1);
            s.update(i);

            t.commit();
        } catch (Exception e) {
            if (t != null && t.isActive()) t.rollback();
            e.printStackTrace();
        }
    }

    public void setStatusiVehiclesAvailable(Session s, Integer vehicleId) {
        Vehicle v = s.get(Vehicle.class, vehicleId);
        if (v == null) {
            System.out.println("Makina nuk ekziston");
            return;
        }
        v.setStatus(Status.AVAILABLE);
        s.update(v);
    }



    public void setStatusiQeraseRenturned(Session s, Integer vehicleId) {
        Rental r = s.createQuery(
                        "select r from Rental r join r.RentedItems ri " +
                                "where ri.vehicle.id = :vid and r.statusiQirasë = :status",
                        Rental.class)
                .setParameter("vid", vehicleId)
                .setParameter("status", StatusiQirasë.active)
                .setMaxResults(1)
                .uniqueResult();

        if (r == null) {
            System.out.println("Nuk ekziston rental aktiv për këtë makinë (id=" + vehicleId + ")");
            return;
        }

        r.setStatusiQirasë(StatusiQirasë.returned);
        s.update(r);
    }

    public void bejPagesen(Payment payment){
        PaymentRepository pr = new PaymentRepository();
        pr.shtoPayment(payment);
        payment.setAmount(llogaritPagesen(payment.getRental()));
    }
    

    public Double llogaritPagesen(Rental rental) {
        Integer nrDiteve = rental.getEndDate().getDate() - rental.getStartDate().getDate()  ;

        double total = 0.0;

        for (RentedItems item : rental.getRentedItems()) {
            Vehicle v = item.getVehicle();
            
            if (v != null) {
                if(nrDiteve>7){
                    total += nrDiteve * v.getPrice()* 0.01;
                }

                total += nrDiteve * v.getPrice();

            }
        }

        return total;
    }



    public void markPaymentAsPaid(int paymentId) {
        Transaction t = null;
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            t = s.beginTransaction();

            Payment payment = s.get(Payment.class, paymentId);
            if (payment == null) {
                throw new RuntimeException("Payment me id " + paymentId + " nuk ekziston");
            }

            Inventari in = s.get(Inventari.class, 1);
            in.increaseQuantity(1);

            payment.setStatusiPageses(StatusiPageses.paid);
            s.update(payment);

            t.commit();
            System.out.println("Payment " + paymentId + " u be PAID.");
        } catch (Exception e) {
            if (t != null && t.isActive()) t.rollback();
            e.printStackTrace();
        }
    }

}

