package paymentSystem.entity.paie;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import paymentSystem.entity.ref.Mois;
import paymentSystem.util.DateService;
import paymentSystem.util.TypeEtatPaie;

@Getter
@Setter
@ToString
@Entity
@Table(name="paie") 
public class Paie  {
	@Id
	@Column(name="code")
	private String code;
	@Column(name="annee")
	private Integer annee;
	@ManyToOne
	@JoinColumn(name="code_mois",nullable=false)
	private Mois mois;
	@Column(name="etat")
	private String etat;
	@Column(name="date_ouverture")
	private LocalDate dateOuverture ;
	@Column(name="date_cloture")
	private LocalDate dateCloture;
	@Column(name="date_annulation")
	private LocalDate dateAnnulation;
	@Transient
	private String libelleDateOuverture,
	libelleDateCloture,
	libelleDateAnnulation,
	libelleMois,
	libelleEtat;


	public String getLibelleDateOuverture() {
		if(getDateOuverture()!=null)
			this.libelleDateOuverture=DateService.getInstance().format(getDateOuverture());
		return libelleDateOuverture;
	}

	public String getLibelleDateCloture() {
		if(getDateCloture()!=null)
			this.libelleDateCloture=DateService.getInstance().format(getDateCloture());
		return libelleDateCloture;
	}

	public String getLibelleDateAnnulation() {
		if(getDateAnnulation()!=null)
			this.libelleDateAnnulation=DateService.getInstance().format(getDateAnnulation());
		return libelleDateAnnulation;
	}

	public String getLibelleEtat() {
		libelleEtat=TypeEtatPaie.getDesignation(etat);
		return libelleEtat;
	}


}
