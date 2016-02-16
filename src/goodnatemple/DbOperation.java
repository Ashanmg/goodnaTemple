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
    
    
    
}
