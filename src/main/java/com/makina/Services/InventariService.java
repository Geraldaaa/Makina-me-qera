package com.makina.Services;

import com.makina.Entity.*;
import com.makina.util.HibernateConn;
import org.hibernate.Session;
import org.hibernate.Transaction;
import  com.makina.Entity.Rental;
import com.makina.Entity.Payment;


public class InventariService {

    //perfundon procesing e rent
    public void rent(Vehicle vehicle,Rental rental, Integer sasiaMakinave) {
        Transaction t = null;
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            t = s.beginTransaction();

            Inventari inventar = s.createQuery(
                    "FROM Inventari i WHERE i.vehicle = :vehicle", Inventari.class).setParameter("vehicle", vehicle).uniqueResult();


            vehicle.setStatus(Status.RENTED);
            s.update(vehicle);

           rental.setStatusiQirasë(StatusiQirasë.active);
            s.save(rental);

            inventar.decreaseQuantity(1);
            t.commit();
            System.out.println("Makina u dha me qera. Makina te mbetura: " + inventar.getQuantity());

        } catch (Exception e) {
            if (t != null && t.isActive()) t.rollback();
            e.printStackTrace();
        }
    }

//kthen makinen e bere rent
    public void ktheVehicle(Long vehicleId, Integer sasiaMakinave) {
        Transaction t = null;
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            t = s.beginTransaction();

            Vehicle v = s.get(Vehicle.class, vehicleId);
            if (v == null) throw new RuntimeException("Makina nuk ekziston!");

            Inventari inventar = s.createQuery(
                            "FROM Inventari i WHERE i.vehicle.id = :vid", Inventari.class)
                    .setParameter("vid", vehicleId)
                    .uniqueResult();

            Rental r = s.createQuery(
                            "FROM Rental r WHERE r.vehicle.id = :vid AND r.statusiQirasë = :status", Rental.class)
                    .setParameter("vid", vehicleId)
                    .setParameter("status", StatusiQirasë.active)
                    .setMaxResults(1)
                    .uniqueResult();

            if (r == null) {
                System.out.println("Nuk ekziston rental aktiv pr kete makin");
            }

            v.setStatus(Status.AVAILABLE);
            r.setStatusiQirasë(StatusiQirasë.returned);
            s.update(v);
            s.update(r);

            inventar.increaseQuantity(1);
            s.update(inventar);

            t.commit();
            System.out.println("Makina u kthye. Gjithsej ne stok: " + inventar.getQuantity());
        } catch (Exception e) {
            if (t != null && t.isActive()) t.rollback();
            e.printStackTrace();
        }
    }

    public Double llogaritPagesen(Vehicle v, Rental r){

        Double cmimi = v.getPrice();
       Integer nrDiteve = r.getStartDate().getDate() - r.getEndDate().getDate() ;

       return cmimi*nrDiteve;

    }

    //ben statusin e payment paid pasi eshte ber pagesa
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

