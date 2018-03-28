package gui.changes;

import model.Category;
import service.Factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class CategoryChange extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField id;
    private JTextArea description;
    private JTextField name;

    Category category;
    private boolean upDl;

    public CategoryChange(Category category, boolean upDl) {
        setContentPane(contentPane);
        setSize(new Dimension(500,300));
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.upDl = upDl;
        this.category = category;
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
        if(category != null) {
            id.setText(Integer.toString(category.getId()));
            name.setText(category.getName());
            description.setText(category.getDescription());
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
                    name.getText() != null &&
                    description.getText() != null
                    ) {
                if(strToInt(id.getText()) != 0) {
                    category.setId(strToInt(id.getText()));
                } else {
                    JOptionPane.showMessageDialog(null, "Введите число!");
                    id.setText(Integer.toString(category.getId()));
                }
                category.setName(name.getText());
                category.setDescription(description.getText());
                if(upDl) Factory.getCategoryService().update(category);
                else Factory.getCategoryService().add(category);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Скорее всего, вы ввели существующий id");
        }
    }

    public Category getCategory() {
        return category;
    }
}
