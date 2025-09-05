package com.makina.Repository;

import com.makina.Entity.Rental;
import com.makina.Entity.RentedItems;
import com.makina.Entity.Status;
import com.makina.Entity.Vehicle;
import com.makina.util.HibernateConn;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RentalRepository {


    public void shtoRental(Rental rental) {
        Transaction t = null;
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            t = s.beginTransaction();

            for (RentedItems ri : rental.getRentedItems()) {
                Vehicle v = ri.getVehicle();
                if (v.getStatus() != Status.AVAILABLE) {
                    System.out.println("Makina " + v.getTarga() + " nuk eshte e disponueshme");
                    return;
                }
                v.setStatus(Status.RENTED);
                s.update(v);
            }
            s.save(rental);
            t.commit();

        } catch (Exception e) {
            if (t != null && t.getStatus().canRollback()) t.rollback();
            e.printStackTrace();
        }
    }


    public void updateRent(Rental rental){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.update(rental);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public void fshiRent(Rental r){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.delete(r);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();
        }
    }

    public List<Rental> shfaqRents() {
        Transaction t = null;
        List<Rental> rentals = null;
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            t = s.beginTransaction();
            rentals = s.createQuery("from Rental", Rental.class).list();
            t.commit();

            for (Rental r : rentals) {
                System.out.println(r);
            }

        } catch (Exception e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        }
        return rentals;
    }



}
