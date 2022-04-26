package paymentSystem.entity.ref;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@Table(name="agence")
public class Agence {

	@Id
	@Column(name="code")
	private String code;

	@Column(name="designation")
	private String designation;
	@ManyToOne
	@JoinColumn(name="code_ville")
	private Ville ville;


	public String getDesignation() {
		return designation;
	}

}
