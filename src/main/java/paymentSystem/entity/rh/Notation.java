package paymentSystem.entity.rh;

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

@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name="notation")
public class Notation  {
	@Id
	@Column(name="code")
	private String code;
	@Column(name="annee")
	private Integer annee;

	@ManyToOne
	@JoinColumn(name="code_personnel")
	private Personnel personnel;

	@Column(name="note")
	private Double note;

	@Column(name="observations")
	private String observations;
}
