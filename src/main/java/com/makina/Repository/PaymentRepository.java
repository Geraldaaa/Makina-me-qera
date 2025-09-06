package com.makina.Repository;

import com.makina.Entity.Payment;
import com.makina.Entity.Rental;
import com.makina.Entity.Vehicle;
import com.makina.util.HibernateConn;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class PaymentRepository {

    public void shtoPayment(Payment payment){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.save(payment);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public void updatePayment(Payment p){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.update(p);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public void fshiPayment(Payment p){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.delete(p);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();
        }
    }

    public List<Payment> shfaqPayments(){

        Transaction t = null;
        List<Payment> paymentList= null;
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            t = s.beginTransaction();
            paymentList = s.createQuery("from Payment", Payment.class).list();
            t.commit();

            for (Payment p : paymentList) {
                System.out.println(p);
            }

        } catch (Exception e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        }

        return paymentList;

    }

    public Double llogaritPagesen(Vehicle v, Rental r){

        Double cmimi = v.getPrice();
        Integer nrDiteve = r.getStartDate().getDate() - r.getEndDate().getDate() ;

        return cmimi*nrDiteve;

    }
}

