package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Messages;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.services.MessagesServices;

@RestController

@RequestMapping("/Message")
public class MessageController {

	@Autowired
	private MessagesServices messagesServices;

	@GetMapping("/retrieveallMessagesbyuser/{mail}")

	@ResponseBody
	public List<Messages> getMessagesByClient(@PathVariable("mail") String mail) {
		return messagesServices.retrieveAllMessages(mail);
	}

	@PostMapping("/addMessage/{messagewhosend}")

	@ResponseBody
	public Messages addMessages(@RequestBody Messages m, @PathVariable("messagewhosend") String messagewhosend) {
		return messagesServices.addMessages(m, messagewhosend);
	}

	@PostMapping("/addMessageAdmin/{messagewhosend}")

	@ResponseBody
	public Boolean addMessageAdmin(@RequestBody Messages m, @PathVariable("messagewhosend") String mail) {
		return messagesServices.sendMessagetoAdmin(m, mail);
	}

	@PostMapping("/sendMessagetoUsers/{email}")

	@ResponseBody
	public void addMessageUsers(@RequestBody Messages m, @PathVariable("email") String email) {
		messagesServices.sendMessagetoUsers(m, email);
	}

	@PostMapping("/reded/{id}")

	@ResponseBody
	public void addMessageUsers(@PathVariable("id") Long id) {
		messagesServices.ReadedMails(id);
	}

	@PostMapping("/sendMessagetoUsers2")

	@ResponseBody
	public void addMessageUsers2(@RequestBody Messages m) {
		messagesServices.sendMessagetoUsers2(m);
	}

	@GetMapping("/retrieveMessages/{messageid}")

	@ResponseBody
	public Messages retriveMessage(@PathVariable("messageid") Long messageid) {
		return messagesServices.retrieveMessages(messageid);
	}

	@DeleteMapping("/removeMessage/{messageid}")

	@ResponseBody
	public void removeMessage(@PathVariable("messageid") Long messageid) {
		messagesServices.deleteMessages(messageid);
	}

}
