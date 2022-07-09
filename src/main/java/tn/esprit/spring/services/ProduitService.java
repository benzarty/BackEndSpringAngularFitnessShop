package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Repository.ProduitDao;
import tn.esprit.spring.entity.Produit;


@Service
public class ProduitService {
	
	
@Autowired
ProduitDao produitDao;


public List<Produit> retrieveAllProduits() {
	// TODO Auto-generated method stub
	return (List<Produit>) this.produitDao.findAll();
}

public List<Produit> retrieveproduitbyidstock(Long id) {
	// TODO Auto-generated method stub
	return (List<Produit>) this.produitDao.retrieveproduitbyidstock(id);
}




  public Produit addproduit(Produit p) { 
  
  
  return produitDao.save(p);
  
  }
 	


public Produit retrieveProduit(Long id) {
	// TODO Auto-generated method stub
	return produitDao.findById(id).orElse(null);
}


public void deleteproduit(Long id) {
	produitDao.deleteById(id);
	
}


public Produit updateproduit(Produit c) {

	return produitDao.save(c);
}






public Produit addProduitfile(Produit p, String file) {
	p.setFileName(file);
			return produitDao.save(p);
}




public void afecterProduitimage(Long idProduit, String file) {
	// TODO Auto-generated method stub
	Produit produit=produitDao.findById(idProduit).orElse(null);// TODO Auto-generated method stub
	produit.setFileName(file);
	produitDao.save(produit);
	}






}
