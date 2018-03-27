package dao;

import model.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nanord on 04.03.2018.
 */
public interface CategoryDao extends TemplateDao<Category> {
     List<Category> getByName(String name) throws SQLException;
     List<Category> getAll();
}
