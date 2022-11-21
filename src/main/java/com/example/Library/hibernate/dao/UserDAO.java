package com.example.Library.hibernate.dao;


import com.example.Library.hibernate.HibernateUtil;
import com.example.Library.hibernate.models.User;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {


    public static List<User> all(){
        ArrayList<User> users = new ArrayList<>();
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            users = (ArrayList<User>) session.createQuery("from User").list();
            session.getTransaction().commit();
            HibernateUtil.closeAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }


    public static void  save(User user) {
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.persist(user);
            session.getTransaction().commit();
            HibernateUtil.closeAll();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void update(User user, int user_id){
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.createQuery("update User set username = :username, " + " password = :password, " +
                            " email = :email, " +
                            " user_type = :user_type " + " WHERE users_id= :users_id")
                    .setParameter("username", user.getUsername())
                    .setParameter("password", user.getPassword())
                    .setParameter("email", user.getEmail())
                    .setParameter("user_type", user.getUser_type())
                    .setParameter("users_id",user_id).executeUpdate();
            session.getTransaction().commit();
            HibernateUtil.closeAll();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void delete (int user_id){
        try{
            Session session = HibernateUtil.openSession();
            session.getTransaction().begin();
            session.createQuery("delete from User u where u.users_id = :users_id")
                    .setParameter("users_id", user_id).executeUpdate();
            session.getTransaction().commit();
            HibernateUtil.closeAll();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public  static void deleted(User user){
        delete(user.getUsers_id());
    }
}
