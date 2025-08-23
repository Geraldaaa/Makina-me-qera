package com.makina.Repository;

import com.makina.Entity.Customer;
import com.makina.Entity.Vehicle;
import com.makina.util.HibernateConn;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    public void shtoVehicle(Vehicle vehicle){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.save(vehicle);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }


    public void updateVehicle(Vehicle v){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.update(v);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public void fshiVehicle(Vehicle v){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.delete(v);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();
        }
    }


    public List<Vehicle> shfaqVehicle() {

        Transaction t = null;
        List<Vehicle> vehicleList =  new ArrayList<>();
        try (Session s = HibernateConn.getSessionFactory().openSession()) {

            t = s.beginTransaction();
            vehicleList = s.createQuery("from Vehicle", Vehicle.class).list();
            t.commit();


        } catch (Exception e) {
            if (t != null && t.getStatus().canRollback()) {
                try {
                    t.rollback();
                } catch (Exception rbEx) {
                    System.err.println("Rollback failed: " + rbEx.getMessage());
                }
            }
            e.printStackTrace();
        }
        return vehicleList;
    }





}
