package tn.esprit.spring.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.Repository.ProduitDao;
import tn.esprit.spring.Repository.StockDao;
import tn.esprit.spring.domaine.Response;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.services.ProduitService;

@RestController
@RequestMapping("/Produit")
public class ProduitController {

	@Autowired
	ProduitService produitService;
	
	@Autowired  ServletContext 
	context;


	@Autowired
	ProduitDao produitDao;
	
	@Autowired 
	StockDao stockDao;
	

	@GetMapping("/retrieve-all-produits")
	@ResponseBody
	public List<Produit> getProduits() {
		List<Produit> listproduits = produitService.retrieveAllProduits();
		return listproduits;
	}
	
	
	@GetMapping("/retrieveproduitbyidstock/{idStock}")
	@ResponseBody
	public List<Produit> retrieveproduitbyidstock(@PathVariable("idStock") Long idStock) {
		List<Produit> listproduits = produitService.retrieveproduitbyidstock(idStock);
		return listproduits;
	}

	// http://localhost:8089/SpringMVC/client/retrieve-client/8
	@GetMapping("/retrieve-produit/{produit-id}")
	@ResponseBody
	public Produit retrieveProduit(@PathVariable("produit-id") Long produitId) {
		return produitService.retrieveProduit(produitId);
	}
	
	
	@PostMapping("/add-produit")
    @PreAuthorize("hasRole('Admin')")
    @ResponseBody
	public Produit addProduit(@RequestBody Produit r)
	{
		return produitService.addproduit(r);
	}
	
	

	@DeleteMapping("/remove-produit/{produit-id}")
    @PreAuthorize("hasRole('Admin')")
    @ResponseBody
	public void removeProduit(@PathVariable("produit-id") Long produitId) {
		produitService.deleteproduit(produitId);
	}

	

	@PostMapping("/file/{stockid}")
	public ResponseEntity<Response> createArticle(@RequestParam("file") MultipartFile file,
			@RequestParam("produit") String produit,@PathVariable Long stockid)
			throws JsonParseException, JsonMappingException, Exception {
		
		
		Stock stock=stockDao.findById(stockid).orElse(null);


			Produit arti = new ObjectMapper().readValue(produit, Produit.class);
		boolean isExit = new File(context.getRealPath("/Images/")).exists();
		if (!isExit) {
			new File(context.getRealPath("/Images/")).mkdir();
			System.out.println("mk dir.............");
		}
		String filename = file.getOriginalFilename();
		String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
		File serverFile = new File(context.getRealPath("/Images/" + File.separator + newFileName));
		try {
       	 FileUtils.writeByteArrayToFile(serverFile,file.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}

		arti.setFileName(newFileName);
		arti.setLibelle(arti.getLibelle());
		arti.setStockproduit(stock);


		Produit art = produitDao.save(arti);

		if (art != null) {
			return new ResponseEntity<Response>(new Response(""), HttpStatus.OK);
		} else {
			return new ResponseEntity<Response>(new Response("Article not saved"), HttpStatus.BAD_REQUEST);
		}
	}

	
	@GetMapping(path = "/Imgarticles/{idProduit}")
	public byte[] getPhoto(@PathVariable("idProduit") Long idProduit) throws Exception {
		Produit Article = produitDao.findById(idProduit).get();
		return Files.readAllBytes(Paths.get(context.getRealPath("/Images/") + Article.getFileName()));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
