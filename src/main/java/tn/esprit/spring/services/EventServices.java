package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Repository.EventRepository;
import tn.esprit.spring.entity.Events;

@Service
public class EventServices {
	
	
	@Autowired
	EventRepository eventRepository;
	
	
	
	public List<Events> retrieveAllEvents() {
		return (List<Events>) this.eventRepository.findAll();
	}
	
	public Events retrieveByDate(Date date) {
		return this.eventRepository.searchEailll(date);
	}

	
	public Boolean addEvent(Events s) {
		
		Events ev=this.eventRepository.searchEmail(s.getDate());
		if(ev==null)
		{
		 this.eventRepository.save(s);
		 
			 return true;
		 }
		
		 return false;
	}
	
	
	public Events retrieveEvent(Long id) {
		return this.eventRepository.findById(id).orElse(null);
}


public int deleteEvent(Long id) {
	 if(this.eventRepository.existsById(id)){
		 eventRepository.deleteById(id);
		 return 1;
	 }
	 return 0;
}
	

}
