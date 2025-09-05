package com.makina.Repository;

import com.makina.Entity.*;
import com.makina.util.HibernateConn;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class InventoriRepository {

    public void shtoInventar(Inventari i){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.save(i);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public void updateInventar(Inventari i){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.update(i);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public void increaseQuantity(int inventarId, int amount){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            Inventari inventar = s.get(Inventari.class, inventarId);
            inventar.increaseQuantity(amount);
            s.update(inventar);
            t.commit();
        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public void decreaseQuantity(int inventarId, int amount){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            Inventari inventar = s.get(Inventari.class, inventarId);
            inventar.decreaseQuantity(amount);
            s.update(inventar);
            t.commit();
        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }


    public void fshiInventar(Inventari i){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.delete(i);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public List<Inventari> shfaqInventarin(){

        Transaction t = null;
        List<Inventari> listInventari= null;
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            t = s.beginTransaction();
            listInventari = s.createQuery("from Inventori", Inventari.class).list();
            t.commit();

            for (Inventari i : listInventari) {
                System.out.println(i);
            }

        } catch (Exception e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        }

        return listInventari;

    }

}





