package service;

import dao.OrderDao;
import model.Order;
import model.User;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Nanord on 04.03.2018.
 */
public class OrderService extends TemplateService<Order> implements OrderDao {
    public List<Order> getAll() {
        session = null;
        List<Order> orders = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "from Order";

            Query<Order> query =  session.createQuery(hql);
            orders = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
            return orders;
        }
    }

    public List<Order> getByUser(User user) {
        session = null;
        List<Order> orders = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "from Order  where user_id = :idUser";

            Query query =  session.createQuery(hql);
            query.setParameter("idUser", user.getId());
            orders = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return orders;
    }
}
