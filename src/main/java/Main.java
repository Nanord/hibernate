
import gui.Enter;
import service.Factory;

import java.sql.SQLException;

/**
 * Created by Nanord on 01.03.2018.
 */
public class Main {
    /*public static void main(String[] args) {
        System.out.println("Hello World!");
        try {

            Order order = new Order();
            order.setDate("1");
            User user = new User();
            user.setLogin("123");
            user.setPassword("123");
            Factory.getUserService().add(user);
            order.setUser(user);
            Product product = new Product();
            product.setName("123");

            Set<Product> products = new HashSet<Product>();
            products.add(product);
            Factory.getProductService().add(product);
           // order.setProducts(products);

            Factory.getOrderService().add(order);

            user = Factory.getUserService().getByEnter("123", "123");



            System.out.println("1");

        } catch (SQLException ex) {
            System.out.println("Ошибка SQL");
            ex.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        try {
            Factory.getUserService().getByEnter("1","1");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Factory.getInstance();
        Enter ent = new Enter();
        ent.pack();
        ent.setVisible(true);
       // System.exit(1);
    }
}
