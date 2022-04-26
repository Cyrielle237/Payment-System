package paymentSystem.entity.ref;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;
import paymentSystem.entity.rh.Personnel;
import paymentSystem.util.DateService;

@Getter
@Setter
@Entity
@Table(name="enfant")
public class Enfant{
	@Id
	@Column(name="code")
	private String code;
	@Column(name="nom")
	private String nom;

	@Column(name="date_naissance")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateNaissance;

	@ManyToOne
	@JoinColumn(name="code_personnel", nullable = false)
	private Personnel personnel;

	@Transient
	private Long age;

	public Long getAge() {
		if(age==null)
			age=DateService.getInstance().dateDiff(dateNaissance, LocalDate.now());
		return age;
	}

}
