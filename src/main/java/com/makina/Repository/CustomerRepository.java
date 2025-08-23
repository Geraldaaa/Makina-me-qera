package com.makina.Repository;

import com.makina.Entity.Customer;
import com.makina.util.HibernateConn;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerRepository {

    public void shtoCustomer(Customer customer){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.save(customer);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }


    public void updateCustomer(Customer customer){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.update(customer);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public void fshiCustomer(Customer c){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.delete(c);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();
        }
    }

    public List<Customer> shfaqCustomer() {
        Transaction t = null;
        List<Customer> customer = null;
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            t = s.beginTransaction();
            customer = s.createQuery("from Customer", Customer.class).list();
            t.commit();

            for (Customer c : customer) {
                System.out.println(c);
            }

        } catch (Exception e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        }
        return customer;
    }


}
