package com.lias.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtility {

    // Paramètres SMTP (Exemple avec un compte Gmail, pensez à activer "l'accès aux applications moins sécurisées" ou générer un "Mot de passe d'application")
    private static final String HOST = "smtp.gmail.com";
    private static final String PORT = "587";
    private static final String SENDER_EMAIL = "mohammed.saissi04@gmail.com"; // Votre e-mail de labo
    private static final String SENDER_PASSWORD = "zmwp depr czqq onng\r\n"; // Mot de passe d'application

    public static void sendTemporaryPassword(String recipientEmail, String recipientName, String rawPassword) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", HOST);
        properties.put("mail.smtp.port", PORT);

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Laboratoire LIAS - Validation de votre adhésion");
            
            String htmlContent = "<h3>Bonjour " + recipientName + ",</h3>"
                    + "<p>Félicitations ! Votre demande d'adhésion au laboratoire <b>LIAS</b> a été acceptée par la direction.</p>"
                    + "<p>Voici vos identifiants temporaires pour vous connecter à la plateforme :</p>"
                    + "<ul>"
                    + "  <li><b>Email :</b> " + recipientEmail + "</li>"
                    + "  <li><b>Mot de passe temporaire :</b> <code style='background:#f1f1f1; padding:4px;'>" + rawPassword + "</code></li>"
                    + "</ul>"
                    + "<p>Nous vous invitons à vous connecter et à modifier votre mot de passe dès que possible.</p>"
                    + "<br><p>Cordialement,<br><b>L'équipe de direction du LIAS</b></p>";

            message.setContent(htmlContent, "text/html; charset=utf-8");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}