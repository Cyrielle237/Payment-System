package paymentSystem.entity.paie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import paymentSystem.entity.ref.Mois;
import paymentSystem.entity.rh.Personnel;

@Getter
@Setter
@Entity
@Table(name="rubriquebulletinpaie") 
public class RubriqueBulletinPaie {
	@Id
	@Column(name="code")
	private String code;
	@ManyToOne
	@JoinColumn(name="code_personnel",nullable=false)
	private Personnel personnel;
	@ManyToOne
	@JoinColumn(name="code_mois",nullable=false)
	private Mois mois;
	@Column(name="annee")
	private Integer annee;
	@ManyToOne
	@JoinColumn(name="code_rubrique_paie",nullable=false)
	private RubriquePaie rubriquePaie;
	@Column(name="montant")
	private Long montant;
	@Column(name="base")
	private Long base;
	@Column(name="pourcentage")
	private Double pourcentage;
	@Column(name="nombre")
	private Integer nombre;
	@ManyToOne
	@JoinColumn(name="code_bulletin_paie")
	private BulletinPaie bulletinPaie;

}
