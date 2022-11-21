package com.example.Library.hibernate.dao;


import com.example.Library.hibernate.HibernateUtil;
import com.example.Library.hibernate.models.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;

   public class AuthorsDAO {

    public static ArrayList<Author> all(){
     ArrayList<Author> authors = new ArrayList<>();
     try{
         SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
         Session session = sessionFactory.openSession();
         session.getTransaction().begin();

       authors = (ArrayList<Author>) session.createQuery("from Author", Author.class).list();
         session.getTransaction().commit();
         session.close();
         sessionFactory.close();
     }catch(Exception e){
         e.printStackTrace();
     }
     return authors;
   }

   // Save Author
   public static void save(Author authors){
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.persist(authors);
            session.getTransaction().commit();
            HibernateUtil.closeAll();

        }catch (Exception e){
            e.printStackTrace();
        }
   }

   // Update Author
   public static void update (Author author, int author_id){
       try{
           Session session = HibernateUtil.openSession();
           session.getTransaction().begin();
           session.createQuery("update Author set full_name = :full_name, " + " photo = :photo, " +
                           " biography = :biography, " +
                           " born = :born, " +" died = :died " + " WHERE authors_id= :authors_id")
                   .setParameter("full_name", author.getFull_name())
                   .setParameter("photo", author.getPhoto())
                   .setParameter("biography", author.getBiography())
                   .setParameter("born", author.getBorn())
                   .setParameter("died", author.getDied())
                   .setParameter("authors_id",author_id).executeUpdate();
           session.getTransaction().commit();
           HibernateUtil.closeAll();

       }catch (Exception e){
           e.printStackTrace();
       }
   }

       public static void delete (int author_id){
           try{
               Session session = HibernateUtil.openSession();
               session.getTransaction().begin();
               session.createQuery("delete from Author a where a.authors_id = :authors_id")
                       .setParameter("authors_id", author_id).executeUpdate();
               session.getTransaction().commit();
               HibernateUtil.closeAll();
           }catch(Exception e){
               e.printStackTrace();
           }
       }

       public  static void deleted(Author author){
           delete(author.getAuthors_id());
       }


}
