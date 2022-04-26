package paymentSystem.entity.rh;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import paymentSystem.entity.ref.Agence;

@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name="affectation")
public class Affectation {

	@Id
	@Column(name="code")
	private String code;
	@ManyToOne
	@JoinColumn(name="code_personnel")
	private Personnel personnel;

	@Column(name="date_affectation")
	private LocalDate dateAffectation;

	@ManyToOne
	@JoinColumn(name="code_nouvelle_agence")
	private Agence nouvelleAgence;

	@ManyToOne
	@JoinColumn(name="code_ancienne_agence")
	private Agence ancienneAgence;

	@ManyToOne
	@JoinColumn(name="code_nouvelle_structure")
	private Structure nouvelleStructure;

	@ManyToOne
	@JoinColumn(name="code_ancienne_structure")
	private Structure ancienneStructure;

	@ManyToOne
	@JoinColumn(name="code_nouvelle_fonction")
	private Fonction nouvelleFonction;

	@ManyToOne
	@JoinColumn(name="code_ancienne_fonction")
	private Fonction ancienneFonction;

}
