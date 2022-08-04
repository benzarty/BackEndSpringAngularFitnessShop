package tn.esprit.spring.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Messages;

@Repository
public interface MessageRepository extends CrudRepository<Messages, Long> {

	@Query("SELECT c FROM Messages c where c.towho= :to ORDER BY dateEnvoi desc")
	List<Messages> findmessages(@Param("to") String to);
	
	
	
	 @Transactional
		@Modifying
		@Query("update Messages c set c.readed= true where c.idMessage= :s")
		void readedMessage(@Param("s") Long s);
	

}
