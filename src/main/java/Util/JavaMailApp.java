package Util;

/**
 * @Data: 27/04/2018
 * @Autor: m159255
 */
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailApp implements Runnable {

    @Override
    public void run() {
        System.out.println("Enviando");
        EnviarEmail();
    }

    public void EnviarEmail() {
        Properties props = new Properties();

        //Parâmetros de conexão com servidor de email
        props.put("mail.smtp.host", "mail.saneago.com.br");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("user@user.com", "senha");
            }
        });

        session.setDebug(true); //Ativa Debug para sessão

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("user@user.com")); //Remetente

            Address[] toUser = InternetAddress.parse("destinatario1@email.com,destinatario2.com.br"); //Destinatário(s)

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Asunto Email"); //Assunto
            message.setText("<html>Teste</html>", "utf-8", "html");

            Transport.send(message);//Método para enviar a mensagem criada

            System.out.println("Feito!!!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
