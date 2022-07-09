package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.detailFacture;
import tn.esprit.spring.services.DetailFacture;

@RestController
@RequestMapping("/DetailFacture")
public class DetailFactureController {
	
	
	
	@Autowired
	DetailFacture detailfac;
	
	
	@PostMapping("/add-DetailFacture2/{idproduit}/{idclient}")
	@ResponseBody
	public void createProduit2(@RequestBody detailFacture p1,@PathVariable Long idproduit,@PathVariable String idclient) {
		
		detailfac.addDetailFacture2(p1, idproduit, idclient);
			
		
	}

}
