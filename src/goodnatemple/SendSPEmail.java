/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goodnatemple;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ashanmadushanka
 */
public class SendSPEmail {
    
    
    public SendSPEmail(String email, String title , String msg){
        
        final String user ="gamadushanka@gmail.com";
        final String pass ="81056593";
   
   
       Properties prop=new Properties();
       
       prop.put("mail.smtp.ssl.trust","smtp.gmail.com");
       prop.put("mail.smtp.auth",true);
       prop.put("mail.smtp.starttls.enable",true);
       prop.put("mail.smtp.host","smtp.gmail.com");
       prop.put("mail.smtp.port","587");
       
       Session session =Session.getInstance(prop, new javax.mail.Authenticator() 
       {
          
          protected javax.mail.PasswordAuthentication getPasswordAuthentication()
           {
               return new javax.mail.PasswordAuthentication(user, pass);
           }
       });
       
       try{
           Message message =new MimeMessage(session);
           message.setFrom(new InternetAddress(user));
           message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
           message.setSubject("Activity Management System-Special Events");
           String htmlText = "<H3>"+title+"</H3><h4>" + msg + "</h4><br>";
           message.setContent(htmlText, "text/html");
           Transport.send(message);
           
           System.out.println("message sent");
           
       }catch(Exception e){
           System.out.println(e); 
           System.out.println(" found the exception !!!!");
       }
    
    
    }
    
}
