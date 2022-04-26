package paymentSystem.entity.paie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Categorie;
import paymentSystem.entity.rh.Echelon;
import paymentSystem.entity.rh.Fonction;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.entity.rh.Rang;
import paymentSystem.util.TypeStatutPersonnel;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="regleCalculpaie") 
public class RegleCalculPaie   {
	@Id
	@Column(name="code")
	private String code;

	@Column(name="formule")
	private String formule;

	@Column(name="formule_base")
	private String formuleBase;

	@Column(name="taux_applique")
	private Double tauxApplique;

	@Column(name="anciennete_inf")
	private Long ancienneteInf;

	@Column(name="anciennete_sup")
	private Long ancienneteSup;

	@ManyToOne
	@JoinColumn(name="code_categorie")
	private Categorie categorie;

	@ManyToOne
	@JoinColumn(name="code_echelon")
	private Echelon echelon;

	@ManyToOne
	@JoinColumn(name="code_fonction")
	private Fonction fonction;

	@ManyToOne
	@JoinColumn(name="code_rang")
	private Rang rang;

	@ManyToOne
	@JoinColumn(name="code_variable_paie")
	private VariablePaie variablePaie;

	@Column(name="valeur_inf")
	private Long valeurInf;

	@Column(name="valeur_sup")
	private Long valeurSup;

	@ManyToOne
	@JoinColumn(name="code_rubrique_paie")
	private RubriquePaie rubriquePaie;

	@Column(name="nombre")
	private Integer nombre=1;

	@ManyToOne
	@JoinColumn(name="code_personnel")
	private Personnel personnel;

	@Column(name="statut_personnel")
	private String statutPersonnel;

	@Transient
	private String libelleStatut;

	@ManyToOne
	@JoinColumn(name="code_mois")
	private Mois mois;

	@Column(name="annee")
	private Integer annee;

	public String getLibelleStatut() {
		return TypeStatutPersonnel.getDesignation(statutPersonnel);
	}

	public void setTauxApplique(Integer tauxApplique) {
		if(tauxApplique!=null)
			this.tauxApplique = tauxApplique*1.0;
		else tauxApplique=null;
	}

	public void setAncienneteInf(Integer ancienneteInf) {
		if(ancienneteInf!=null)
			this.ancienneteInf = ancienneteInf*1L;
	}

	public void setAncienneteSup(Integer ancienneteSup) {
		if(ancienneteInf!=null)
			this.ancienneteSup = ancienneteSup*0L;
	}

	public void setValeurInf(Integer valeurInf) {
		if(valeurInf!=null)
			this.valeurInf = valeurInf*1L;
		else valeurInf=null;
	}

	public void setValeurSup(Integer valeurSup) {
		if(valeurSup!=null)
			this.valeurSup = valeurSup*1L;
		else valeurSup=null;
	}

	public Fonction getFonction() {
		return fonction;
	}

	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}

	public void setTauxApplique(Double tauxApplique) {
		if(tauxApplique!=null)
			this.tauxApplique = tauxApplique*1.0;
		else tauxApplique=null;

	}


}
