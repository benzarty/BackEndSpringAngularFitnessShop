package tn.esprit.spring.Repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Events;
import tn.esprit.spring.entity.User;


@Repository
public interface EventRepository extends CrudRepository<Events, Long> {
	
	
	
	@Query("SELECT c FROM Events c where c.date= :date")
	Events searchEmail(@Param("date") Date date);
	
	
	@Query("SELECT c FROM Events c where c.date= :date")
	Events searchEailll(@Param("date") Date date);
	
	
	

}
