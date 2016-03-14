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
import java.sql.SQLException;
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
    static String UserName;
    static String Password;
   
    
    /*
    addDenotee method fill DB using data that customer entered
    from given addNewDenotee interface.
    to create connection we need to use try catch block because
    if there is a run time error catch the exception from that block.
    ........
    Denotee type object called 'd' passed as parameter to set that
    input values.
    
    */
    public boolean addDevotee(Denotee d) {
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
    
    /*
    From this method we passed denotee class object and update details 
    according to the change.
    
    */
    
    public boolean updateDevoteeDetails(Denotee dv){
        
        int contct =Integer.parseInt(dv.getContact());
        int ID =Integer.parseInt(dv.getDenoteeID());  
        try {
            con=(Connection) DriverManager.getConnection(url, username, password);
            String query="UPDATE denoteedetail SET firstname='"+dv.getFirstname()+"',lastname='"+dv.getLastname()+"',address='"+dv.getAddress()+"',d_type='"+dv.getType()+"',date='"+dv.getDate()+"',contact='"+contct+"' WHERE denotee_ID='"+ID+"'";
            pst = (PreparedStatement) con.prepareStatement(query);
            
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
    From this method we passed denotee class object and deleate raw by raw
    details that user slected as his/her wish.
    according to the change.
    
    */
    
    
    public boolean deleteDevoteeDetails(Denotee dv){
        
        int ID =Integer.parseInt(dv.getDenoteeID());
        try {
            con=(Connection) DriverManager.getConnection(url, username, password);
            String query="DELETE FROM denoteedetail WHERE denotee_ID='"+ID+"'";
            pst = (PreparedStatement) con.prepareStatement(query);
            
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
    here We check matching of entered details according 
    to the database. So we passed given username and password
    from user and using those details check that they are valid 
    and match....
    then return int according to that happens.
    .......
    */
    
    int checkUsernamePassword(String username,String password){
        try {
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT username,password FROM user";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            while(rs.next()){
                if(rs.getString(1).equals(username) & rs.getString(2).equals(password)){
                    UserName=username;
                    Password=password;
                    return 1;
                }
            }return 0;
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return 2;
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
    }
    
    
    /****************************************************************************************************************/
    /****************************************************************************************************************/
    /****************************************************************************************************************/
    
    /*
    From here......
    We wirte methods for connect details with sending email part
    of the project...
    These method given below for get and send email to the devotee who 
    should be informed for armsgiving or special ocation.
    .................
    .................
    */
    
    
    // this method return an array which is containing userID username, password and email.
    // this email method is used for login the system.
    
    String[] getEmailInfo(String username1){
        
        
         String[] Array=new String[5];
        try {
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT user_ID,username,password,user_Email  FROM user WHERE username='"+username1+"'";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            
            if(rs.next()){
                
                Array[0] = rs.getString(1);
                Array[1] = rs.getString(2);
                Array[2] = rs.getString(3);
                Array[3] = rs.getString(4);
                
            };
            
            return Array;
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
            return  null;
        }finally {
            try {
                if (pst != null) {
                    pst.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }     
        
    }
    
    
    String[] getUser(){
        try {
            String[] Array = new String[100];
            con=DriverManager.getConnection(url, username, password);
            String quary="SELECT username FROM user ";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            int i=0;
            while(rs.next()){
                Array[i]=(rs.getString(1));
                i+=1;
            }
            return Array;
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
        finally {

            try {
                if (pst != null) {
                    pst.close();
                }
                
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }   
    }
    
    
    int SerachUsername(String username){
        String arr[]=getUser();
        int check=0;
        for(int i=0;i<arr.length;i++)
        {
            if(username.equals(arr[i])){
                check=1;
                break;
            }
            else{
                check=0;
            }
        }
        return check;
        
    }
    
    ResultSet getAllEmailAddress(){
        try {
            con=DriverManager.getConnection(url, this.username, this.password);
            String quary="SELECT email FROM denoteedetail";
            pst=(PreparedStatement) con.prepareStatement(quary);
            rs=pst.executeQuery();
            System.out.println(rs);
            return rs;
            
        } catch (Exception e) {
            System.out.println(e);
            return  null;
        }
    }
    
}
