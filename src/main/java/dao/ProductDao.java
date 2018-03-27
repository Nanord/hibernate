package dao;

import model.Category;
import model.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nanord on 04.03.2018.
 */
public interface ProductDao extends TemplateDao<Product> {
     List<Product> getByName(String name) throws SQLException;
     List<Product> getAll() throws SQLException;
}
