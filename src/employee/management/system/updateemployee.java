package employee.management.system;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class updateemployee extends JFrame {

    JTextField  tfname, tphone, taddress, tsalary,tedu,temail;
    JLabel tempid;
    JButton addb , back;
    String number;
    public updateemployee(String number){
        this.number = number;

        getContentPane().setBackground(new Color(163,255,188));

        JLabel heading = new JLabel("Update Employee Details");
        heading.setBounds(320,30,500,50);
        heading.setFont(new Font("serif",Font.BOLD,25));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(50,150,150,30);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(name);

       JLabel tname = new JLabel();
        tname.setBounds(200,150,150,30);
        tname.setBackground(new Color(177,252,197));
        add(tname);


        JLabel fathername = new JLabel("Father's Name");
        fathername.setBounds(400,150,150,30);
        fathername.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(fathername);

        tfname = new JTextField();
        tfname.setBounds(600,150,150,30);
        tfname.setBackground(new Color(177,252,197));
        add(tfname);

        JLabel dob = new JLabel("Date of Birth");
        dob.setBounds(50,200,150,30);
        dob.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(dob);

        JLabel tdob = new JLabel();
        tdob.setBounds(200,200,150,30);
    tdob.setFont(new Font("Tahoma",Font.BOLD,20));
        add(tdob);

        JLabel salary = new JLabel("Salary");
        salary.setBounds(400,200,150,30);
        salary.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(salary);

        tsalary = new JTextField();
        tsalary.setBounds(600,200,150,30);
        tsalary.setBackground(new Color(177,252,197));
        add(tsalary);


        JLabel address = new JLabel("Address");
        address.setBounds(50,250,150,30);
        address.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(address);

        taddress = new JTextField();
        taddress.setBounds(200,250,150,30);
        taddress.setBackground(new Color(177,252,197));
        add(taddress);

        JLabel phone = new JLabel("Phone No.");
        phone.setBounds(400,250,150,30);
        phone.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(phone);

        tphone = new JTextField();
        tphone.setBounds(600,250,150,30);
        tphone.setBackground(new Color(177,252,197));
        add(tphone);

        JLabel email = new JLabel("Email");
        email.setBounds(50,300,150,30);
        email.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(email);

        temail = new JTextField();
        temail.setBounds(200,300,150,30);
        temail.setBackground(new Color(177,252,197));
        add(temail);


        JLabel Aadhar = new JLabel("Aadhar No.");
        Aadhar.setBounds(400,300,150,30);
        Aadhar.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(Aadhar);

        JLabel taadhar = new JLabel();
        taadhar.setBounds(600,300,150,30);
        taadhar.setBackground(new Color(177,252,197));
        add(taadhar);

        JLabel education = new JLabel("Education");
        education.setBounds(50,350,150,30);
        education.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(education);

         tedu = new JTextField();
        tedu.setBounds(200,350,150,30);
        tedu.setBackground(new Color(177,252,197));
        add(tedu);

        JLabel Emp_id = new JLabel("Emp_id");
        Emp_id.setBounds(400,350,150,30);
        Emp_id.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(Emp_id);

        tempid = new JLabel();
        tempid.setBounds(600,350,150,30);
        tempid.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        tempid.setForeground(Color.red);
        add(tempid);

        try {
            databaseconnection databaseconnection = new databaseconnection();
            String q1 = "select * from emp_table_1 where empid='"+number+"' ";
            ResultSet rs = databaseconnection.statement.executeQuery(q1);
            while (rs.next()){
                tname.setText(rs.getString("name"));
                tfname.setText(rs.getString("fname"));
                tdob.setText(rs.getString("dob"));
                taadhar.setText(rs.getString("adhar"));
                taddress.setText(rs.getString("address"));
                tsalary.setText(rs.getString("salary"));
                tedu.setText(rs.getString("edu"));
                tphone.setText(rs.getString("phone"));
                temail.setText(rs.getString("email"));
                tempid.setText(rs.getString("empid"));
            }
        }catch (Exception ex3){
            ex3.printStackTrace();
        }


        addb = new JButton("UPDATE");
        addb.setBounds(450,550,150,30);
        addb.setBackground(Color.black);
        addb.setForeground(Color.white);
        addb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==addb){
                    String fname=tfname.getText();
                    String salary = tsalary.getText();
                    String address = taddress.getText();
                    String phone = tphone.getText();
                    String email = temail.getText();
                    String edu = tedu.getText();

                    try {
                        databaseconnection databaseconnection = new databaseconnection();
                        String query_2="update emp_table_1 set fname = ? , salary = ? ,address = ? , phone = ? , email = ?, edu=? where empid = ?";
                        PreparedStatement preparedStatement = databaseconnection.connection.prepareStatement(query_2);
                        preparedStatement.setString(1,fname);
                        preparedStatement.setString(2,salary);
                        preparedStatement.setString(3,address);
                        preparedStatement.setString(4,phone);
                        preparedStatement.setString(5,email);
                        preparedStatement.setString(6,edu);
                        preparedStatement.setString(7,number);

                        int rowsUpdated = preparedStatement.executeUpdate();

                        // Check the result and provide feedback
                        if (rowsUpdated > 0) {
                            JOptionPane.showMessageDialog(null, "Details Updated Successfully");
                        } else {
                            JOptionPane.showMessageDialog(null, "Update Failed. Employee not found.");
                        }

                    }catch (Exception ex4){
                        ex4.printStackTrace();
                    }

                }

            }
        });
        add(addb);

        back = new JButton("BACK");
        back.setBounds(250,550,150,30);
        back.setBackground(Color.black);
        back.setForeground(Color.white);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==back){
                    setVisible(false);
                    new ViewEmployee();
                }
            }
        });
        add(back);

        setSize(900,700);
        setTitle("Add Employee");
        setLayout(null);
        setLocation(300,50);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new updateemployee("");
    }
}
