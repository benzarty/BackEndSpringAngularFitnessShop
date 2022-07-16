package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Repository.FactureRepository;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.services.FactureServiceImpl;


@RestController
@RequestMapping("/facture")
public class FactureController {
	
	@Autowired
	FactureServiceImpl factureserv;
	
	
	
	@Autowired
	FactureRepository facrepo;
	
	
	
	
	@GetMapping("/getFacturesByClient/{idClient}")
	@ResponseBody
     Facture getFacturesByClient(@PathVariable("idClient") String idClient) {
	   return factureserv.getFacturesByClient(idClient);
	
	}
	
	
	
	@GetMapping("/getFacturesHistorique/{idClient}")
	@ResponseBody
     List<Facture>getFacturesHistorique(@PathVariable("idClient") String idClient) {
	   return factureserv.getFacturesHistoriqueClient(idClient);
	
	}
	
	
	
	@PutMapping("/closefacture/{idfacture}")
	@ResponseBody
	public void closefacture(@PathVariable("idfacture") Long idfacture) {
	 factureserv.Closefacture(idfacture);
	}
	
	
	
	
	
	
	
	
	

}
