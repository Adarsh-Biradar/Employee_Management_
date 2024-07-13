package employee.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ViewEmployee extends JFrame {
    Choice choiceemp;
    JTable table;
    JButton search , print , update ,back;
    public ViewEmployee(){

        getContentPane().setBackground(new Color(255,131,122));

        JLabel search1 = new JLabel("Search by employee id");
        search1.setBounds(20,20,150,20);
        add(search1);

        choiceemp = new Choice();
        choiceemp.setBounds(180,20,150,20);
        add(choiceemp);

        try {
            databaseconnection databaseconnection = new databaseconnection();
            String query_2="select * from emp_table_1";
            PreparedStatement preparedStatement = databaseconnection.connection.prepareStatement(query_2);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                choiceemp.add(resultSet.getString("empid"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        table = new JTable();
        try {
            databaseconnection databaseconnection = new databaseconnection();
            String query_2="select * from emp_table_1";
            PreparedStatement preparedStatement = databaseconnection.connection.prepareStatement(query_2);
            ResultSet resultSet = preparedStatement.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(resultSet));


        }catch (Exception ex)
        {
            ex.printStackTrace();
        }

        JScrollPane jp = new JScrollPane(table);
        jp.setBounds(0,100,900,600);
        add(jp);

       search = new JButton("Search");
       search.setBounds(20,70,80,20);
       search.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                if(e.getSource()==search){
                    try {
                        databaseconnection databaseconnection = new databaseconnection();
                        String query_2="select * from emp_table_1 where empid = ?";
                        PreparedStatement preparedStatement = databaseconnection.connection.prepareStatement(query_2);
                        preparedStatement.setString(1,(String) choiceemp.getSelectedItem());
                        ResultSet resultSet = preparedStatement.executeQuery();
                      table.setModel(DbUtils.resultSetToTableModel(resultSet));

                    }catch (Exception ex1){
                        ex1.printStackTrace();
                    }
                }
           }
       });
       add(search);


        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(e.getSource()==print){
                        try {
                            table.print();
                        }catch (Exception ex2){
                            ex2.printStackTrace();
                        }
                    }
            }
        });
        add(print);



        update = new JButton("Update");
        update.setBounds(220,70,80,20);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==update) {
                    setVisible(false);
                    new updateemployee(choiceemp.getSelectedItem());
                }
            }
        });
        add(update);

        back = new JButton("Back");
        back.setBounds(320,70,80,20);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==back){
                    setVisible(false);
                    new Main();
                }
            }
        });
        add(back);


        JLabel sdone = new JLabel("Done");
        sdone.setBounds(20,20,150,20);
        add(sdone);

        setVisible(true);
        setTitle("View Employee");
        setLocation(300,100);
        setLayout(null);
        setSize(900,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
    new ViewEmployee();
    }

}
