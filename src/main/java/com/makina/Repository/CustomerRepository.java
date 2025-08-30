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

            Customer ekzistues = s.createQuery(
                            "FROM Customer c WHERE c.email = :email", Customer.class)
                    .setParameter("email", customer.getEmail())
                    .uniqueResult();

            if ( findTarget(customer) == true) {
                System.out.println("Ky customer ekziston " + customer.getEmail());
                return;
            } else {
                s.save(customer);
                System.out.println("Customer u shtua");
            }
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public static Boolean findTarget(Customer c) {
        Transaction t = null;
        Session s = HibernateConn.getSessionFactory().openSession();
        t = s.beginTransaction();
        Customer customer = s.createQuery(
                        "FROM Customer c WHERE c.email = :email", Customer.class)
                .setParameter("email", c.getEmail())
                .uniqueResult();

        if (customer != null) {
            return true;
        }
        return false;
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
