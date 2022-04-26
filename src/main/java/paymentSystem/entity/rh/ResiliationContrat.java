package paymentSystem.entity.rh;

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

@Getter
@Setter
@ToString
@Entity
@Table(name="resiliationcontrat")
public class ResiliationContrat  {
	@Id
	@Column(name="code")
	private String code;

	@ManyToOne
	@JoinColumn(name="code_personnel")
	private Personnel personnel;

	@Column(name="date_depart")
	private LocalDate dateResiliation;

	@Column(name="motif")
	private String motif;

	@Transient
	private String libelleMotif;

}
