package tn.esprit.spring.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Facture;

@Repository
public interface FactureRepository extends CrudRepository<Facture, Long> {
	
	@Query("SELECT c FROM Facture c where c.ClientFacure.userName= :idClient AND c.active= TRUE ")
	Facture getFacturesByClientAndActive(@Param("idClient") String idClient);
	
	
	@Query("SELECT c FROM Facture c where c.ClientFacure.userName= :idClient  ")
	List<Facture> getFacturesHistoriqueClient(@Param("idClient") String idClient);
	
	
	
	
	 @Transactional
		@Modifying
		@Query("update Facture c set c.active=FALSE where c.idFacture= :idfacture")
		void Closefacture(@Param("idfacture") Long idfacture);
}
