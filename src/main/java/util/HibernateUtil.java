package util; /**
 * Created by Nanord on 01.03.2018.
 */

import model.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().addAnnotatedClass(User.class)
                    .addAnnotatedClass(Order.class)
                    .addAnnotatedClass(Role.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Category.class)
                    .configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Initial SessionFactory creation failed" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}