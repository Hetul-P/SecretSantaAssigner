package patelhetul;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;

public class RoleAssigner {
	
	private Person head = null;
	private Person tail = null;
	
	public void addPerson(String name, String email) {
		
		Person newPerson = new Person(name, email);
		
		// if the list is empty than add first person
		if(head == null) {
			head = newPerson;
		}
		
		// not empty so add person to end and make previous point to new
		else {
			tail.partner = newPerson;
		}
		
		// change location of end to the new person
		tail = newPerson;
		
		// make new person point to head 
		tail.partner = head;
	}
	
	public void traverseSend() throws AddressException, MessagingException {
		Person current = head;
		
		if(head != null) {
				do {
					System.out.println(current.email + "-" + current.name + " will gift " + current.partner.name);
					final String fromEmail = "youremail@gmail.com"; //requires valid gmail id
					final String password = "yourpass"; // correct password for gmail id
					final String toEmail = current.email; // can be any email id 
					
					System.out.println("TLSEmail Start");
					Properties props = new Properties();
					props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
					props.put("mail.smtp.port", "587"); //TLS Port
					props.put("mail.smtp.auth", "true"); //enable authentication
					props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
					
			                //create Authenticator object to pass in Session.getInstance argument
					Authenticator auth = new Authenticator() {
						//override the getPasswordAuthentication method
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(fromEmail, password);
						}
					};
					Session session = Session.getInstance(props, auth);
					
					EmailUtil.sendEmail(session, toEmail,"Secret Santa", "The person you are gifting is " + current.partner.name);
					

					current = current.partner;
				} while(current != head);
		}
		else {
			System.out.println("Empty List of Participants");	
		}
	}
	
}
