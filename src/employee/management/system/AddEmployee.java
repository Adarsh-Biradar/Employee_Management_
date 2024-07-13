package employee.management.system;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.util.Random;

public class AddEmployee extends JFrame {
    Random random = new Random();
    int number = random.nextInt(99999);


    JTextField tname, tfname, tphone, taddress, taadhar,tsalary,temail;
    JLabel tempid;
    JDateChooser date_of_birth;
    JComboBox teducation;
    JButton addb , back;


    public  AddEmployee(){

        getContentPane().setBackground(new Color(163,255,188));

        JLabel heading = new JLabel("Add Employee Details");
        heading.setBounds(320,30,500,50);
        heading.setFont(new Font("serif",Font.BOLD,25));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(50,150,150,30);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(name);

        tname = new JTextField();
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

        date_of_birth = new JDateChooser();
        date_of_birth.setBounds(200,200,150,30);
        date_of_birth.setBackground(new Color(177,252,197));
        add(date_of_birth);


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

        taadhar = new JTextField();
        taadhar.setBounds(600,300,150,30);
        taadhar.setBackground(new Color(177,252,197));
        add(taadhar);

        JLabel education = new JLabel("Education");
        education.setBounds(50,350,150,30);
        education.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(education);

        String items[]={"12TH PASS","BBA","BCOM","MSC-IT","BSC-IT","MCOM","MCA","BA","PHD"};
        teducation = new JComboBox(items);
        teducation.setBounds(200,350,150,30);
        teducation.setBackground(new Color(177,252,197));
        add(teducation);

        JLabel Emp_id = new JLabel("Emp_id");
        Emp_id.setBounds(400,350,150,30);
        Emp_id.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        add(Emp_id);

        tempid = new JLabel(""+number);
        tempid.setBounds(600,350,150,30);
        tempid.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        tempid.setForeground(Color.red);
        add(tempid);

        addb = new JButton("ADD");
        addb.setBounds(450,550,150,30);
        addb.setBackground(Color.black);
        addb.setForeground(Color.white);
        addb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==addb){


                    String name = tname.getText();
                    String fname = tfname.getText();
                    String dob = ((JTextField)date_of_birth.getDateEditor().getUiComponent()).getText();
                    String salary = tsalary.getText();
                    String address = taddress.getText();
                    String email = temail.getText();
                    //
                    String phone = tphone.getText();
                    String edu = (String) teducation.getSelectedItem();
                    String empid = tempid.getText();
                    String adhar = taadhar.getText();

                    try {
                        databaseconnection databaseconnection = new databaseconnection();
                        String query = "INSERT INTO emp_table_1(name, fname, dob, salary, address, email, phone, edu,empid,adhar) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?)";
                        PreparedStatement preparedStatement = databaseconnection.connection.prepareStatement(query);

                        preparedStatement.setString(1, name);
                        preparedStatement.setString(2, fname);
                        preparedStatement.setString(3, dob);
                        preparedStatement.setString(4, salary);
                        preparedStatement.setString(5, address);
                        preparedStatement.setString(6, email);
                        preparedStatement.setString(7, phone);
                        preparedStatement.setString(8, edu);
                        preparedStatement.setString(9, empid);
                        preparedStatement.setString(10,adhar);
                        int rowsInserted = preparedStatement.executeUpdate();
                        if (rowsInserted > 0) {
                            JOptionPane.showMessageDialog(null, "Employee added successfully!");
                        }
                        setVisible(false);
                        new Main();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
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
                new Main();
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
       new AddEmployee();
    }
}
