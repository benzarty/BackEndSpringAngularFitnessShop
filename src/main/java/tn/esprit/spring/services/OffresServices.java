package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Repository.OffreRepository;
import tn.esprit.spring.entity.Offres;

@Service
public class OffresServices {

	@Autowired
	OffreRepository offreRepository;

	public List<Offres> retrieveAllOffers() {
		return (List<Offres>) this.offreRepository.findAll();
	}

	public Offres addOffers(Offres s) {

		return this.offreRepository.save(s);

	}

	public Offres retrieveOffers(Long id) {
		return this.offreRepository.findById(id).orElse(null);
	}

	public int deleteOffers(Long id) {
		if (this.offreRepository.existsById(id)) {
			offreRepository.deleteById(id);
			return 1;
		}
		return 0;
	}

}
