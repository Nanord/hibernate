package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nanord on 03.03.2018.
 */
public interface TemplateDao<T> {
     void add(T entity) throws SQLException;
     void update(T entity) throws SQLException;
     void delete(T entity) throws SQLException;
}
