import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame{
    private JButton btnregister;
    private JButton btnlogin;
    private JPanel Mainpanel;
    private JButton btnback;

    // creating a constructor
    Home(){
        add(Mainpanel);
        setTitle("Welcome to Homepage.");
        setVisible(true);
        setBounds(600,150,500,500);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setFocusable(false);

        btnlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                log_in logIn=new log_in();


            }
        });
        btnregister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                newRegestration regestration=new newRegestration();
            }
        });
    }


    public static class Main {
        public static void main(String[] args) {
            Home home=new Home();
        }
    }
}
