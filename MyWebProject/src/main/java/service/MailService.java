package service;

import org.apache.log4j.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {

    private static final Logger logger = Logger.getLogger(MailService.class);

    public void sendMail(String userMail, Long code) {
        final String username = "MyWebProjectMA@gmail.com";
        final String password = "1MyWebProjectMA1";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("MyWebProjectMA@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(userMail)
            );
            message.setSubject("Одноразовый код подтверждения покупки");
            message.setText("Ваш код для покупки: " + code);

            Transport.send(message);

            logger.debug("Mail send - done!");
        } catch (MessagingException e) {
            logger.error("Can't send email", e);
        }
    }
}
