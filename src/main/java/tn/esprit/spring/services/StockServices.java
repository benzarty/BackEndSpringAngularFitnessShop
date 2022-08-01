package tn.esprit.spring.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Repository.StockDao;
import tn.esprit.spring.entity.Stock;

@Service
public class StockServices {
	
	@Autowired
	StockDao stockRepo;
	
	public List<Stock> retrieveAllStocks() {
		return (List<Stock>) this.stockRepo.findAll();
	}

	public Stock addStock(Stock s) {
		s.setDateCreation(new Date());
		s.setQte(0);

		return this.stockRepo.save(s);
	}

	
	public Stock updateStock(Stock s) {
		if(this.stockRepo.existsById(s.getIdStock())){
			s.setDateDerniereModification(new Date());
			return this.stockRepo.save(s);
		 }
		 return null;	
	}

	
	public Stock retrieveStock(Long id) {
			return this.stockRepo.findById(id).orElse(null);
	}

	
	public int deleteStock(Long id) {
		 if(this.stockRepo.existsById(id)){
			 stockRepo.deleteById(id);
			 return 1;
		 }
		 return 0;
	}

	


	
	

}


