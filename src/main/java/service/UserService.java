package service;

import dao.UserDao;
import model.User;
import org.hibernate.query.Query;
import util.HibernateUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Nanord on 03.03.2018.
 */
public class UserService extends  TemplateService<User> implements UserDao {

    public User getByEnter(String login, String password) throws SQLException {
        session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            /*Query query =  session.createNativeQuery("select * from User where User.login = :login and User.password = :password");
            query.setParameter(login, login);
            query.setParameter(password, password);
*/
            String hql = "from User u where u.login = :login and u.password = :password";

            Query  query1 = session.createQuery(hql);
            query1.setParameter("login", login);
            query1.setParameter("password", password);
            List<User> users = query1.getResultList();
            if(users.size() != 1) {
                return null;
            }
            else {
                user = users.get(0);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    public List<User> getAll() throws SQLException {
        session = null;
        List<User> users = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "from User";

            Query<User> query =  session.createQuery(hql);
            users = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
            return users;
        }
    }

}
