package klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailManager {

    @Autowired
    private JavaMailSender mailSender;

    private static final String FROM_EMAIL = "ktejaswanth05@gmail.com";

    public String sendEmail(String toEmail, String subject, String message) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(FROM_EMAIL); // Use configured email
            mail.setTo(toEmail);
            mail.setSubject(subject);
            mail.setText(message);
            mailSender.send(mail);
            return "200::Email sent successfully";
        } catch (Exception e) {
            e.printStackTrace(); // Optional: log error to console
            return "401::Failed to send email: " + e.getMessage();
        }
    }

    public String sendContactEmail(String name, String email, String message) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(FROM_EMAIL); // same email as configured
            mail.setTo(FROM_EMAIL);   // send to yourself
            mail.setSubject("New Contact Message from " + name);
            mail.setText(
                "Contact Name: " + name + "\n" +
                "Contact Email: " + email + "\n\n" +
                "Message:\n" + message
            );
            mailSender.send(mail);
            return "200::Contact message sent successfully!";
        } catch (Exception e) {
            e.printStackTrace(); // Optional: log error
            return "401::Failed to send contact message: " + e.getMessage();
        }
    }

    public String sendFeedback(String name, String userEmail, String feedbackMessage) {
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(FROM_EMAIL); // Sender address
            mail.setTo(FROM_EMAIL);   // Send to yourself
            mail.setSubject("New Feedback from " + name);
            mail.setText(
                "Feedback from:\n" +
                "Name: " + name + "\n" +
                "Email: " + userEmail + "\n\n" +
                "Message:\n" + feedbackMessage
            );
            mailSender.send(mail);
            return "200::Feedback email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "401::Failed to send feedback email: " + e.getMessage();
        }
    }
  

    public void sendBookingConfirmation(String toEmail, String userName, String date, String time, String service, double total) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setCc("ktejasanth06@gmail.com");
        message.setSubject("Service Booking Confirmation");
        message.setText("Hi " + userName + ",\n\nYour service has been booked for " + date + " at " + time +
                ".\nService: " + service + "\nTotal: ₹" + total + "\n\nThank you!");

        mailSender.send(message);
    }


	
}