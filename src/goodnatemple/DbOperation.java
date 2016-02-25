/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goodnatemple;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author ashanmadushanka
 */
public class DbOperation {

    String url = "jdbc:mysql://localhost:3306/goodna_temple";
    String username = "root";
    String password = "";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    /*
    addDenotee method fill DB using data that customer entered
    from given addNewDenotee interface.
    to create connection we need to use try catch block because
    if there is a run time error catch the exception from that block.
    ........
    Denotee type object called 'd' passed as parameter to set that
    input values.
    
    */
    public boolean addDenotee(Denotee d) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query = "INSERT INTO denoteedetail VALUES (?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            
            pst.setString(1,d.getDenoteeID());
            pst.setString(2, d.getFirstname());
            pst.setString(3,d.getLastname());
            pst.setString(4,d.getAddress());
            pst.setString(5,d.getType());
            pst.setString(6,d.getDate());
            pst.setString(7,d.getContact());
            pst.setString(8,d.getEmail());
            
            pst.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("exception --->" + e);
            return false;
        }finally{
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    pst.close();
                }
            } catch (Exception e) {
            }
        }
    }
    
    /*
    From this get Devotee details method we are going to 
    execute query for display detail in a table acording to 
    there columns...
    this method pass resultset array to the constructor of the view details class.
    */
    
    public ResultSet getDevoteeDetails(){
        try {
            
            con=(Connection) DriverManager.getConnection(url, username, password);
            String query = "SELECT denotee_ID,firstname,lastname,address,d_type,date,contact FROM denoteedetail";
            pst = (PreparedStatement) con.prepareStatement(query);
            
            rs = pst.executeQuery();
            return rs;
            
        } catch (Exception e) {
            System.out.println("exception --->" + e);
            //JOptionPane.showMessageDialog(null, "there is a wrong connecting with DataBase");
            return null;
        }
        
    }
    
    /*
    From this filtered details method we are going to reset the table
    as user need in search table row with firstname of the devotee details.
    ..........
    So in this method sorted and only display the devotee who is call
    by name that entered that textfield.
    ..........
    From this method easy to find data.
    */
    
    public ResultSet filteredDetails(String Fname){
        try {
            con=(Connection) DriverManager.getConnection(url, username, password);
            String query = "SELECT denotee_ID,firstname,lastname,address,d_type,date,contact FROM denoteedetail WHERE firstname LIKE '%"+Fname+"%'";
            pst = (PreparedStatement) con.prepareStatement(query);
            
            rs = pst.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("exception --->" + e);
            return null;
        }
            
    }
    
    
    
    
}
