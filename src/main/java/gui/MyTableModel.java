package gui;

import com.sun.org.apache.xpath.internal.operations.Or;
import model.Order;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.event.EventListenerList;

/**
 * Created by Nanord on 15.03.2018.
 */
public class MyTableModel extends AbstractTableModel {
    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();
    protected EventListenerList listenerList = new EventListenerList();

    private List<Order> orders;

    public MyTableModel(List<Order> Orders) {
        this.orders = Orders;
    }

    public void addTableModelListener(TableModelListener l) {
        listenerList.add(TableModelListener.class, l);
    }

    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return "Дата";
            case 1:
                return "Общая стоимость";
            case 2:
                return "Наличные";
            case 3:
                return "Самовывоз";
            case 4:
                return "";
        }
        return "";
    }

    public int getRowCount() {
        return orders.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Order order = orders.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return order.getDate();
            case 1:
                return order.getPriceProduct();
            case 2:
                return order.getCash();
            case 3:
                return order.getPickup();
            case 4:
                return "Кнопка";
        }
        return "";
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void removeTableModelListener(TableModelListener l) {
        listenerList.remove(TableModelListener.class, l);
    }

    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }
}
