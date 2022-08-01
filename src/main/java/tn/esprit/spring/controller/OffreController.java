package tn.esprit.spring.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.Repository.OffreRepository;
import tn.esprit.spring.entity.Events;
import tn.esprit.spring.entity.Offres;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.Stock;
import tn.esprit.spring.services.OffresServices;

@RestController
@RequestMapping("/Offres")
public class OffreController {
	
	
	@Autowired
	OffresServices offresServices;
	
	@Autowired  ServletContext 
	context;
	
	
	@Autowired
	OffreRepository offreRepository;
	
	
	@GetMapping("/retrieve-all-Offers")
	@ResponseBody
	public List<Offres>getOffers() {
		return offresServices.retrieveAllOffers();
	}
	
	@PostMapping("/add-Offers")
    @ResponseBody
	public Offres addOffers(@RequestBody Offres r)
	{
		return offresServices.addOffers(r);
	}
	
	
	
	@GetMapping("/retrieve-Offers/{Offers-id}")
	@ResponseBody
  public Offres retriveOffers(@PathVariable("Offers-id") Long offerid) {
		return offresServices.retrieveOffers(offerid);
	}
	


	@DeleteMapping("/remove-Offers/{Offers-id}")
	@ResponseBody
public void removeOffers(@PathVariable("Offers-id") Long offerid) {
		offresServices.deleteOffers(offerid);
	}
	
	
	
	
	@PostMapping("/file")
	public boolean createArticle(@RequestParam("file") MultipartFile file,
			@RequestParam("offre") String offre)
			throws JsonParseException, JsonMappingException, Exception {
		
		
		

		Offres arti = new ObjectMapper().readValue(offre, Offres.class);
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


		Offres art = offreRepository.save(arti);
		
		
		

		
		return true;
	}
	
	
	@GetMapping(path = "/Imgarticles/{idcourse}")
	public byte[] getPhoto(@PathVariable("idcourse") Long idcourse) throws Exception {
		Offres Offre = offreRepository.findById(idcourse).get();
		return Files.readAllBytes(Paths.get(context.getRealPath("/Images/") + Offre.getFileName()));
	}
	
	

}
