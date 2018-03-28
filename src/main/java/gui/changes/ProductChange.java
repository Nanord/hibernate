package gui.changes;

import model.Product;
import model.User;
import service.Factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ProductChange extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField id;
    private JTextArea description;
    private JTextField name;
    private JTextField cost;

    private Product product;
    private boolean upDl;

    public ProductChange(Product product, boolean upDl) {
        setContentPane(contentPane);
        setSize(new Dimension(500,300));
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.upDl = upDl;
        this.product = product;
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
        if(product != null) {
            id.setText(Integer.toString(product.getId()));
            name.setText(product.getName());
            description.setText(product.getDescription());
            cost.setText(Float.toString((float) product.getCost()));
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

    private float strToFloat(String input) {
        try {

            final float i = Float.parseFloat(input);
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
                    name.getText() != null &&
                    description.getText() != null &&
                    cost.getText() != null
                    ) {
                if(strToInt(id.getText()) != 0 && strToFloat(cost.getText()) != 0) {
                    product.setId(strToInt(id.getText()));
                    product.setCost(strToFloat(cost.getText()));
                } else {
                   JOptionPane.showMessageDialog(null, "Введите число!");
                   id.setText(Integer.toString(product.getId()));
                }
                product.setName(name.getText());
                product.setDescription(description.getText());
                if(upDl) Factory.getProductService().update(product);
                else Factory.getProductService().add(product);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Скорее всего, вы ввели существующий id");
        }
    }

    public Product getProduct() {
        return product;
    }
}
