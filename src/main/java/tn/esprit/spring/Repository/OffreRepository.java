package tn.esprit.spring.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Offres;

@Repository
public interface OffreRepository extends CrudRepository<Offres, Long> {

}
