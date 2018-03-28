package gui;

import com.sun.org.apache.xpath.internal.operations.Or;
import gui.changes.CategoryChange;
import gui.changes.OrderChange;
import gui.changes.ProductChange;
import gui.changes.UserChange;
import model.Category;
import model.Order;
import model.Product;
import model.User;
import service.Factory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Nanord on 28.03.2018.
 */
public class AdminMainWindow extends JFrame{
    private JPanel panel1;
    private JTable tableUsers;
    private JTable orders;
    private JTable tableCategories;
    private JTable tableProducts;
    private JTable tableOrders;
    private JButton addUser;
    private JButton addOrder;
    private JButton addCategory;
    private JButton addProduct;

    private User admin;
    private List<User> userList;
    private List<Order> orderList;
    private List<Category> categoryList;
    private List<Product> productList;

    AdminMainWindow(User user) {
        setContentPane(panel1);
        setSize(new Dimension(1500,300));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        admin = user;
        getAll();
        createTableUsers();
        createTableCategories();
        createTableOrders();
        createTableProducts();


        addUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserChange categoryChange = new UserChange(null, false);
                categoryChange.setVisible(true);
                getAll();
                createTableUsers();
            }
        });
        addOrder.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                OrderChange orderChange = new OrderChange(null, false);
                orderChange.setVisible(true);
                getAll();
                createTableOrders();
            }
        });
        addCategory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CategoryChange categoryChange = new CategoryChange(null, false);
                categoryChange.setVisible(true);
                getAll();
                createTableCategories();
            }
        });
        addProduct.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProductChange productChange = new ProductChange(null, false);
                productChange.setVisible(true);
                getAll();
                createTableProducts();
            }
        });
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    private void createTableUsers() {
        Vector<String> header = new Vector<String>();
        header.add("id");
        header.add("Логин");
        header.add("Имя");
        header.add("Фамилия");
        header.add("Email");
        header.add("Адрес");
        header.add("Телефон");
        header.add("");
        header.add("");

        Vector<String> id = new Vector<String>();
        Vector<String> login = new Vector<String>();
        Vector<String> first_name = new Vector<String>();
        Vector<String> last_name = new Vector<String>();
        Vector<String> email = new Vector<String>();
        Vector<String> adress = new Vector<String>();
        Vector<String> tel = new Vector<String>();
        Vector<String> buttonChange = new Vector<String>();
        Vector<String> buttonDelete = new Vector<String>();
        for (User user:
                userList) {
            id.add(Integer.toString(user.getId()));
            login.add(user.getLogin());
            first_name.add(user.getFirstName());
            last_name.add(user.getLastName());
            email.add(user.getEmail());
            adress.add(user.getAdress());
            tel.add(user.getTel());
            buttonChange.add("Изменить");
            buttonDelete.add("Удалить");
        }
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn(header.get(0), id);
        tableModel.addColumn(header.get(1), login);
        tableModel.addColumn(header.get(2), first_name);
        tableModel.addColumn(header.get(3), last_name);
        tableModel.addColumn(header.get(4), email);
        tableModel.addColumn(header.get(5), adress);
        tableModel.addColumn(header.get(6), tel);
        tableModel.addColumn(header.get(7), buttonChange);
        tableModel.addColumn(header.get(8), buttonDelete);
        tableUsers.setModel(tableModel);

        Action change = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                String id_user = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));

                for (User user:
                        userList) {
                    if(Integer.toString(user.getId()).equals(id_user)) {
                        UserChange userChange = new UserChange(user, true);
                        userChange.setVisible(true);

                    }
                }
                getAll();
                createTableUsers();
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(tableUsers, change, 7);
        buttonColumn.setMnemonic(KeyEvent.VK_D);

        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                String id_user = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));

                for (User user:
                        userList) {
                    if(Integer.toString(user.getId()).equals(id_user)) {
                        int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "?",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            try {
                                Factory.getUserService().delete(user);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                getAll();
                createTableUsers();
            }
        };
        ButtonColumn buttonColumn2 = new ButtonColumn(tableUsers, delete, 8);
        buttonColumn2.setMnemonic(KeyEvent.VK_D);
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    private void createTableOrders() {
        Vector<String> header = new Vector<String>();
        header.add("Дата");
        header.add("Общая стоимость");
        header.add("Наличные");
        header.add("Самовывоз");
        header.add("");
        header.add("id");
        header.add("");
        header.add("");

        Vector<String> id = new Vector<String>();
        Vector<String> date = new Vector<String>();
        Vector<String> pP = new Vector<String>();
        Vector<String> cash = new Vector<String>();
        Vector<String> pickup = new Vector<String>();
        Vector<String> buttonName = new Vector<String>();
        Vector<String> buttonChange = new Vector<String>();
        Vector<String> buttonDelete = new Vector<String>();
        for (Order order:
                orderList) {
            id.add(Integer.toString(order.getId()));
            date.add(order.getDate());
            pP.add(Float.toString(order.getPriceProduct()));
            cash.add(order.getCash() == 0?"Нет":"Да");
            pickup.add(order.getPickup() == 0?"Нет":"Да");
            buttonName.add("Подробнее");
            buttonChange.add("Изменить");
            buttonDelete.add("Удалить");
        }
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn(header.get(5), id);
        tableModel.addColumn(header.get(0), date);
        tableModel.addColumn(header.get(1), pP);
        tableModel.addColumn(header.get(2), cash);
        tableModel.addColumn(header.get(3), pickup);
        tableModel.addColumn(header.get(4), buttonName);
        tableModel.addColumn(header.get(6), buttonChange);
        tableModel.addColumn(header.get(7), buttonDelete);
        tableOrders.setModel(tableModel);

        Action detail = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                String id_order = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));

                for (Order order:
                        orderList) {
                    if(Integer.toString(order.getId()).equals(id_order)) {
                        JOptionPane.showMessageDialog(null, order.getStringProduct());
                    }
                }
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(tableOrders, detail, 5);
        buttonColumn.setMnemonic(KeyEvent.VK_D);

        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                String id_order = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));

                for (Order order:
                        orderList) {
                    if(Integer.toString(order.getId()).equals(id_order)) {
                        int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "?",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            try {
                                Factory.getOrderService().delete(order);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                getAll();
                createTableOrders();
            }
        };
        ButtonColumn buttonColumn2 = new ButtonColumn(tableOrders, delete, 7);
        buttonColumn2.setMnemonic(KeyEvent.VK_D);

        Action change = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                String id_order = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));

                for (Order order:
                        orderList) {
                    if(Integer.toString(order.getId()).equals(id_order)) {
                        OrderChange orderChange = new OrderChange(order, true);
                        orderChange.setVisible(true);
                    }
                }
                getAll();
                createTableOrders();
            }
        };
        ButtonColumn buttonColumn3 = new ButtonColumn(tableOrders, change, 6);
        buttonColumn3.setMnemonic(KeyEvent.VK_D);
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    private void createTableCategories() {
        Vector<String> header = new Vector<String>();
        header.add("id");
        header.add("Название");
        header.add("Описание");
        header.add("");
        header.add("");
        header.add("");

        Vector<String> id = new Vector<String>();
        Vector<String> name = new Vector<String>();
        Vector<String> description = new Vector<String>();
        Vector<String> buttonDetail = new Vector<String>();
        Vector<String> buttonChange = new Vector<String>();
        Vector<String> buttonDelete = new Vector<String>();
        for (Category category:
                categoryList) {
            id.add(Integer.toString(category.getId()));
            name.add(category.getName());
            description.add(category.getDescription());
            buttonDetail.add("Подробнее");
            buttonChange.add("Изменить");
            buttonDelete.add("Удалить");
        }
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn(header.get(0), id);
        tableModel.addColumn(header.get(1), name);
        tableModel.addColumn(header.get(2), description);
        tableModel.addColumn(header.get(3), buttonDetail);
        tableModel.addColumn(header.get(4), buttonChange);
        tableModel.addColumn(header.get(5), buttonDelete);
        tableCategories.setModel(tableModel);

        Action detail = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                String id_category = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));

                for (Category category:
                        categoryList) {
                    if(Integer.toString(category.getId()).equals(id_category)) {
                        JOptionPane.showMessageDialog(null, category.toStringProducts());
                    }
                }
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(tableCategories, detail, 3);
        buttonColumn.setMnemonic(KeyEvent.VK_D);

        Action change = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                String id_category = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));

                for (Category category:
                        categoryList) {
                    if(Integer.toString(category.getId()).equals(id_category)) {
                        CategoryChange categoryChange = new CategoryChange(category, true);
                        categoryChange.setVisible(true);
                    }
                }
                getAll();
                createTableCategories();
            }
        };
        ButtonColumn buttonColumn2 = new ButtonColumn(tableCategories, change, 4);
        buttonColumn2.setMnemonic(KeyEvent.VK_D);

        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                String id_category = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));

                for (Category category:
                        categoryList) {
                    if(Integer.toString(category.getId()).equals(id_category)) {
                        int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "?",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            try {
                                Factory.getCategoryService().delete(category);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                getAll();
                createTableCategories();
            }
        };
        ButtonColumn buttonColumn3 = new ButtonColumn(tableCategories, delete, 5);
        buttonColumn3.setMnemonic(KeyEvent.VK_D);
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////
    private void createTableProducts() {
        Vector<String> header = new Vector<String>();
        header.add("id");
        header.add("Название");
        header.add("Описание");
        header.add("Категория");
        header.add("");
        header.add("");

        Vector<String> id = new Vector<String>();
        Vector<String> name = new Vector<String>();
        Vector<String> description = new Vector<String>();
        Vector<String> category = new Vector<String>();
        Vector<String> buttonChange = new Vector<String>();
        Vector<String> buttonDelete = new Vector<String>();
        for (Product product:
                productList) {
            id.add(Integer.toString(product.getId()));
            name.add(product.getName());
            description.add(product.getDescription());
            category.add(product.getCategory().getName());
            buttonChange.add("Изменить");
            buttonDelete.add("Удалить");
        }
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn(header.get(0), id);
        tableModel.addColumn(header.get(1), name);
        tableModel.addColumn(header.get(2), description);
        tableModel.addColumn(header.get(3), category);
        tableModel.addColumn(header.get(4), buttonChange);
        tableModel.addColumn(header.get(5), buttonDelete);
        tableProducts.setModel(tableModel);

        Action change = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                String id_product = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));

                for (Product product:
                        productList) {
                    if(Integer.toString(product.getId()).equals(id_product)) {
                        ProductChange productChange = new ProductChange(product, true);
                        productChange.setVisible(true);
                    }
                }
                getAll();
                createTableProducts();
            }
        };
        ButtonColumn buttonColumn2 = new ButtonColumn(tableProducts, change, 4);
        buttonColumn2.setMnemonic(KeyEvent.VK_D);

        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                String id_product = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));

                for (Product product:
                        productList) {
                    if(Integer.toString(product.getId()).equals(id_product)) {
                        int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "?",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                        if (response == JOptionPane.YES_OPTION) {
                            try {
                                Factory.getProductService().delete(product);
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
                getAll();
                createTableProducts();
            }
        };
        ButtonColumn buttonColumn3 = new ButtonColumn(tableProducts, delete, 5);
        buttonColumn3.setMnemonic(KeyEvent.VK_D);
    }
    ////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    private void getAll() {
        try {
            userList = Factory.getUserService().getAll();
            categoryList = Factory.getCategoryService().getAll();
            orderList = Factory.getOrderService().getAll();
            productList = new ArrayList<Product>();


            for (Category category:
                    categoryList) {
                for(Product product:
                        category.getProducts()) {
                    productList.add(product);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
