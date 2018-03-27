package gui;

import model.User;
import service.UserService;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Enter extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField Login;
    private JPasswordField Password;
    private JButton reg;
    private JButton Enter;

    public Enter() {
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        reg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reg registr = new Reg();
                registr.setVisible(true);
            }
        });
    }

    private void onOK() {
        if(Login.getText() != null && Password.getText() != null) {
            try {
                UserService userService = new UserService();
                User user = userService.getByEnter(Login.getText(), Password.getText());
                if(user != null) {
                    MainWindow mainWindow = new MainWindow(user);
                    mainWindow.pack();
                    mainWindow.setVisible(true);

                    dispose();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
