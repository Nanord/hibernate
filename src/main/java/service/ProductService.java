package service;

import dao.ProductDao;
import model.Product;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nanord on 04.03.2018.
 */
public class ProductService extends TemplateService<Product> implements ProductDao{

    public List<Product> getByName(String name) throws SQLException {
        session = null;
        List<Product> products = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "from Product where name = :name";

            Query query =  session.createNativeQuery(hql);
            query.setParameter("name", name);
            products = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
            return products;
        }
    }

    public List<Product> getAll() throws SQLException {
        session = null;
        List<Product> products = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql = "from Product";

            Query query = session.createNativeQuery(sql);
            products = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
            return products;
        }
    }
}
