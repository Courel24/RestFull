package com.example.demo.service;

import com.example.demo.controller.dto.PickupPetNotificationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@AllArgsConstructor
public class NotifierService {

     public String sendNotificationToClient(PickupPetNotificationDTO pickupPetNotificationDTO){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        final String username = "mail";
        final String password = "pass";

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // Dirección de correo del remitente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(pickupPetNotificationDTO.getEmail()));
            message.setSubject("Finalización de la reserva de su mascota."); // Asunto del mensaje
            message.setText("Hola " + pickupPetNotificationDTO.getClientName() + " le notificamos que su mascota " + pickupPetNotificationDTO.getPetName() + " esta lista para ser recogida.");

            Transport.send(message);

            return "message sent successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "message not sent successfully";
        }
    }

}
