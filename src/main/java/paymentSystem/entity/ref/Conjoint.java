package paymentSystem.entity.ref;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import paymentSystem.entity.rh.Personnel;

@Getter
@Setter
@ToString
@Entity
@Table(name="conjoint")
public class Conjoint {
	@Id
	@Column(name="code")
	private String code;
	@Column(name="nom")
	private String nom;
	@Column(name="date_naissance")
	private LocalDate dateNaissance;
	@OneToOne
	@JoinColumn(name="code_personnel")
	private Personnel personnel;
	@Column(name="profession")
	private String profession;
	@Column(name="employeur")
	private String employeur;
	@Column(name="contact")
	private String contact;
}
