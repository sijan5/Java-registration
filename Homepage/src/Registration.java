//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class Registration extends JDialog {
//    private JPanel Mainpanel;
//    private JLabel icon;
//    private JTextField tfname;
//    private JTextField tffathername;
//    private JTextField tfmothername;
//    private JTextField tfaddress;
//    private JTextField tfcontact;
//    private JPasswordField tfpassword;
//    private JPasswordField tfconfirmpassword;
//    private JButton btnregester;
//    private JComboBox cbdate;
//    private JComboBox cbmonth;
//    private JComboBox cbyear;
//    private JButton btncancel;
//
//    public Registration(JFrame parent) {
//        super(parent);
//        setSize(500, 500);
//        setTitle("This is Regestrationform form");
//
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        setVisible(true);
//
//        //action listener to cancel and regester
//        btncancel.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
//        btnregester.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                registeruser();//calling construct
//
//
//            }
//        });
//    }
//
//    public void createUIComponents() {
//
//    }
//
//    private void registeruser() {
//        String name = tfname.getText();
//        String contact = tfcontact.getText();
//        String address = tfaddress.getText();
//        String fathersname = tffathername.getText();
//        String mothersname = tfmothername.getText();
//        String password = String.valueOf(tfpassword.getPassword());
//        String confirmpassword = String.valueOf(tfconfirmpassword.getPassword());
//        if (name.isEmpty() || contact.isEmpty() || address.isEmpty() || fathersname.isEmpty() || mothersname.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
//
//
//            JOptionPane.showMessageDialog(this,
//                    "please Enter all the fields", "Try again", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        //checking password is equal or not
//        if (!password.equals(confirmpassword)) {
//            JOptionPane.showMessageDialog(
//                    null, "Password didnot match", "try agein", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        user = (User) addUserToDatabase(name, contact, address, fathersname, mothersname, password, confirmpassword);
//        //if the user is null it will dispose of close
//        if (null != user) {
//            dispose();
//
//        }
//    }
//
//    public User user;
//
//    private Object addUserToDatabase(String name, String contact, String address, String fathersname, String mothersname, String password, String confirmpassword) {
//        if (user == null) ;
//        dispose();
//        final String DB_Url = "localhost/sijan?ServerTimeZone=UTC";
//        final String USERNAME = "root";
//        final String PASSWORD = "";
//        try {
//            Connection connection = DriverManager.getConnection(DB_Url, USERNAME, PASSWORD);
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null,"Connection could not established sorry!!","sorry",JOptionPane.ERROR_MESSAGE);
//        }
//        return user;
//    }
//    public static void main(String[] args) {
//      Registration registration=new Registration(null);
//    }
//
//    public static class User {
//       public String name;
//        public String contact;
//         public    String address;
//        public String fathersname;
//           public String mothersname;
//       public String password;
//        public String confirmpassword;
//    }
//}
//
//
//
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registration extends JFrame {

    private JPanel mainPanel;
    private JLabel icon;
    private JTextField tfname;
    private JTextField tffathername;
    private JTextField tfmothername;
    private JTextField tfaddress;
    private JTextField tfcontact;
    private JPasswordField tfpassword;
    private JPasswordField tfconfirmpassword;
    private JButton btnregister;
    private JComboBox<String> cbdate;
    private JComboBox<String> cbmonth;
    private JComboBox<String> cbyear;
    private JButton btncancel;
    private JPanel Mainpanel;
    private JButton btnregester;

    public Registration() {
        setSize(500, 500);
        setTitle("This is Registration form");
        setContentPane(mainPanel);
        setVisible(true);


        btncancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home=new Home();
                dispose();


            }
        });
        btnregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }
    public void createUIComponents() {
        // Leave this method empty
    }

    private void registerUser() {
        //fetching from the textfield
        String name = tfname.getText();
        String contact = tfcontact.getText();
        String address = tfaddress.getText();
        String fathersname = tffathername.getText();
        String mothersname = tfmothername.getText();
        String password = String.valueOf(tfpassword.getPassword());
        String confirmpassword = String.valueOf(tfconfirmpassword.getPassword());

        if (name.isEmpty() || contact.isEmpty() || address.isEmpty() || fathersname.isEmpty() || mothersname.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all the fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // checking password is equal or not
        if (!password.equals(confirmpassword)) {
            JOptionPane.showMessageDialog(
                    null, "Passwords did not match", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

//to add to the database
        User user = new User(name, contact, address, fathersname, mothersname, password);
        addUserToDatabase(user);

    }
    private void addUserToDatabase(User user) {
        final String DB_URL = "jdbc:mysql://localhost/sijan?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            String sql = "INSERT INTO users (name, contact, address, fathersname, mothersname, password) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.name);
            statement.setString(2, user.contact);
            statement.setString(3, user.address);
            statement.setString(4, user.fathersname);
            statement.setString(5, user.mothersname);
            statement.setString(6, user.password);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null, "Registration successful", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static class User {
        public String name;
        public String contact;
        public String address;
        public String fathersname;
        public String mothersname;
        public String password;

        public User(String name, String contact, String address, String fathersname, String mothersname, String password) {
            this.name = name;
            this.contact = contact;
            this.address = address;
            this.fathersname = fathersname;
            this.mothersname = mothersname;
            this.password = password;
        }
    }
}