package service;

import dao.CategoryDao;
import model.Category;

import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nanord on 04.03.2018.
 */
public class CategoryService extends TemplateService<Category> implements CategoryDao {

    public List<Category> getByName(String name) throws SQLException {
        session = null;
        List<Category> categories = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "from Category where name = :name)";

            Query query =  session.createQuery(hql);
            query.setParameter("name", name);
            categories = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return categories;
    }

    public List<Category> getAll() {
        session = null;
        List<Category> categories = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "from Category";

            Query<Category> query =  session.createQuery(hql);
            categories = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            if(session != null && session.isOpen()) {
                session.close();
            }
        }
        return categories;
    }

}
