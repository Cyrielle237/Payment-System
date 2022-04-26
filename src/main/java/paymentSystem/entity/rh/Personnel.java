package paymentSystem.entity.rh;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import paymentSystem.entity.ref.Agence;
import paymentSystem.entity.ref.Banque;
import paymentSystem.entity.ref.Quartier;
import paymentSystem.entity.ref.Region;
import paymentSystem.util.DateService;
import paymentSystem.util.TypeEtatPersonnel;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="personnel")
public class Personnel {
	@Id
	@Column(name="code")
	private String code;

	@NotBlank
	@Column(name="matricule",unique=true)
	private String matricule;

	@NotBlank
	@Column(name="nom",nullable=false)
	private String nom;

	@Column(name="prenom")
	private String prenom;

	@Column(name="civilite")
	private String civilite;

	@Column(name="statut")
	private String statut;

	@Column(name="situation_matrimonial")
	private String situationMatrimoniale;

	@Column(name="sexe")
	private String sexe;

	@Column(name="matricule_fp")
	private String matriculeFP;

	@Column(name="date_prise_service")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate datePriseService;

	@Past
	@Column(name="date_naissance")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateNaissance;

	@Column(name="numero_telephone")
	private String numeroTelephone;

	@Email
	@Column(name="adresse_email")
	private String adresseEmail;

	@ManyToOne
	@JoinColumn(name="code_quartier")
	private Quartier quartier;

	@ManyToOne
	@JoinColumn(name="code_structure")
	private Structure structure;

	@ManyToOne
	@JoinColumn(name="code_agence")
	private Agence agence;

	@ManyToOne
	@JoinColumn(name="code_categorie",nullable=false)
	private Categorie categorie;

	@ManyToOne
	@JoinColumn(name="code_echelon",nullable=false)
	private Echelon echelon;

	@ManyToOne
	@JoinColumn(name="code_fonction")
	private Fonction fonction;

	@Column(name="majoration_interne")
	private Double majorationInterne;

	@Column(name="date_fin_contrat")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateFinContrat;

	@Positive
	@Column(name="duree_contrat")
	private Integer dureeContrat;

	@Column(name="etat")
	private String etat;

	@Column(name="matricule_cnps")
	private String matriculeCNPS;

	@Column(name="numero_dipe")
	private String numeroDIPE;

	@Column(name="compte_bancaire")
	private String compteBancaire;

	@ManyToOne
	@JoinColumn(name="code_banque")
	private Banque banque;

	@ManyToOne
	@JoinColumn(name="code_rang")
	private Rang rang;

	@ManyToOne
	@JoinColumn(name="code_region_origine")
	private Region regionOrigine;

	@Transient
	private Integer nbreEnfants;

	@Transient
	private Integer nbreEnfantsMineurs;

	@Transient
	private String libelleEtat;

	@Transient
	private String libelleSituationMatrimoniale;

	@Transient
	private String libelleStatut;

	@Transient
	private String libelleSexe;

	public Long getAnciennete() {
		if(getDatePriseService()!=null)
			return DateService.getInstance().dateDiff(LocalDate.now(), getDatePriseService());
		else return 0L;

	}

	public Long getAnciennete(LocalDate date) {
		if(getDatePriseService()!=null)
			return DateService.getInstance().dateDiff(date, getDatePriseService());
		else return 0L;

	}	

	public Integer getDureeContrat() {
		if(dureeContrat==null) return 0;
		return dureeContrat;
	}

	public Integer getNbreEnfants() {
		if(nbreEnfants==null) return 0;
		return nbreEnfants;
	}

	public Integer getNbreEnfantsMineurs() {
		if(nbreEnfantsMineurs==null) return 0;
		return nbreEnfantsMineurs;
	}

	public String getLibelleEtat() {
		this.libelleEtat=TypeEtatPersonnel.getDesignation(getEtat());
		return this.libelleEtat;
	}

	public String getLibelleDateEmbauche (){
		return DateService.getInstance().format(datePriseService) ;
	}

}
