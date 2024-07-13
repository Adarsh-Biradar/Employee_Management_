package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

public class Login extends JFrame implements ActionListener {
    JTextField usertest;
    JPasswordField userpass;
    JButton login, back;

    Login() {

        JLabel username = new JLabel("UserName:-");
        username.setBounds(40, 20, 100, 30);
        add(username);

        usertest = new JTextField();
        usertest.setBounds(150, 20, 150, 30);
        add(usertest);

        JLabel password = new JLabel("Password:-");
        password.setBounds(40, 70, 100, 30);
        add(password);
        userpass = new JPasswordField();
        userpass.setBounds(150, 70, 150, 30);
        add(userpass);

        login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.setBackground(Color.black);
        login.setForeground(Color.white);
        login.addActionListener(this::actionPerformed);
        add(login);

        back = new JButton("BACK");
        back.setBounds(150, 180, 150, 30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        ImageIcon li1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image li2 = li1.getImage().getScaledInstance(600, 400, Image.SCALE_DEFAULT);
        ImageIcon li3 = new ImageIcon(li2);
        JLabel limg = new JLabel(li3);
        limg.setBounds(350, 0, 600, 400);
        add(limg);


        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/LoginB.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel img = new JLabel(i3);
        img.setBounds(0, 0, 600, 300);
        add(img);


        JLabel temp = new JLabel("temp");
        temp.setBounds(40, 100, 100, 30);
        add(temp);

        setVisible(true);
        setSize(600, 300);
        setLocation(450, 200);
        setLayout(null);
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==login) {
            try {
                String UserName = userpass.getText();
                String UserPass = userpass.getText();

                databaseconnection databaseconnection = new databaseconnection();

                String query = "SELECT * FROM login WHERE username = ? AND password = ?";

                PreparedStatement preparedStatement = databaseconnection.connection.prepareStatement(query);
                preparedStatement.setString(1,UserName);
                preparedStatement.setString(2,UserPass);
                ResultSet resultSet = preparedStatement.executeQuery();


                if (resultSet.next()){
                    setVisible(false);
                    new Main();
                }else{
                    JOptionPane.showConfirmDialog(null,"Invalid User");
                }

            }catch (Exception E){
                E.printStackTrace();
            }

        } else {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            System.exit(90);
        }
    }
}
