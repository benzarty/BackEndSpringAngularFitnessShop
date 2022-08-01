package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Reviews;
import tn.esprit.spring.services.ReviewServices;

@RestController
@RequestMapping("/Reviews")
public class ReviewsController {
	
	
	
	@Autowired
	ReviewServices reviewServices;
	
	
	@GetMapping("/retrieve-getReviews/{idproduit}")
	@ResponseBody
	public List<Reviews> getReviews(@PathVariable("idproduit") Long idproduit) {
		List<Reviews> listproduits = reviewServices.retrieveAllReviews(idproduit);
		return listproduits;
	}
	
	
	
	@PostMapping("/addReview/{idproduit}")
    @ResponseBody
	public Reviews addReview(@RequestBody Reviews r,@PathVariable("idproduit") Long idproduit)
	{
		return reviewServices.addReview(r,idproduit);
	}
	

}
