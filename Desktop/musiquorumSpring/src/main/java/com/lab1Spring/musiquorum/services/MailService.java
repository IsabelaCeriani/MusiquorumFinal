package com.lab1Spring.musiquorum.services;

import java.io.IOException;

import java.util.Date;
import java.util.Properties;

import com.lab1Spring.musiquorum.models.Assignment;
import com.lab1Spring.musiquorum.models.Course;
import org.springframework.stereotype.Service;


import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;

@Service
public class MailService {



    public void sendAssignmentEmail(String recipientEmail, Assignment assignment) throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("musiquorum.notify@gmail.com", "musiquorum.notify1");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("musiquorum.notify@gmail.com", false));
//
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        msg.setSubject("You've got a new assignment" );
        msg.setContent(
                "<html><body style=\"background-color: #00bfff;\">" +
            "<h1>You've got a new assignment for class " + assignment.getClasss().getClassName() + "</h1>",
                        "text/html");
//        msg.setText("You've got a new Assignment for class " + assignment.getClasss().getClassName());
        msg.setSentDate(new Date());

        //comentar la imagen
//        MimeBodyPart messageBodyPart = new MimeBodyPart();
//        messageBodyPart.setContent("You've been invited to an event!", "text/html");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messageBodyPart);
//        MimeBodyPart attachPart = new MimeBodyPart();
//
//        attachPart.attachFile("/Users/isabelaceriani/Desktop/Projects/EventIt/EventItRepo/photo.jpeg");
//        multipart.addBodyPart(attachPart);
//        msg.setContent(multipart);
        Transport.send(msg);
    }


    public void homeworkHandedInMail(String recipientEmail, Assignment assignment) throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("musiquorum.notify@gmail.com", "musiquorum.notify1");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("musiquorum.notify@gmail.com", false));
//
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        msg.setSubject("Task delivered" );
        msg.setText("Your submission for assignment " + assignment.getClasss().getClassName() + " has been succesfully delivered");

//        msg.setText("You've got a new Assignment for class " + assignment.getClasss().getClassName());
        msg.setSentDate(new Date());

        Transport.send(msg);
    }
    public void enrolledToCourseMail(String recipientEmail, Course course) throws MessagingException, IOException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("musiquorum.notify@gmail.com", "musiquorum.notify1");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("musiquorum.notify@gmail.com", false));
//
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
        msg.setSubject("Succesfully enrolled" );
        msg.setContent(
                "<html><body style=\"background-color: #00bfff;\">" +
                        "<h1>You've succesfully enrolled to course " + course.getName() + "</h1>",
                "text/html");

//        msg.setText("You've got a new Assignment for class " + assignment.getClasss().getClassName());
        msg.setSentDate(new Date());

        Transport.send(msg);
    }



}
