package com.si.apirest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.si.apirest.entity.Tenant;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailService {
    
    @Autowired
    private final JavaMailSender javaMailSender;
    
    public void sendMail(Tenant tenant) {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message);

        String emailUser = tenant.getEmail();

        try {
            helper.setTo(emailUser);
            helper.setSubject("Este es un Email.");
            helper.setText("Hola que tal.");

            javaMailSender.send(message);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendEmail(String username, String subject, String body) {
        try {
            // Crear el mensaje MIME
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true); // true para soporte de HTML
    
            // Configurar los detalles del mensaje
            helper.setTo(username); // Correo del destinatario
            helper.setSubject(subject); // Asunto del correo
            helper.setText(body, true); // Cuerpo del mensaje (HTML activado)
    
            // Enviar el correo
            javaMailSender.send(message);
            System.out.println("Correo enviado a: " + username);
        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo: " + e.getMessage());
            throw new RuntimeException("Fallo al enviar el correo", e);
        }
    }
    

}
