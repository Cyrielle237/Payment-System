package paymentSystem.entity.rh;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name="promotion")
public class Promotion {
	@Id
	@Column(name="code")
	private String code;

	@ManyToOne
	@JoinColumn(name="code_personnel")
	private Personnel personnel;

	@ManyToOne
	@JoinColumn(name="code_nouvelle_fonction")
	private Fonction nouvelleFonction;

	@ManyToOne
	@JoinColumn(name="code_ancienne_fonction")
	private Fonction ancienneFonction;

	@Column(name="date_promotion")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate datePromotion;



}
