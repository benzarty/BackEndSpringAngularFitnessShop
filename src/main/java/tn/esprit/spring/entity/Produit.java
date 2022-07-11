package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produit implements Serializable{
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idProduit; 
	private String code;
	private String libelle;
	private float prixUnitaire;
	private String fileName;
	
	private String description;

	
	
	
	


	@OneToMany(cascade = CascadeType.ALL, mappedBy="Totheparentdetailfacture")   
	@JsonIgnore
	private Set<detailFacture> detailFacture; 
	
/*	
	@OneToOne(cascade = CascadeType.MERGE) 
	private DetailProduit Detailproduit;  */
	

	
	

	@ManyToOne
	@JsonIgnore
	private Stock stockproduit;  //stock;
	



	
}
