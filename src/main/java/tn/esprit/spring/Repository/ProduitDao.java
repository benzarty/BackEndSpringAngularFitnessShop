package tn.esprit.spring.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Produit;

@Repository
public interface ProduitDao extends CrudRepository<Produit, Long> {
	
	
	@Query("SELECT c FROM Produit c WHERE c.stockproduit.idStock= :id")
	List<Produit> retrieveproduitbyidstock(@Param("id") Long id);
}
