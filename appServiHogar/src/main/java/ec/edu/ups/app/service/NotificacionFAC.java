package ec.edu.ups.app.service;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class NotificacionFAC {

	public void enviarEmail(final String origen,final String contraseniaOrigen, String destino, String asunto, String texto) {
	       
        Properties props = new Properties();
        /** Par�metros de conex�o com servidor Hotmail */
   
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected PasswordAuthentication getPasswordAuthentication()
                         {
                               return new PasswordAuthentication(origen, contraseniaOrigen);
                         }
                    });

        /** Ativa Debug para sess�o */
        session.setDebug(true);

        try {

              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress(origen)); //Remitente

              message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(destino)); //Destinatario(s)
              message.setSubject(asunto);//Assunto
              message.setText(texto);
              /**M�todo para enviar a mensagem criada*/
              Transport.send(message);

              System.out.println("se fue!!!");

         } catch (MessagingException e) {
              throw new RuntimeException(e);
        }
  }

public static void main (String[]args) 
{
   NotificacionFAC nf = new NotificacionFAC();
   for (int i=0;i<1;i++)
   {
     nf.enviarEmail("lenin.quinonez@taurustech.ec", "@quinonez@25", "jayora@est.ups.edu.ec", "Notificacion Cuenta Fissare", "Su registro fue exitoso");
   }
}
	

}
