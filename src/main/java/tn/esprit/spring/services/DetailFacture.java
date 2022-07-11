package tn.esprit.spring.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Repository.DetailFactureRepository;
import tn.esprit.spring.Repository.FactureRepository;
import tn.esprit.spring.Repository.ProduitDao;
import tn.esprit.spring.Repository.UserDao;
import tn.esprit.spring.entity.Facture;
import tn.esprit.spring.entity.Produit;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.detailFacture;


@Service
public class DetailFacture {
	
	
	@Autowired
	DetailFactureRepository detailrepo;
	
	@Autowired
	ProduitDao produitrepo;
	
	@Autowired
	FactureRepository facturerepo;
	
	@Autowired
	UserDao clientrepo;
	
	
	
	
	
	public void addDetailFacture2(detailFacture c, Long idProduit, String idclient) {
		Facture p=facturerepo.getFacturesByClientAndActive(idclient);
		if(p  != null )
		{
			Long idFacture=p.getIdFacture();
			
			
			Produit prod=produitrepo.findById(idProduit).orElse(null);
			
			
			c.setPourcentageRemise(5);
			Float prix=prod.getPrixUnitaire();
			Float prixtotal=prix*c.getQte();
			
			Facture fact=facturerepo.findById(idFacture).orElse(null);
			
			Float mantantremise=c.getPourcentageRemise()*prixtotal/100;
			
			Float newprix=prixtotal-mantantremise;
			
			c.setPrixtotal(newprix);
			c.setMontantRemise(mantantremise);
			
		c.setTotheparentdetailfacture(prod);
		c.setFacturechildren(fact);
		
	           fact.setMontantRemise(fact.getMontantRemise()+mantantremise);
	           fact.setMontantFacture(fact.getMontantFacture()+newprix);
		detailrepo.save(c);
			
			
			
			
		}
		
		else {
	Facture facnew=new Facture();
	  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	  Date date = new Date();
	  
	   facnew.setDatefacture(date);
		User client=clientrepo.findById(idclient).orElse(null);

		facnew.setClientFacure(client);
		facnew.setModepaiement("Espece");
		facnew.setActive(true);

			
			
			facturerepo.save(facnew);
			
			Facture pp=facturerepo.getFacturesByClientAndActive(idclient);
			
Long idFacture=pp.getIdFacture();
			
			
			Produit prod=produitrepo.findById(idProduit).orElse(null);
			
			
			c.setPourcentageRemise(5);
			Float prix=prod.getPrixUnitaire();
			Float prixtotal=prix*c.getQte();
			
			Facture fact=facturerepo.findById(idFacture).orElse(null);
			
			Float mantantremise=c.getPourcentageRemise()*prixtotal/100;
			
			Float newprix=prixtotal-mantantremise;
			
			c.setPrixtotal(newprix);
			c.setMontantRemise(mantantremise);
			
		c.setTotheparentdetailfacture(prod);
		c.setFacturechildren(fact);
		
	           fact.setMontantRemise(fact.getMontantRemise()+mantantremise);
	           fact.setMontantFacture(fact.getMontantFacture()+newprix);
		detailrepo.save(c);
			
			
			
			
			
		}

		
	
		
		
		
	}
	
	
	
	
	public List<detailFacture> retrieveDetailFactureByidFacture(Long idfacture) {
		return detailrepo.getDetailFactureByFacture(idfacture);
	}
	
	
	
	
	
	
	
	
	
	

}
