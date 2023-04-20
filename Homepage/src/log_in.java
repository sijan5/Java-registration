import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class log_in extends JFrame {
    private JPanel Mainpanel;
    private JTextField tfemail;
    private JTextField tfpassword;
    private JButton btncancel;
    private JButton btnregister;


    log_in() {
        // adding properties of a frame
        add(Mainpanel);
        setTitle("Login Here");
        setBounds(550, 150, 400, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Home home=new Home();
            }
        });
        btnregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserLogin();

            }
        });
    }

    private void UserLogin() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/registrationdatabase", "root", "");
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM registrationtable WHERE email=? AND password=?")) {
                statement.setString(1, tfemail.getText());
                statement.setString(2, tfpassword.getText());
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Email or password incorrect", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Login successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection did not establish", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}