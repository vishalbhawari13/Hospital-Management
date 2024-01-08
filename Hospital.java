package Hospital;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Hospital {
    private JPanel Main;
    private JTextField txtName;
    private JTextField txtAge;
    private JTextField txtGender;
    private JButton button1;
    private JTable table1;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField txtid;
    private JScrollPane table_1;

    static final String driver="com.mysql.cj.jdbc.Driver";
    static final String url ="jdbc:mysql://localhost:3306/Hospital";
    static final String user="root";
    static final String pass="Root@123";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hospital");
        frame.setContentPane(new Hospital().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
Connection connection;
    PreparedStatement pst;
    public void connect(){
        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(url,user,pass);
            System.out.println("database connection is done");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    void table_load()
    {
        try {
            pst=connection.prepareStatement("select * from hospital");
            ResultSet rs= pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }





    public Hospital() {
        connect();
        table_load();
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String patientname,age,gender;

                patientname= txtName.getText();
                age= txtAge.getText();
                gender= txtGender.getText();



                try {
                    pst= connection.prepareStatement("insert into hospital(patient_name,age,gender)values (?,?,?)");
                    pst.setString(1,patientname);
                    pst.setString(2,age);
                    pst.setString(3,gender);
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Record Addeddd!!!!!");
                    txtName.setText("");
                    txtAge.setText("");
                    txtGender.setText("");
                    txtName.requestFocus();


                }
                catch (SQLException e1){
                    e1.printStackTrace();
                }

            }
        });
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pname,age,gender;

                pname=txtName.getText();
                age= txtAge.getText();
                gender= txtGender.getText();
               /* try {
                    String txtidText = txtid.getText();
                    pst = connection.prepareStatement("select patient_name,age,gender where id=?");
                    pst.setString(1,txtidText);
                    ResultSet rs= pst.executeQuery();

                    if (rs.next()==true)
                    {
                        String patient_name= rs.getString(1);
                        String  age= rs.getString(2);
                        String  gender = rs.getString(3);
                    }
                }
                catch ()
                {

                }*/
            }
        });
    }
}
