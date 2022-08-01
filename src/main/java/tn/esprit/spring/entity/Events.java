package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Events implements Serializable {
	

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idEvent; 
	
	private String title;

	
	private String Description;
	
	@Column(unique=true)
  @Temporal(TemporalType.DATE)
    private Date date;
	
	private String host;
	
	
	@ManyToOne
	@JsonIgnore
	private User ClientEvent; 
	

}
