package paymentSystem.entity.paie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.util.DateService;
import paymentSystem.util.TypeModePaiement;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="bulletinpaie") 
public class BulletinPaie {

	@Id
	@Column(name="code")
	private String code;

	@ManyToOne
	@JoinColumn(name="code_personnel",nullable=false)
	private Personnel personnel;

	@ManyToOne
	@JoinColumn(name="code_mois",nullable=false)
	private Mois mois;

	@Column(name="annee",nullable=false)
	private Integer annee;

	@Column(name="date_calcul_paie")
	private LocalDate dateCalculPaie;

	@Column(name="salaire_brut")
	private Long salaireBrut;

	@Column(name="salaire_net")
	private Long salaireNet;

	@Column(name="salaire_brut_taxable")
	private Long salaireBrutTaxable;

	@Column(name="salaire_cotisable")
	private Long salaireCotisable;

	@Column(name="salaire_int_taxable")
	private Long salaireIntTaxable;

	@Column(name="retenues_salariales")
	private Long retenuesSalariales;

	@Column(name="retenues_patronales")
	private Long retenuesPatronales;

	@Column(name="heures_supp")
	private Integer heuresSupp;

	@Column(name="absences")
	private Integer absences;

	@Column(name="mode_paiement")
	private String modePaiement;

	@Column(name="date_creation")  //Added from GenericSecuredEntity
	private LocalDate dateCreation;

	@Transient
	private String libelleModePaiement, libelleDateCreation;

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<RubriqueBulletinPaie> rubriquesBulletinPaie = new ArrayList();

	public String getDateCalculPaieAsString() {
		return DateService.getInstance().format(dateCalculPaie);
	}

	public String getLibelleModePaiement() {
		return TypeModePaiement.getDesignation(modePaiement);
	}

	public String getLibelleDateCreation() {
		if(getDateCreation()!=null)
			this.libelleDateCreation=DateService.getInstance().format(getDateCreation());
		return libelleDateCreation;
	}


}
