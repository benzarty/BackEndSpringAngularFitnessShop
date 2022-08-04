package tn.esprit.spring.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Repository.MessageRepository;
import tn.esprit.spring.Repository.UserDao;
import tn.esprit.spring.entity.Messages;
import tn.esprit.spring.entity.User;

@Service
public class MessagesServices {

	@Autowired
	MessageRepository messageRepository;

	@Autowired
	UserDao userDao;

	@Autowired
	private JavaMailSender mailSender;

	public List<Messages> retrieveAllMessages(String u) {

		return (List<Messages>) this.messageRepository.findmessages(u);
	}

	public Messages addMessages(Messages s, String iduser) {

		User u = userDao.findById(iduser).orElse(null);

		// s.setDateEnvoi(new ());

		s.setMessageClient(u);
		return this.messageRepository.save(s);

	}

	public Messages retrieveMessages(Long id) {
		return this.messageRepository.findById(id).orElse(null);
	}

	public int deleteMessages(Long id) {
		if (this.messageRepository.existsById(id)) {
			messageRepository.deleteById(id);
			return 1;
		}
		return 0;
	}

	public boolean sendMessagetoAdmin(Messages s, String mail) {

		User u = userDao.findByEmail(mail);

		if (u != null) {

			s.setTowho("m.benzarti.1996@gmail.com");

			s.setMessageClient(u);

			s.setDateEnvoi(new Date(System.currentTimeMillis()));
			s.setReaded(false);

			this.messageRepository.save(s);

			return true;
		}

		return false;
	}

	public void sendMessagetoUsers(Messages s, String mail) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("m.benzarti.1996@gmail.com");
		message.setTo(mail);
		message.setText(s.getDescription());
		message.setSubject("Reply");
		mailSender.send(message);

	}
	
	
	public void sendMessagetoUsers2(Messages s ) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("m.benzarti.1996@gmail.com");
		message.setTo(s.getTowho());
		message.setText(s.getDescription());
		message.setSubject(s.getSubject());
		mailSender.send(message);

	}
	
	public void ReadedMails(Long s)
	{
		
		this.messageRepository.readedMessage(s);
	}
	

}
