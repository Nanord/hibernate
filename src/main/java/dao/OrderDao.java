package dao;

import model.Order;
import model.User;

import java.util.List;

/**
 * Created by Nanord on 04.03.2018.
 */
public interface OrderDao extends TemplateDao<Order> {
    List<Order> getAll();
    List<Order> getByUser(User user);
}
