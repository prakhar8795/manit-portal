package in.ac.manit.portal.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import in.ac.manit.portal.util.MailSenderUtil;

public class MailSenderService {
	

	public void sendMail(String to, String reciever, String pass ) {
    	
		
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");

       	MailSenderUtil msu = (MailSenderUtil) context.getBean("mailSenderUtil");
       	
        msu.sendMail("manitportal@gmail.com", to, "Password Recovery", "Hi " + reciever + ", \n Your password for MANIT Portal is " + pass);
        
	}
	
	

	
}
