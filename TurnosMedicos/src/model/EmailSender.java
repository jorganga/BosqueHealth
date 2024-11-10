package model;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Clase que envía correos electrónicos usando SMTP.
 */
public class EmailSender {
	/**
	 * Envía un correo electrónico usando Gmail.
	 */
	public void EnviarMailGmail(String destinatario, String asunto, String cuerpo) {
		// La dirección de correo de envío
		String remitente = "jorge.enrique.angulo@gmail.com";
		// La clave de aplicación obtenida según se explica aquí:
		String claveemail = "gzmpngnazhodtrrw";

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", claveemail); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario)); // Se podrían añadir
																								// varios de la misma
																								// manera
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, claveemail);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace(); // Si se produce un error
		}
	}

	/**
	 * Envía un correo de prueba con configuraciones predeterminadas.
	 */
	public void EnviarMail() {

		String to = "jorge.enrique.angulo@gmail.com"; // sender email
		String from = "sender@jea.com"; // receiver email
		String host = "127.0.0.1"; // mail server host

		String claveemail = "1234567890123456";

		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);

		Session session = Session.getDefaultInstance(properties); // default session
		// gzmp ngna zhod trrw
		try {
			MimeMessage message = new MimeMessage(session); // email message

			message.setFrom(new InternetAddress(from)); // setting header fields

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			message.setSubject("Test Mail from Java Program"); // subject line

			// actual mail body
			message.setText("You can send mail from Java program by using mail API, but you need"
					+ "couple of more JAR files e.g. smtp.jar and activation.jar");

			// Send message
			Transport.send(message);
			System.out.println("Email Sent successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

}
