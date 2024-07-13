package employee.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    Main(){
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("icons/home.jpg"));
        Image img = img1.getImage().getScaledInstance(1170,650,Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(img);
        JLabel jl1 = new JLabel(img2);
        jl1.setBounds(0,0,1170,650);
        add(jl1);

        JLabel heading = new JLabel("Employee Management System");
        heading.setBounds(360,155,400,40);
        heading.setFont(new Font("Raleway",Font.BOLD,25));
        jl1.add(heading);

        JButton add = new JButton("Add Employee");
        add.setBounds(335,270,150,40);
        add.setForeground(Color.white);
        add.setBackground(Color.black);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    if(e.getSource()==add){
        new AddEmployee();
    }
            }
        });
        jl1.add(add);



        JButton view = new JButton("View Employee");
        view.setBounds(565,270,150,40);
        view.setForeground(Color.white);
        view.setBackground(Color.black);
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    if(e.getSource()==view){
        new ViewEmployee();
    }
            }
        });
        jl1.add(view);


        JButton remove = new JButton("Remove Employee");
        remove.setBounds(440,370,150,40);
        remove.setForeground(Color.white);
        remove.setBackground(Color.black);
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==remove){
                    new RemoveEmployee();
                }
            }
        });
        jl1.add(remove);



        setTitle("Home");
        setLayout(null);
        setLocation(170,100);
        setVisible(true);
        setSize(1170,650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Main();
    }
}
