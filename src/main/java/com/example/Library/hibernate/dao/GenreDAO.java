package com.example.Library.hibernate.dao;

import com.example.Library.hibernate.HibernateUtil;
import com.example.Library.hibernate.models.Genre;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class GenreDAO {


    public static List<Genre> all(){
        ArrayList<Genre> genre = new ArrayList<>();
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            genre = (ArrayList<Genre>) session.createQuery("from Genre").list();
            session.getTransaction().commit();
            HibernateUtil.closeAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return genre;
    }


    public static void save(Genre genre){
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.persist(genre);
            session.getTransaction().commit();
            HibernateUtil.closeAll();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void update(Genre genre, int genre_id){
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.createQuery("update Genre set name = :name " + " WHERE genres_id= :genres_id")
                    .setParameter("name", genre.getName())
                    .setParameter("genres_id",genre_id).executeUpdate();
            session.getTransaction().commit();
            HibernateUtil.closeAll();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void delete (int genre_id){
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.createQuery("delete from Genre g where g.genres_id = :genres_id")
                    .setParameter("genres_id", genre_id).executeUpdate();
            session.getTransaction().commit();
            HibernateUtil.closeAll();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public  static void deleted(Genre genre){
        delete(genre.getGenres_id());
    }


}
