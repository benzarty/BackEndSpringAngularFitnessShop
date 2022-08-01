package tn.esprit.spring.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Repository.ProduitDao;
import tn.esprit.spring.Repository.ReviewRepository;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Reviews;

@Service
public class ReviewServices {
	
	
	@Autowired
	ReviewRepository reviewRepository;
	
	@Autowired
	ProduitDao produitDao;
	
	
	
	
	  public List<Reviews> retrieveAllReviews(Long id) {
			 List<Reviews> reviews=(List<Reviews>)reviewRepository.findbyidproducts(id) ;
			 return reviews;
		}
	  
	  
	  public Reviews addReview(Reviews r,Long idproduit) {
	    	
		  Produit a=produitDao.findById(idproduit).orElse(null);
		  
		  r.setDateCreation(new Date());
		  r.setReviewProduit(a);
		  
			return reviewRepository.save(r);
		}
	  
	  
	  
	  
	  

}
