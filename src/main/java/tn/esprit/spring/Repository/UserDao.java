package tn.esprit.spring.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.detailFacture;

@Repository
public interface UserDao extends CrudRepository<User, String> {
	
	/*@Transactional
	@Modifying
	@Query("update Facture c set c.Modepaiement= :Modepaiement where c.idFacture= :idfacture")
	int UpdateUsercustom(@Param("idfacture") Long idfacture,@Param("Modepaiement") String Modepaiement);*/
	
	
	
	
	@Query("SELECT c FROM User c where c.Email= :Mail")
	User findByEmail(@Param("Mail") String Mail);
	
	
}
