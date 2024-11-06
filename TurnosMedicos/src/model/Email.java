package model;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.persistence.FileHandler;

public class Email {
	private String asunto;
	private String destinatario;
	private String mensaje;
	private LocalDate fechaCreacion;
	
	Properties properties;
	
	
	public String getAsunto() {
		return asunto;
	}



	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}



	public String getDestinatario() {
		return destinatario;
	}



	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Email(String asunto, String destinatario, String mensaje) {
		this.asunto = asunto;
		this.destinatario = destinatario;
		this.mensaje = mensaje;
		this.fechaCreacion = LocalDate.now();
		
		properties = new Properties();
	}

	public boolean EnviarMail()
	{
		String from = "";
		String host = "";
		String claveEmail = "";
		
		try{
			properties = FileHandler.loadProperties("config.properties");
            // Leer propiedades
            from = properties.getProperty("email.from");
            host = properties.getProperty("email.host");
            claveEmail = properties.getProperty("email.pwd");
            
            Properties props = System.getProperties();
		    props.put("mail.smtp.host", host);  //El servidor SMTP de Google
		    props.put("mail.smtp.user", from);
		    props.put("mail.smtp.clave", claveEmail);    //La clave de la cuenta
		    props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
		    props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
		    props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
	
		    Session session = Session.getDefaultInstance(props);
		    MimeMessage message = new MimeMessage(session);
	    
	        message.setFrom(new InternetAddress(from));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));  
	        message.setSubject(asunto);
	        message.setText(mensaje);
	        Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", from, claveEmail);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        return true;
	    }
	    catch (MessagingException me) {
	        me.printStackTrace();  
	        return false;
	    }
	}
}
