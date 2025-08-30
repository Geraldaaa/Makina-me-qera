package com.makina.Repository;

import com.makina.Entity.Status;
import com.makina.Entity.Vehicle;
import com.makina.util.HibernateConn;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository {

    public void shtoVehicle(Vehicle vehicle) {
        Transaction t = null;
        Session s = null;
        try {
            s = HibernateConn.getSessionFactory().openSession();
            t = s.beginTransaction();


            if ( findTarget(vehicle) == true) {
                System.out.println("Makina me kete targe eshte regjistruar me pare.");
            } else {
                s.save(vehicle);
                System.out.println("Makina u shtua me sukses");
            }
            t.commit();
        } catch (Exception e) {
            if (t != null && t.isActive()) {
                t.rollback();
            }
            e.printStackTrace();
        }
    }


    public static Boolean findTarget(Vehicle v) {
        Transaction t = null;
        Session s = HibernateConn.getSessionFactory().openSession();
        t = s.beginTransaction();
        Vehicle ekzistues = s.createQuery(
                            "FROM Vehicle v WHERE v.targa = :targa", Vehicle.class)
                    .setParameter("targa", v.getTarga())
                    .uniqueResult();

            if (ekzistues != null) {
                return true;
            }
        return false;
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







}
