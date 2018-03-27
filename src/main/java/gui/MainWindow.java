package gui;

import model.Category;
import model.Order;
import model.User;
import service.Factory;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.util.Vector;

/**
 * Created by Nanord on 15.03.2018.
 */
public class MainWindow extends JFrame{
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField login;
    private JTextField lastName;
    private JTextField tel;
    private JTextField adres;
    private JTextField email;
    private JButton buttonOk;
    private JCheckBox checkBoxChanges;
    private JTextField firstName;
    private JPasswordField password;
    private JPanel Ord;
    private JTable table;
    private JButton make;
    private JComboBox comboBoxCategory;
    private JTable table1;
    private JScrollPane jSP;

    private User user;
    private List<Order> orders;

    public MainWindow(User user) {
        this.user = user;
        setContentPane(panel1);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setEditable(false);
        buttonOk.setVisible(false);
       // setPreferredSize(new Dimension(300, 300));
        createTable();
        createComboBox();

        setUserTextField();





        checkBoxChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setEditable(checkBoxChanges.isSelected());
            }
        });
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                getUserTextField();
                checkBoxChanges.setSelected(false);
            }
        });

    }

    private void createComboBox() {
        List<Category> categories = Factory.getCategoryService().getAll();

        List<String> catigoriesName = new ArrayList<String>();

        for (Category category:
             categories) {
                catigoriesName.add(category.getName());
        }

        comboBoxCategory.setModel(new DefaultComboBoxModel(catigoriesName.toArray()));


  /*
*//*  images = new ImageIcon[catigoriesName.length];
        Integer[] intArray = new Integer[catigoriesName.length];
        for (int i = 0; i < catigoriesName.length; i++) {
            intArray[i] = new Integer(i);
           // images[i] = createImageIcon("ponch.png");
            images[i] = new ImageIcon();
            if (images[i] != null) {
                images[i].setDescription(catigoriesName[i]);
            }
        }*//*
        //Create the combo box.
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel(intArray);
        comboBoxCategory.setModel(comboBoxModel);
        ComboBoxRenderer renderer= new ComboBoxRenderer(images, catigoriesName);
        renderer.setPreferredSize(new Dimension(200, 130));
        comboBoxCategory.setRenderer(renderer);
        comboBoxCategory.setMaximumRowCount(3);
        //comboBoxCategory.add();*/

    }

    private static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = MainWindow.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    private void createTable() {
        orders = Factory.getOrderService().getByUser(user);
        Vector<String> header = new Vector<String>();
        header.add("Дата");
        header.add("Общая стоимость");
        header.add("Наличные");
        header.add("Самовывоз");
        header.add("");
        header.add("id");

        Vector<String> id = new Vector<String>();
        Vector<String> date = new Vector<String>();
        Vector<String> pP = new Vector<String>();
        Vector<String> cash = new Vector<String>();
        Vector<String> pickup = new Vector<String>();
        Vector<String> buttonName = new Vector<String>();
        for (Order order:
             orders) {
            id.add(Integer.toString(order.getId()));
            date.add(order.getDate());
            pP.add(Float.toString(order.getPriceProduct()));
            cash.add(Integer.toString(order.getCash()));
            pickup.add(Integer.toString(order.getPickup()));
            buttonName.add("Подробнее");
        }
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn(header.get(5), id);
        tableModel.addColumn(header.get(0), date);
        tableModel.addColumn(header.get(1), pP);
        tableModel.addColumn(header.get(2), cash);
        tableModel.addColumn(header.get(3), pickup);
        tableModel.addColumn(header.get(4), buttonName);
        table.setModel(tableModel);

        Action delete = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                JTable table = (JTable)e.getSource();
                int modelRow = Integer.valueOf( e.getActionCommand() );
                String id_order = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));

                for (Order order:
                     orders) {
                    if(Integer.toString(order.getId()).equals(id_order)) {
                        //System.out.println(order.getStringProduct());
                        JOptionPane.showMessageDialog(null, order.getStringProduct());
                    }
                }
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(table, delete, 5);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
}

    private void setUserTextField() {
        login.setText(user.getLogin());
        password.setText(user.getPassword());
        lastName.setText(user.getLastName());
        tel.setText(user.getTel());
        adres.setText(user.getAdress());
        email.setText(user.getEmail());
        firstName.setText(user.getFirstName());
    }

    private void getUserTextField() {
        try {
            if(login.getText() != null &&
                    password.getText() != null &&
                    firstName.getText() != null &&
                    lastName.getText() != null &&
                    email.getText() != null &&
                    adres.getText() != null &&
                    tel.getText() != null) {
                User user = new User();
                user.setId(this.user.getId());
                user.setLogin(login.getText());
                user.setPassword(password.getText());
                user.setFirstName(firstName.getText());
                user.setLastName(lastName.getText());
                user.setAdress(adres.getText());
                user.setTel(tel.getText());
                Factory.getUserService().update(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void setEditable(boolean check) {
        buttonOk.setVisible(check);
        login.setEditable(check);
        password.setEnabled(check);
        lastName.setEditable(check);
        tel.setEditable(check);
        adres.setEditable(check);
        email.setEditable(check);
        firstName.setEditable(check);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
