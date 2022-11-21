package com.example.Library.hibernate.dao;

import com.example.Library.hibernate.HibernateUtil;
import com.example.Library.hibernate.models.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;


public class BookDAO {

public static ArrayList<Book> all(){
    ArrayList<Book> books = new ArrayList<>();
    try{
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        books = (ArrayList<Book>) session.createQuery("from Book", Book.class).list();
        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

    }catch(Exception e){
        e.printStackTrace();
    }
    return books;
}


 public static Book one(int book_id){

     try{
         Session session = HibernateUtil.openSession();
         // Ako Nista ne sacuvavamo u bazi ili updatujemo. Onda ne moramo koristiti
         // session.getTransaction().begin(); i session.getTransaction().commit();
         session.getTransaction().begin();
         Book book = session.createQuery("from Book b where b.books_id = :books_id", Book.class)
                    .setParameter("books_id", book_id).getSingleResult();

         session.getTransaction().commit();
         HibernateUtil.closeAll();
         return book;

     }catch(Exception e){
         e.printStackTrace();
         return null;
     }
 }

   public static void save (Book book){
       try{
           Session session = HibernateUtil.openSession();
           // Kada nesto treba da bude sacuvano , izmenjeno ili obriseno u bazi.
           // Tada moramo koristiti session.getTransaction().begin(); i session.getTransaction().commit();
           session.getTransaction().begin();
           // metoda za cuvanje.
           session.persist(book);
           session.getTransaction().commit();
           HibernateUtil.closeAll();

       }catch(Exception e){
           e.printStackTrace();
       }
   }

   public static  void update(Book book , int book_id){

    try{
           Session session = HibernateUtil.openSession();
           session.getTransaction().begin();
           session.createQuery("update Book set title = :title, " + " cover = :cover, " +
                           " release_date = :release_date, " +
                           " synopsis = :synopsis " + " WHERE books_id= :books_id")
                   .setParameter("title", book.getTitle())
                   .setParameter("cover", book.getCover())
                   .setParameter("release_date", book.getRelease_date())
                   .setParameter("synopsis", book.getSynopsis())
                   .setParameter("books_id",book_id).executeUpdate();
           session.getTransaction().commit();
           HibernateUtil.closeAll();

       }catch(Exception e){
          e.printStackTrace();
       }

   }

   public static void delete (int books_id){
       try{
           Session session = HibernateUtil.openSession();
           session.getTransaction().begin();
           session.createQuery("delete from Book b where b.books_id = :books_id")
                   .setParameter("books_id", books_id).executeUpdate();
           session.getTransaction().commit();
           HibernateUtil.closeAll();
       }catch(Exception e){
           e.printStackTrace();
       }
   }

   public  static void deleted(Book book){
    delete(book.getBooks_id());
   }
}
