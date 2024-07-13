package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RemoveEmployee extends JFrame {
Choice choiceempid;
JButton delete , back;

    RemoveEmployee(){

        JLabel label = new JLabel("Employee Id");
        label.setBounds(50,50,100,30);
        add(label);

        choiceempid = new Choice();
        choiceempid.setBounds(200,50,150,30);
        add(choiceempid);

        try{
            databaseconnection databaseconnection = new databaseconnection();
            String query = "select * from emp_table_1";
            PreparedStatement preparedStatement= databaseconnection.connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                choiceempid.add(resultSet.getString("empid"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        JLabel labelname = new JLabel("Name");
        labelname.setBounds(50,100,100,30);
        add(labelname);

        JLabel textname = new JLabel();
        textname.setBounds(200,100,100,30);
        add(textname);

        JLabel labelphone = new JLabel("Phone");
        labelphone.setBounds(50,150,100,30);
        add(labelphone);

        JLabel textphone = new JLabel();
        textphone.setBounds(200,150,100,30);
        add(textphone);

        JLabel labelemail = new JLabel("Email");
        labelemail.setBounds(50,200,100,30);
        add(labelemail);

        JLabel textemail = new JLabel();
        textemail.setBounds(200,200,100,30);
        add(textemail);

        try{
            databaseconnection databaseconnection = new databaseconnection();
            String query = "Select * from emp_table_1 where empid=?";
            PreparedStatement preparedStatement = databaseconnection.connection.prepareStatement(query);
            preparedStatement.setString(1,(String)choiceempid.getSelectedItem());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                textname.setText(resultSet.getString("name"));
                textphone.setText(resultSet.getString("phone"));
                textemail.setText(resultSet.getString("email"));
            }
        }catch (Exception ec2){
            ec2.printStackTrace();
        }

        choiceempid.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    databaseconnection databaseconnection = new databaseconnection();
                    String query = "Select * from emp_table_1 where empid=?";
                    PreparedStatement preparedStatement = databaseconnection.connection.prepareStatement(query);
                    preparedStatement.setString(1,(String)choiceempid.getSelectedItem());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()){
                        textname.setText(resultSet.getString("name"));
                        textphone.setText(resultSet.getString("phone"));
                        textemail.setText(resultSet.getString("email"));
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

        delete = new JButton("DELETE");
        delete.setBounds(80,300,100,30);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==delete){
                   try {
                       databaseconnection databaseconnection = new databaseconnection();
                       String query = " delete from emp_table_1 where empid=?";
                       PreparedStatement preparedStatement = databaseconnection.connection.prepareStatement(query);
                       preparedStatement.setString(1,choiceempid.getSelectedItem());
                       int rowsAffected = preparedStatement.executeUpdate();


                       if (rowsAffected > 0) {
                           JOptionPane.showMessageDialog(null, "Employee deleted successfully.");
                       } else {
                           JOptionPane.showMessageDialog(null, "Employee not found.");
                       }
                   }catch (Exception ex1){
                       ex1.printStackTrace();
                   }

                }
            }
        });
        add(delete);


        back = new JButton("BACK");
        back.setBounds(220,300,100,30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==back){
                    setVisible(false);
                    new Main();
                }
            }
        });
        add(back);

        ImageIcon imageIcon2 = new ImageIcon(ClassLoader.getSystemResource("icons/rback.png"));
        Image image1 = imageIcon2.getImage().getScaledInstance(1120,630,Image.SCALE_DEFAULT);
        ImageIcon imageIcon3 = new ImageIcon(image1);
        JLabel label2 = new JLabel(imageIcon3);
        label2.setBounds(0,0,1120,630);
        add(label2);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/delete.png"));
        Image image = imageIcon.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label1 = new JLabel(imageIcon1);
        label1.setBounds(700,20,200,200);
        add(label1);




        setVisible(true);
        setSize(1000,400);
        setLayout(null);
        setLocation(300,150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Delete Employee");
    }

    public static void main(String[] args) {
        new RemoveEmployee();
    }
}
