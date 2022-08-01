package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Reviews;

@Repository
public interface ReviewRepository extends CrudRepository<Reviews,Long> {
	
	
	
	@Query("SELECT c FROM Reviews c where c.ReviewProduit.idProduit= :idProduit  ")
	List<Reviews> findbyidproducts(@Param("idProduit") Long idProduit);

}
