package gui.changes;

import model.Order;
import model.User;
import service.Factory;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class OrderChange extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField id;
    private JTextField date;
    private JTextField cash;
    private JTextField pickup;
    private JTextField user_id;

    private Order order;
    private boolean upDl;

    public OrderChange(Order order, boolean upDl) {
        setContentPane(contentPane);
        setSize(new Dimension(500,300));
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.upDl = upDl;
        this.order = order;
        setUserTextField();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        getUserTextField();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private void setUserTextField() {
        if(order != null) {
            id.setText(Integer.toString(order.getId()));
            date.setText(order.getDate());
            cash.setText(order.getCash() == 0 ? "Нет" : "Да");
            pickup.setText(order.getPickup() == 0 ? "Нет" : "Да");
            user_id.setText(Integer.toString(order.getUser().getId()));
        }
    }

    private int strToInt(String input) {
        try {

            final int i = Integer.parseInt(input);
            return i;
        } catch(NumberFormatException e) {
            return 0;
        } catch(NullPointerException e) {
            return 0;
        }
    }

    private void getUserTextField() {
        try {
            if(id.getText() != null &&
                    date.getText() != null &&
                    pickup.getText() != null &&
                    cash.getText() != null &&
                    user_id.getText() != null
                    ) {
                if(strToInt(id.getText()) != 0 && strToInt(user_id.getText()) != 0) {
                    order.setUser(
                            equalsUser(
                                    strToInt(user_id.getText())
                            )
                    );
                    order.setId(strToInt(id.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Введите число!");
                    id.setText(Integer.toString(order.getId()));
                }
                order.setDate(date.getText());
                order.setCash(
                        cash.getText().equals("Нет")?0:cash.getText().equals("Да")?1:null
                );
                order.setPickup(
                        pickup.getText().equals("Нет")?0:cash.getText().equals("Да")?1:null
                );
                if(upDl) Factory.getOrderService().update(order);
                else Factory.getOrderService().add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Скорее всего, вы ввели существующий id");
        } catch (NullPointerException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Введите Да или Нет");
            cash.setText(order.getCash() == 0 ? "Нет":"Да");
            pickup.setText(order.getPickup() == 0 ? "Нет":"Да");
        }
    }

    private User equalsUser(int id) {
        User userResult = null;
        try {
            List<User> userList = Factory.getUserService().getAll();
            for (User user:
                    userList) {
                if(user.getId() == id) {
                    userResult = user;
                    break;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return userResult;
    }

    public Order getOrder() {
        return order;
    }
}
