package com.hotel.fresnel.service;

import com.hotel.fresnel.model.Validation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {

    JavaMailSender javaMailSender;
    public void envoyer(Validation validation) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@fresnel.hotel");
        message.setTo(validation.getUser().getEmail());
        message.setSubject("Votre code d'activation");

        String texte = String.format(
                "Bonjour %s, <br /> Votre code d'action est %s; A bientôt",
                validation.getUser().getFirstName(),
                validation.getCode()
        );
        message.setText(texte);

        javaMailSender.send(message);
    }

    public void sendSimpleEmail(String toEmail,
                                String subject,
                                String body
    ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fromemail@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        javaMailSender.send(message);
        System.out.println("Mail Send...");


    }

    public void envoyerCodeReservation(String email, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@fresnel.hotel");
        message.setTo(email);
        message.setSubject("Votre code d'activation");

        String texte = String.format(
                "Bonjour ! Votre code de reservation est %s; A bientôt",
                //validation.getUser().getFirstName(),
                code.toString()
        );
        message.setText(texte);

        javaMailSender.send(message);
    }

}
