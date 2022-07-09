package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.services.StockServices;

@RestController
@RequestMapping("/Stock")
public class StockController {
	
	@Autowired
	StockServices stockServices;
	
	
	@GetMapping("/retrieve-all-Stock")
    @PreAuthorize("hasRole('Admin')")
	@ResponseBody
	public List<Stock>getStocks() {
		return stockServices.retrieveAllStocks();
	}
	
	@PostMapping("/add-Stock")
    @PreAuthorize("hasRole('Admin')")
    @ResponseBody
	public Stock addStock(@RequestBody Stock r)
	{
		return stockServices.addStock(r);
	}
	
	@GetMapping("/retrieve-Stock/{Stock-id}")
	@ResponseBody
    @PreAuthorize("hasRole('Admin')")
  public Stock retriveStock(@PathVariable("Stock-id") Long Stock) {
		return stockServices.retrieveStock(Stock);
	}
	


	@DeleteMapping("/remove-Stock/{Stock-id}")
	@ResponseBody
    @PreAuthorize("hasRole('Admin')")
public void removeStock(@PathVariable("Stock-id") Long Stock) {
		stockServices.deleteStock(Stock);
	}

	@PutMapping("/modify-Stock")
	@ResponseBody
    @PreAuthorize("hasRole('Admin')")
    public Stock modifyStock(@RequestBody Stock Stock) {
		return stockServices.updateStock(Stock);
	}

}
