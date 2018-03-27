package dao;

import model.Category;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nanord on 03.03.2018.
 */
public interface UserDao extends  TemplateDao<User> {
     User getByEnter(String login, String password) throws SQLException;
     List<User> getAll() throws SQLException;
}
