package gui;

import model.User;
import service.Factory;
import service.UserService;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Vector;

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
                System.exit(0);
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
                User user = Factory.getUserService().getByEnter(Login.getText(), Password.getText());
                if(user != null) {
                    if(user.getRole().getId() == 1) {
                        Object[] possibilities = {"Admin panel", "User panel"};
                        String s = (String)JOptionPane.showInputDialog(
                                null,
                                "Выберите меню:",
                                "Customized Dialog",
                                JOptionPane.PLAIN_MESSAGE,
                                null,
                                possibilities,
                                "Admin panel");

                        if ((s != null) && (s.length() > 0)) {
                            if(s.equals("Admin panel")) {
                                adminMenu(user);
                            } else {
                                mainMenu(user);
                            }
                        }
                    } else {
                        mainMenu(user);
                    }
                    dispose();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void adminMenu(User user) {
        AdminMainWindow adminMainWindow = new AdminMainWindow(user);
        adminMainWindow.pack();
        adminMainWindow.setVisible(true);
    }

    private void mainMenu(User user) {
        MainWindow mainWindow = new MainWindow(user);
        mainWindow.pack();
        mainWindow.setVisible(true);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
