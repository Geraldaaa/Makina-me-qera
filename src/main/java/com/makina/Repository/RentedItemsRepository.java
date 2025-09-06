package com.makina.Repository;

import com.makina.Entity.RentedItems;
import com.makina.util.HibernateConn;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;



public class RentedItemsRepository {

    public void shtoItem(RentedItems ri){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.save(ri);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public void updateItemRepository(RentedItems ri){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.update(ri);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public void fshiItemRepository(RentedItems ri){
        Transaction t=null;
        try(Session s= HibernateConn.getSessionFactory().openSession()){
            t=s.beginTransaction();
            s.delete(ri);
            t.commit();

        } catch(Exception e){
            if(t!=null)t.rollback();
            e.printStackTrace();}
    }

    public List<RentedItems> shfaqRentedItems(){

        Transaction t = null;
        List<RentedItems> items = new ArrayList<>();
        try (Session s = HibernateConn.getSessionFactory().openSession()) {
            t = s.beginTransaction();
             items = s.createQuery("from RentedItems", RentedItems.class).list();

            t.commit();

            for (RentedItems i : items) {
                System.out.println(i);
            }

        } catch (Exception e) {
            if (t != null) t.rollback();
            e.printStackTrace();
        }

        return items;

    }







}
