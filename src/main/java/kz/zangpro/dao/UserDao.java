package kz.zangpro.dao;

import kz.zangpro.models.User;
import kz.zangpro.utils.HibernateSessionFactoryUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDao {

    public User findById(int id){
        return HibernateSessionFactoryUtils.getSessionFactory().openSession().get(User.class, id);
    }

    public void save(User user){
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user){
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user){
        Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public List<User> findAll(){
        List<User> users = (List<User>) HibernateSessionFactoryUtils.getSessionFactory().openSession().createQuery("From Users").list();
        return  users;
    }
}
