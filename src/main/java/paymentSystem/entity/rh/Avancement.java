package paymentSystem.entity.rh;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name="avancement")
public class Avancement {
	@Id
	@Column(name="code")
	private String code;
	@OneToOne
	@JoinColumn(name="code_nouvelle_categorie")
	private Categorie nouvelleCategorie;

	@OneToOne
	@JoinColumn(name="code_ancienne_categorie")
	private Categorie ancienneCategorie;

	@OneToOne
	@JoinColumn(name="code_nouvel_echelon")
	private Echelon nouvelEchelon;

	@OneToOne
	@JoinColumn(name="code_ancien_echelon")
	private Echelon ancienEchelon;

	@ManyToOne
	@JoinColumn(name="code_personnel")
	private Personnel personnel;

	@Column(name="date_avancement")
	private LocalDate dateAvancement;


}
