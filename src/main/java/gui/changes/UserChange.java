package gui.changes;

import model.User;
import service.Factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class UserChange extends JDialog {
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

    private User user;
    private boolean upDl;

    public UserChange(User user, boolean upDl) {
        setContentPane(contentPane);
        setSize(new Dimension(500,300));
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        this.upDl = upDl;
        this.user = user;
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

    private void setUserTextField() {
        if(user != null) {
            Login.setText(user.getLogin());
            Password.setText(user.getPassword());
            Last_name.setText(user.getLastName());
            Tel.setText(user.getTel());
            Adres.setText(user.getAdress());
            Email.setText(user.getEmail());
            First_name.setText(user.getFirstName());
        }
        else {
            user = new User();
        }
    }

    private void getUserTextField() {
        try {
            if(Login.getText() != null &&
                    Password.getText() != null &&
                    First_name.getText() != null &&
                    Last_name.getText() != null &&
                    Email.getText() != null &&
                    Adres.getText() != null &&
                    Tel.getText() != null) {
                if(upDl) user.setId(this.user.getId());
                user.setLogin(Login.getText());
                user.setPassword(Password.getText());
                user.setFirstName(First_name.getText());
                user.setLastName(Last_name.getText());
                user.setAdress(Adres.getText());
                user.setEmail(Email.getText());
                user.setTel(Tel.getText());

                if(upDl) Factory.getUserService().update(user);
                else Factory.getUserService().add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void onOK() {
        getUserTextField();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public User getUser() {
        return user;
    }
}
