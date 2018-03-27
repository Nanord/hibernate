package gui;

import model.User;
import service.Factory;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Reg extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField Login;
    private JPasswordField Password;
    private JTextField First_name;
    private JTextField Last_name;
    private JTextField Email;
    private JTextField Adres;
    private JTextField Tel;

    public Reg() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if(Login.getText() != null &&
                            Password.getText() != null &&
                            First_name.getText() != null &&
                            Last_name.getText() != null &&
                            Email.getText() != null &&
                            Adres.getText() != null &&
                            Tel.getText() != null) {
                        User user = new User();
                        user.setLogin(Login.getText());
                        user.setPassword(Password.getText());
                        user.setFirstName(First_name.getText());
                        user.setLastName(Last_name.getText());
                        user.setAdress(Adres.getText());
                        user.setTel(Tel.getText());
                        Factory.getUserService().add(user);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


                dispose();
            }
        });
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        Reg dialog = new Reg();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
