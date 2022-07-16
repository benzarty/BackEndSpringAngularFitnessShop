	package tn.esprit.spring.services;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.Repository.FactureRepository;
import tn.esprit.spring.Repository.UserDao;
import tn.esprit.spring.entity.Facture;
@Slf4j
@Service
public class FactureServiceImpl   {

	@Autowired
	FactureRepository facturerepo;
	
	@Autowired
	 UserDao repoclient;
	
	

	public Facture getFacturesByClient(String idClient) {
		
		
	return 	facturerepo.getFacturesByClientAndActive(idClient);
		
	}
	
	
	public List<Facture> getFacturesHistoriqueClient(String idClient) {
		return 	facturerepo.getFacturesHistoriqueClient(idClient);
	}
	
	

	
	public void Closefacture(Long idfacture) {
		
		
		facturerepo.Closefacture(idfacture);

				
	}


	
	

}
