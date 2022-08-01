package tn.esprit.spring.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import tn.esprit.spring.entity.Events;
import tn.esprit.spring.services.EventServices;

@RestController
@RequestMapping("/Event")
public class EventController {
	
	@Autowired
	EventServices eventServices;
	
	
	@GetMapping("/retrieve-all-Event")
	@ResponseBody
	public List<Events>getEvents() {
		return eventServices.retrieveAllEvents();
	}
	
	@PostMapping("/add-Event")
    @PreAuthorize("hasRole('Admin')")
    @ResponseBody
	public Boolean addEvents(@RequestBody Events r)
	{
		return eventServices.addEvent(r);
	}
	
	
	
	@GetMapping("/retrieveEventsbydate/{date}")
	@ResponseBody
  public Events retriveEventDate(@PathVariable("date") String date) throws ParseException {
		
		Date  startDate1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);

		return eventServices.retrieveByDate(startDate1);
	}
	
	@GetMapping("/retrieve-Events/{Events-id}")
	@ResponseBody
  public Events retriveEvent(@PathVariable("Event-id") Long Eventid) {
		return eventServices.retrieveEvent(Eventid);
	}
	


	@DeleteMapping("/remove-Event/{Event-id}")
	@ResponseBody
    @PreAuthorize("hasRole('Admin')")
public void removeEvent(@PathVariable("Event-id") Long Eventid) {
		eventServices.deleteEvent(Eventid);
	}
	

}
