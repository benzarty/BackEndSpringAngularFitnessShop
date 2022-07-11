package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.detailFacture;


@Repository
public interface DetailFactureRepository extends CrudRepository<detailFacture, Long> {
	
	@Query("SELECT c FROM detailFacture c where c.facturechildren.idFacture= :idFacture ")
	List<detailFacture> getDetailFactureByFacture(@Param("idFacture") Long idFacture);

}
