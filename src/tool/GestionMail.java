package tool;

import java.util.*;
import java.util.Properties;
import javax.mail.internet.MimeMessage;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

public class GestionMail {
 
	public static void smtpGmail () {
		
		try {
			// Protocole authentification mail
			String smtpHost = "smtp.gmail.com";
		    String username = "recursiveness.liberty@gmail.com";
		    String password = "btssio2019";
	
		    // Parametres d'envoie
		    String from = "recursiveness.liberty@gmail.com";
		    String to = "pro.info.lmm@gmail.com";
		    
		    String contenuMessage = "Ceci est le contenu du courriel. Pour test.";
		    String contenuObjet = "Message pour test envoie courriel.";
		    boolean sessionDebug = false;
		    
		    //
		    Properties props = new Properties();
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.smtp.host", smtpHost);
		    props.put("mail.smtp.port", "587");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.required", "true");
		    props.setProperty("mail.smtp.timeout", "1000");
		 
		    // Securite Java
		    //java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
		    
		    // Session
		    Session mailSession = Session.getDefaultInstance(props, null);
		    mailSession.setDebug(sessionDebug);
		 
		    MimeMessage message = new MimeMessage(mailSession);   
		    message.setFrom(new InternetAddress(from));
		    message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		    message.setSentDate(new Date());
		    message.setSubject(contenuObjet);
		    //message.setText(contenuMessage);
		    message.setContent("<h1>This is actual message</h1>", "text/html");
		 
		    // Connection et envoie
		    Transport tr = mailSession.getTransport("smtp");
		    tr.connect(smtpHost, username, password);
		    message.saveChanges();
		 
		    // tr.send(message);
		    /** Genere l'erreur. Avec l authentification, oblige d utiliser sendMessage meme pour une seule adresse... */
		 
		    tr.sendMessage(message,message.getAllRecipients());
		    tr.close();
		    System.out.println("Message envoye avec succes !");
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}
}