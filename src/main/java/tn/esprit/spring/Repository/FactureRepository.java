package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Facture;

@Repository
public interface FactureRepository extends CrudRepository<Facture, Long> {
	
	@Query("SELECT c FROM Facture c where c.ClientFacure.userName= :idClient AND c.active= TRUE ")
	Facture getFacturesByClientAndActive(@Param("idClient") String idClient);

}
