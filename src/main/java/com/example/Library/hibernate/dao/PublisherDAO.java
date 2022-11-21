package com.example.Library.hibernate.dao;


import com.example.Library.hibernate.HibernateUtil;
import com.example.Library.hibernate.models.Publisher;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class PublisherDAO {



    public static List<Publisher> all(){
        ArrayList<Publisher> publisher = new ArrayList<>();
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            publisher = (ArrayList<Publisher>) session.createQuery("from Publisher").list();
            session.getTransaction().commit();
            HibernateUtil.closeAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return publisher;
    }

    public static void save(Publisher publisher){
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.persist(publisher);
            session.getTransaction().commit();
            HibernateUtil.closeAll();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void update (Publisher publisher , int publisher_id){
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.createQuery("update Publisher set name = :name, " + " website = :website, " +
                            " address = :address, " +
                            " books = :books " + " WHERE publishers_id= :publishers_id")
                    .setParameter("name", publisher.getName())
                    .setParameter("website", publisher.getWebsite())
                    .setParameter("address", publisher.getAddress())
                    .setParameter("books", publisher.getBooks())
                    .setParameter("publishers_id",publisher_id).executeUpdate();
            session.getTransaction().commit();
            HibernateUtil.closeAll();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void delete (int publisher_id){
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.createQuery("delete from Publisher p where p.publishers_id = :publishers_id")
                    .setParameter("publishers_id", publisher_id).executeUpdate();
            session.getTransaction().commit();
            HibernateUtil.closeAll();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public  static void deleted(Publisher publisher){
        delete(publisher.getPublishers_id());
    }
}
